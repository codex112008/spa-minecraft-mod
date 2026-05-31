package net.penguincodex.spamod.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.penguincodex.spamod.SPA;
import net.penguincodex.spamod.entity.client.sunflower_summon.SunflowerSummonEntityModel;
import net.penguincodex.spamod.entity.client.sunflower_summon.SunflowerSummonEntityRenderer;
import net.penguincodex.spamod.entity.client.sunflower_summon.sun_beam.SunBeamProjectileEntityModel;
import net.penguincodex.spamod.entity.client.sunflower_summon.sun_beam.SunBeamProjectileEntityRenderer;
import net.penguincodex.spamod.entity.custom.SunBeamProjectileEntity;
import net.penguincodex.spamod.entity.custom.SunflowerSummonEntity;

public class ModEntities {
    public static final EntityType<SunflowerSummonEntity> SUNFLOWER_SUMMON = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(SPA.MOD_ID, "sunflower_summon"),
            EntityType.Builder.<SunflowerSummonEntity>create(SunflowerSummonEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 0.7f).eyeHeight(0.5f).build());

    public static final EntityType<SunBeamProjectileEntity> SUN_BEAM = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(SPA.MOD_ID, "sun_beam"),
            EntityType.Builder.<SunBeamProjectileEntity>create(SunBeamProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.4f, 0.2f).build());

    public static void registerModEntities(){
        SPA.LOGGER.info("Registering Mod Entities for" + SPA.MOD_ID);
    }

    public static void registerModEntitiesAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.SUNFLOWER_SUMMON, SunflowerSummonEntity.createAttributes());
    }

    public static void registerModEntitiesModelLayers(){
        EntityModelLayerRegistry.registerModelLayer(SunflowerSummonEntityModel.SUNFLOWER_SUMMON, SunflowerSummonEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SunBeamProjectileEntityModel.SUN_BEAM, SunBeamProjectileEntityModel::getTexturedModelData);
    }

    public static void registerModEntitiesRenderers(){
        EntityRendererRegistry.register(ModEntities.SUNFLOWER_SUMMON, SunflowerSummonEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SUN_BEAM, SunBeamProjectileEntityRenderer::new);
    }
}
