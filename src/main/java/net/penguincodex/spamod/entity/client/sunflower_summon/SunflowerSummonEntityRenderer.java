package net.penguincodex.spamod.entity.client.sunflower_summon;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.penguincodex.spamod.SPA;
import net.penguincodex.spamod.entity.custom.sunflower_summon.SunflowerSummonEntity;

public class SunflowerSummonEntityRenderer extends MobEntityRenderer<SunflowerSummonEntity, SunflowerSummonEntityModel<SunflowerSummonEntity>> {
    public SunflowerSummonEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SunflowerSummonEntityModel<>(context.getPart(SunflowerSummonEntityModel.SUNFLOWER_SUMMON)), 0.35f);
    }

    @Override
    public Identifier getTexture(SunflowerSummonEntity entity) {
        return Identifier.of(SPA.MOD_ID, "textures/entity/sunflower/sunflower_texture.png");
    }

    @Override
    public void render(SunflowerSummonEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
