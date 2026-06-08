package net.penguincodex.spamod.entity.custom.sunflower_summon;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.penguincodex.spamod.entity.ModEntities;
import net.penguincodex.spamod.entity.custom.AbstractFloralSummonEntity;

public class SunflowerSummonEntity extends AbstractFloralSummonEntity implements RangedAttackMob {
    // Statuses track animation states in server side
    private static final byte FIRE_ANIMATION_STATUS = 1;
    private static final byte WIND_DOWN_ANIMATION_STATUS = 2;

    private static final int ATTACK_INTERVAL_MIN = 20;
    private static final int ATTACK_INTERVAL_MAX = 30;
    private static final float ATTACK_RADIUS = 10f;

    public final AnimationState idleAnimationState = new AnimationState();
    private int fireAnimTimeout = 0;
    public final AnimationState fireAnimationState = new AnimationState();
    public int windDownAnimTimeout = 0;
    public final AnimationState windDownAnimationState = new AnimationState();

    private LivingEntity target;

    public SunflowerSummonEntity(World world, LivingEntity summoner){
        this(world, summoner, 0f);
    }

    public SunflowerSummonEntity(World world, LivingEntity summoner, float summonItemDropChance){
        super(ModEntities.SUNFLOWER_SUMMON, world, summonItemDropChance, Items.SUNFLOWER);
        this.summoner = summoner;
    }

    public SunflowerSummonEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world, 0, Items.SUNFLOWER);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new ProjectileAttackGoal(this, 0, ATTACK_INTERVAL_MIN, ATTACK_INTERVAL_MAX, ATTACK_RADIUS));

        // Replace this with a better custom goal like wolves
        if (this.summoner instanceof HostileEntity){
            this.targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        }
        else {
            this.targetSelector.add(0, new ActiveTargetGoal<>(this, HostileEntity.class, true));
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes(){
        return AbstractFloralSummonEntity.createAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6);
    }

    @Override
    public void handleStatus(byte status) {
        // Used to start and stop animations from the server side
        if (status == FIRE_ANIMATION_STATUS) {
            this.idleAnimationState.stop();
            this.fireAnimationState.start(this.age);
            this.fireAnimTimeout = 5;
        } else if (status == WIND_DOWN_ANIMATION_STATUS) {
            this.idleAnimationState.stop();
            this.windDownAnimationState.start(this.age);
            this.windDownAnimTimeout = 2;
        } else {
            super.handleStatus(status);
        }
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        this.fireAnimationState.start(this.age);
        this.fireAnimTimeout = 5;
        this.getWorld().sendEntityStatus(this, FIRE_ANIMATION_STATUS);

        this.target = target;
    }

    private void handleAnimations(){
        if (!this.fireAnimationState.isRunning() && !this.windDownAnimationState.isRunning()){
            this.idleAnimationState.start(this.age);
        }

        if (this.fireAnimTimeout > 0) {
            --this.fireAnimTimeout;
        }

        if (this.windDownAnimTimeout > 0) {
            --this.windDownAnimTimeout;
        }

        if (this.fireAnimTimeout <= 0 && this.fireAnimationState.isRunning()){
            this.fireAnimationState.stop();

            fire(this.target);

            this.windDownAnimTimeout = 2;
            this.windDownAnimationState.start(this.age);
            this.getWorld().sendEntityStatus(this, WIND_DOWN_ANIMATION_STATUS);
        }

        if (this.windDownAnimTimeout <= 0 && this.windDownAnimationState.isRunning()){
            this.windDownAnimationState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();

        handleAnimations();
    }

    private void fire(LivingEntity target){
        if (target != null){
            SunBeamProjectileEntity sunBeamProjectile = new SunBeamProjectileEntity(this.getWorld(), this, summoner);
            double x = target.getX() - this.getX();
            double y = target.getBodyY(0.5) - this.getBodyY(0.5);
            double z = target.getZ() - this.getZ();
            sunBeamProjectile.setPosition(this.getEyePos());
            sunBeamProjectile.setVelocity(x, y, z, 1.7f, 1f);
            this.getWorld().spawnEntity(sunBeamProjectile);
        }
    }
}
