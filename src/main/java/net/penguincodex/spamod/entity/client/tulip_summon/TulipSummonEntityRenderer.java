package net.penguincodex.spamod.entity.client.tulip_summon;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.penguincodex.spamod.SPA;
import net.penguincodex.spamod.entity.client.sunflower_summon.SunflowerSummonEntityModel;
import net.penguincodex.spamod.entity.custom.sunflower_summon.SunflowerSummonEntity;
import net.penguincodex.spamod.entity.custom.tulip_summon.TulipSummonEntity;

public class TulipSummonEntityRenderer extends MobEntityRenderer<TulipSummonEntity, TulipSummonEntityModel<TulipSummonEntity>> {
    public TulipSummonEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new TulipSummonEntityModel<>(context.getPart(TulipSummonEntityModel.TULIP_SUMMON)), 0.35f);
    }

    @Override
    public Identifier getTexture(TulipSummonEntity entity) {
        return Identifier.of(SPA.MOD_ID, "textures/entity/tulip/tulip_texture.png");
    }

    @Override
    public void render(TulipSummonEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
