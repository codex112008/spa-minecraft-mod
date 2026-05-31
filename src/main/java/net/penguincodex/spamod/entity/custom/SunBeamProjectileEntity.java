package net.penguincodex.spamod.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.penguincodex.spamod.entity.ModEntities;

public class SunBeamProjectileEntity extends ProjectileEntity {
    private final LivingEntity summonEntity;
    private final LivingEntity summonOwner;

    public SunBeamProjectileEntity(World world, LivingEntity summonEntity, LivingEntity summonOwner) {
        super(ModEntities.SUN_BEAM, world);
        this.summonEntity = summonEntity;
        this.summonOwner = summonOwner;

        this.setNoGravity(true);
    }

    public SunBeamProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.summonEntity = null;
        this.summonOwner = null;

        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        if (hitResult.getType() != HitResult.Type.MISS) {
            this.hitOrDeflect(hitResult);
        }

        Vec3d velocity = this.getVelocity();
        double xVel = velocity.x;
        double yVel = velocity.y;
        double zVel = velocity.z;
        double xPos = this.getX() + xVel;
        double yPos = this.getY() + yVel;
        double zPos = this.getZ() + zVel;
        double horizLen = velocity.horizontalLength();
        this.setYaw((float)(MathHelper.atan2(xVel, zVel) * 180.0F / (float)Math.PI));
        this.setPitch((float)(MathHelper.atan2(yVel, horizLen) * 180.0F / (float)Math.PI));
        this.setPitch(updateRotation(this.prevPitch, this.getPitch()));
        this.setYaw(updateRotation(this.prevYaw, this.getYaw()));

        this.setPosition(xPos, yPos, zPos);

        this.checkBlockCollision();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        Entity hitEntity = entityHitResult.getEntity();
        float damage = 4f;
        if (summonEntity != null)
            damage = (float) summonEntity.getAttributes().getValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        hitEntity.damage(this.getDamageSources().mobProjectile(summonEntity, summonOwner), damage);

        if (!this.getWorld().isClient()){
            this.getWorld().sendEntityStatus(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

        if (!this.getWorld().isClient()){
            this.discard();
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }
}
