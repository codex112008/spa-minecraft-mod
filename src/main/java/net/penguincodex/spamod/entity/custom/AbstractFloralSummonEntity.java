package net.penguincodex.spamod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractFloralSummonEntity extends MobEntity {
    protected static final int LIFESPAN = 600;

    protected LivingEntity summoner;
    protected Item dropItem = Items.AIR;

    protected AbstractFloralSummonEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
        this.summoner = null;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(2, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1000);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.age >= LIFESPAN || this.summoner.isDead()){
            DespawnSummon();
        }
    }

    @Override
    protected void onKilledBy(@Nullable LivingEntity adversary) {
        DespawnSummon();
    }

    private void DespawnSummon(){
        this.dropItem(dropItem);
        this.discard();
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPushedByFluids() { return false; }

    @Override
    public void jump() { }
}
