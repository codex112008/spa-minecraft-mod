package net.penguincodex.spamod.item.custom;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.penguincodex.spamod.entity.custom.AbstractFloralSummonEntity;
import net.penguincodex.spamod.entity.custom.sunflower_summon.SunflowerSummonEntity;
import net.penguincodex.spamod.entity.custom.tulip_summon.TulipSummonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class FloralStaffItem extends Item {
    private final Random random = Random.create();
    private static final List<Item> VALID_SUMMONING_ITEMS = List.of(Items.SUNFLOWER, Items.PINK_TULIP);
    private static final float RANGE = 5f;

    public FloralStaffItem(Settings settings) {
        super(settings);
    }

    public static void registerDamageCallback() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (!(entity.getWorld() instanceof ServerWorld)) return true;

            if ((source.getSource() instanceof HostileEntity attacker) && attacker.getMainHandStack().getItem() instanceof FloralStaffItem floralStaffItem) {
                floralStaffItem.summonAppropriateFloralSummon(attacker.getWorld(), attacker);
            }

            return true;
        });
    }

    private ItemStack findSummonItemItemStack(PlayerEntity player){
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < inventory.size(); i++){
            ItemStack itemStack = inventory.getStack(i);
            if (VALID_SUMMONING_ITEMS.contains(itemStack.getItem()) && itemStack.getCount() > 0)
                return itemStack;
        }

        if (player.isInCreativeMode())
            return new ItemStack(VALID_SUMMONING_ITEMS.get(random.nextBetweenExclusive(0, VALID_SUMMONING_ITEMS.size())));
        else
            return null;
    }

    private boolean isFloralSummonOnBlock(World world, BlockPos pos) {
        // Finds all entities in block and block above
        List<Entity> entities = world.getOtherEntities(null, new Box(pos));
        entities.addAll(world.getOtherEntities(null, new Box(pos.up())));

        for (Entity entity : entities) {
            if (entity instanceof AbstractFloralSummonEntity) {
                if (entity.getBlockPos().equals(pos)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        summonAppropriateFloralSummon(world, user);

        return super.use(world, user, hand);
    }

    private void summonAppropriateFloralSummon(World world, LivingEntity user){
        HitResult hit = user.raycast(RANGE, 1f, false);
        // If ray cast hits a block or the user is hostile
        if (hit.getType() == HitResult.Type.BLOCK || user instanceof HostileEntity) {
            BlockHitResult blockHit = (BlockHitResult) hit;
            BlockPos blockPos = blockHit.getBlockPos();
            if (user instanceof HostileEntity) // Spawn on a random block around user if hostile mob
                blockPos = getRandomBlockPosAroundBlockPos(world, user.getBlockPos(), 4);

            if (isValidSummoningLocation(world, blockPos)){
                Vec3d spawnPos = blockPos.up().toCenterPos();

                // If used by hostile mob use a random summon, if player then check if relevant item in inventory
                ItemStack summonItemItemStack = new ItemStack(VALID_SUMMONING_ITEMS.get(random.nextBetweenExclusive(0, VALID_SUMMONING_ITEMS.size())));
                if (user instanceof PlayerEntity player)
                    summonItemItemStack = findSummonItemItemStack(player);

                // If there is a valid summoning item then spawn the relevant summon based on item found
                if (summonItemItemStack != null){
                    AbstractFloralSummonEntity summon = null;
                    if (summonItemItemStack.getItem().equals(Items.SUNFLOWER)){
                        summon = new SunflowerSummonEntity(world, user);
                    }
                    else if (summonItemItemStack.getItem().equals(Items.PINK_TULIP)){
                        summon = new TulipSummonEntity(world, user);
                    }

                    if (summon != null){
                        summon.setPos(spawnPos.x, spawnPos.y - 0.495f, spawnPos.z);
                        world.spawnEntity(summon);
                    }

                    // Consume summon item if used by player
                    if (user instanceof PlayerEntity)
                        summonItemItemStack.decrementUnlessCreative(1, user);
                }
            }
        }
    }

    private BlockPos getRandomBlockPosAroundBlockPos(World world, BlockPos centerPos, int radius){
        List<BlockPos> validPositions = new ArrayList<>();
        for (int x = -radius; x <= radius; x++){
            for (int y = -(radius / 2); y <= (radius / 2); y++){
                for (int z = -radius; z <= radius; z++){
                    if (isValidSummoningLocation(world, centerPos.add(x, y, z)))
                        validPositions.add(centerPos.add(x, y, z));
                }
            }
        }

        if (!validPositions.isEmpty()){
            return validPositions.get(random.nextBetweenExclusive(0, validPositions.size()));
        }
        else
            return null;
    }

    private boolean isValidSummoningLocation(World world, BlockPos blockPos){
        if (blockPos != null)
            return (world.getBlockState(blockPos.up()).isReplaceable() && !isFloralSummonOnBlock(world, blockPos));
        else
            return false;
    }
}
