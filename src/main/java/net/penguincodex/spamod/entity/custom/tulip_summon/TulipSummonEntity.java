package net.penguincodex.spamod.entity.custom.tulip_summon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.penguincodex.spamod.entity.ModEntities;
import net.penguincodex.spamod.entity.custom.AbstractFloralSummonEntity;

import java.util.List;

public class TulipSummonEntity extends AbstractFloralSummonEntity {
    private static final int ABILITY_INTERVAL = 100;
    private static final float ABILITY_RANGE = 5f;
    private static final int ABILITY_HEAL_DURATION = 75;
    private static final int ABILITY_HEAL_AMPLIFIER = 1;
    private static final int ABILITY_SLOW_DURATION = 50;
    private static final int ABILITY_SLOW_AMPLIFIER = 1;

    private int abilityTimeout = 0;

    public TulipSummonEntity(World world, LivingEntity summoner){
        this(world, summoner, 0f);
    }

    public TulipSummonEntity(World world, LivingEntity summoner, float summonItemDropChance){
        super(ModEntities.TULIP_SUMMON, world, summonItemDropChance, Items.PINK_TULIP);
        this.summoner = summoner;
    }

    public TulipSummonEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world, 0, Items.PINK_TULIP);
    }

    @Override
    public void tick() {
        super.tick();

        // Cooldown and then aoe slow burst
        if (!getWorld().isClient()){
            Box abilityBox = this.getBoundingBox().expand(ABILITY_RANGE);
            List<LivingEntity> livingEntitiesInRange = getWorld().getEntitiesByClass(LivingEntity.class, abilityBox, entity -> !(entity instanceof AbstractFloralSummonEntity));
            if (this.abilityTimeout <= 0 && !livingEntitiesInRange.isEmpty()){
                this.abilityTimeout = ABILITY_INTERVAL;

                for (LivingEntity entityInRange : livingEntitiesInRange){
                    if (entityInRange == summoner)
                        healSummoner();

                    ApplyStatusToEntity(entityInRange, StatusEffects.SLOWNESS, ABILITY_SLOW_DURATION, ABILITY_SLOW_AMPLIFIER);
                }
            }
            else
                --this.abilityTimeout;
        }
    }

    private void healSummoner(){
        RegistryEntry<StatusEffect> appropriateStatus = StatusEffects.REGENERATION;
        if (summoner.hasInvertedHealingAndHarm())
            appropriateStatus = StatusEffects.POISON;

        ApplyStatusToEntity(summoner, appropriateStatus, ABILITY_HEAL_DURATION, ABILITY_HEAL_AMPLIFIER);
    }

    private void ApplyStatusToEntity(LivingEntity target, RegistryEntry<StatusEffect> statusEffect, int duration, int amplifier){
        if (target.hasStatusEffect(statusEffect)){
            StatusEffectInstance statusInstance = target.getStatusEffect(statusEffect);

            int newAmplifier = statusInstance.getAmplifier() + amplifier;
            if (statusEffect == StatusEffects.SLOWNESS) // Slowness 6 already prevents movement
                newAmplifier = MathHelper.clamp(newAmplifier, 0, 6);

            //int newDuration = statusInstance.getDuration() + (duration / (statusInstance.getAmplifier() + 1));

            target.removeStatusEffect(statusEffect);
            target.addStatusEffect(new StatusEffectInstance(statusEffect, statusInstance.getDuration(), newAmplifier));
        }
        else
            target.addStatusEffect(new StatusEffectInstance(statusEffect, duration, amplifier - 1));
    }
}
