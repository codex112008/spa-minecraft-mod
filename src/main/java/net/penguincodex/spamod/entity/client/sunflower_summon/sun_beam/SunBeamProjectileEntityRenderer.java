package net.penguincodex.spamod.entity.client.sunflower_summon.sun_beam;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.penguincodex.spamod.SPA;
import net.penguincodex.spamod.entity.custom.SunBeamProjectileEntity;

public class SunBeamProjectileEntityRenderer extends EntityRenderer<SunBeamProjectileEntity> {
    protected Identifier identifier = Identifier.of(SPA.MOD_ID, "textures/entity/sunflower/sun_beam_texture.png");
    protected SunBeamProjectileEntityModel model;

    public SunBeamProjectileEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new SunBeamProjectileEntityModel(ctx.getPart(SunBeamProjectileEntityModel.SUN_BEAM));
    }

    @Override
    public Identifier getTexture(SunBeamProjectileEntity entity) {
        return identifier;
    }

    @Override
    public void render(SunBeamProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw())));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.lerp(tickDelta, -entity.prevPitch, -entity.getPitch())));

        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers,
                this.model.getLayer(identifier), false, false);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
