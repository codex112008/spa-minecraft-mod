package net.penguincodex.spamod.entity.client.sunflower_summon.sun_beam;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.penguincodex.spamod.SPA;
import net.penguincodex.spamod.entity.custom.SunBeamProjectileEntity;

public class SunBeamProjectileEntityModel extends EntityModel<SunBeamProjectileEntity> {
    public static final EntityModelLayer SUN_BEAM = new EntityModelLayer(Identifier.of(SPA.MOD_ID, "sun_beam"), "main");

    private final ModelPart SunBeam;
    private final ModelPart Body1;
    private final ModelPart Body2;

    public SunBeamProjectileEntityModel(ModelPart root) {
        this.SunBeam = root.getChild("SunBeam");
        this.Body1 = this.SunBeam.getChild("Body1");
        this.Body2 = this.SunBeam.getChild("Body2");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData SunBeam = modelPartData.addChild("SunBeam", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 3.0F));

        ModelPartData Body1 = SunBeam.addChild("Body1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Body2 = SunBeam.addChild("Body2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 8, 8);
    }

    @Override
    public void setAngles(SunBeamProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        SunBeam.render(matrices, vertexConsumer, light, overlay, color);
    }
}
