package net.penguincodex.spamod.entity.client.sunflower_summon;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.penguincodex.spamod.SPA;
import net.penguincodex.spamod.entity.custom.sunflower_summon.SunflowerSummonEntity;

public class SunflowerSummonEntityModel<T extends SunflowerSummonEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer SUNFLOWER_SUMMON = new EntityModelLayer(Identifier.of(SPA.MOD_ID, "sunflower_summon"), "main");

    private final ModelPart Sunflower;
    private final ModelPart Flower;
    private final ModelPart FlowerIdle;
    private final ModelPart FlowerFire0;
    private final ModelPart FlowerFire1;
    private final ModelPart FlowerFire2;
    private final ModelPart FlowerFire3;
    private final ModelPart Base;

    public SunflowerSummonEntityModel(ModelPart root) {
        this.Sunflower = root.getChild("Sunflower");
        this.Flower = this.Sunflower.getChild("Flower");
        this.FlowerIdle = this.Flower.getChild("FlowerIdle");
        this.FlowerFire0 = this.Flower.getChild("FlowerFire0");
        this.FlowerFire1 = this.Flower.getChild("FlowerFire1");
        this.FlowerFire2 = this.Flower.getChild("FlowerFire2");
        this.FlowerFire3 = this.Flower.getChild("FlowerFire3");
        this.Base = this.Sunflower.getChild("Base");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Sunflower = modelPartData.addChild("Sunflower", ModelPartBuilder.create(), ModelTransform.of(0.0F, 17.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData Stem_r1 = Sunflower.addChild("Stem_r1", ModelPartBuilder.create().uv(20, 0).cuboid(-1.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 7.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData Flower = Sunflower.addChild("Flower", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData FlowerIdle = Flower.addChild("FlowerIdle", ModelPartBuilder.create().uv(0, 9).cuboid(-2.0F, -2.0F, -4.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(20, 0).cuboid(-3.0F, -1.0F, -5.0F, 8.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.1901F, -0.9578F, 1.0F, 0.0F, 0.0F, 1.309F));

        ModelPartData FlowerFire0 = Flower.addChild("FlowerFire0", ModelPartBuilder.create().uv(0, 18).cuboid(-2.0F, -2.0F, -4.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(20, 0).cuboid(-3.0F, -1.0F, -5.0F, 8.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.1901F, -0.9578F, 1.0F, 0.0F, 0.0F, 1.309F));

        ModelPartData FlowerFire1 = Flower.addChild("FlowerFire1", ModelPartBuilder.create().uv(0, 27).cuboid(-2.0F, -2.0F, -4.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(20, 0).cuboid(-3.0F, -1.0F, -5.0F, 8.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.1901F, -0.9578F, 1.0F, 0.0F, 0.0F, 1.309F));

        ModelPartData FlowerFire2 = Flower.addChild("FlowerFire2", ModelPartBuilder.create().uv(0, 36).cuboid(-2.0F, -2.0F, -4.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(20, 0).cuboid(-3.0F, -1.0F, -5.0F, 8.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.1901F, -0.9578F, 1.0F, 0.0F, 0.0F, 1.309F));

        ModelPartData FlowerFire3 = Flower.addChild("FlowerFire3", ModelPartBuilder.create().uv(0, 45).cuboid(-2.0F, -2.0F, -4.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(20, 0).cuboid(-3.0F, -1.0F, -5.0F, 8.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.1901F, -0.9578F, 1.0F, 0.0F, 0.0F, 1.309F));

        ModelPartData Base = Sunflower.addChild("Base", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -0.5F, -4.0F, 2.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 6.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube_r1 = Base.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -0.5F, -4.0F, 2.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(SunflowerSummonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.updateAnimation(entity.idleAnimationState, SunflowerSummonEntityAnimations.IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.fireAnimationState, SunflowerSummonEntityAnimations.FIRE, ageInTicks, 1f);
        this.updateAnimation(entity.windDownAnimationState, SunflowerSummonEntityAnimations.WIND_DOWN, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.Flower.yaw = headYaw * 0.017453292F;
        this.Flower.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        Sunflower.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return Sunflower;
    }
}
