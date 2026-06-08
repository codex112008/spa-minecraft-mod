package net.penguincodex.spamod.entity.client.tulip_summon;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.penguincodex.spamod.SPA;
import net.penguincodex.spamod.entity.custom.tulip_summon.TulipSummonEntity;

// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class TulipSummonEntityModel<T extends TulipSummonEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer TULIP_SUMMON = new EntityModelLayer(Identifier.of(SPA.MOD_ID, "tulip_summon"), "main");

    private final ModelPart Tulip;
    private final ModelPart Stem;
    private final ModelPart Leaf1;
    private final ModelPart Leaf4;
    private final ModelPart Leaf2;
    private final ModelPart Leaf3;
    private final ModelPart Base;
    private final ModelPart Flower;

    public TulipSummonEntityModel(ModelPart root) {
        this.Tulip = root.getChild("Tulip");
        this.Stem = this.Tulip.getChild("Stem");
        this.Leaf1 = this.Stem.getChild("Leaf1");
        this.Leaf4 = this.Stem.getChild("Leaf4");
        this.Leaf2 = this.Stem.getChild("Leaf2");
        this.Leaf3 = this.Stem.getChild("Leaf3");
        this.Base = this.Tulip.getChild("Base");
        this.Flower = this.Tulip.getChild("Flower");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Tulip = modelPartData.addChild("Tulip", ModelPartBuilder.create(), ModelTransform.of(0.0F, 17.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData Stem = Tulip.addChild("Stem", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 7.0F, 0.0F));

        ModelPartData Stem_r1 = Stem.addChild("Stem_r1", ModelPartBuilder.create().uv(12, 11).cuboid(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData Leaf1 = Stem.addChild("Leaf1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -4.0F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 2).cuboid(-2.0F, -3.0F, -0.5F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 7).cuboid(0.0F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -4.0F, 0.0F));

        ModelPartData Leaf4 = Stem.addChild("Leaf4", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -4.0F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 2).cuboid(-2.0F, -3.0F, -0.5F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 7).cuboid(0.0F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -4.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData Leaf2 = Stem.addChild("Leaf2", ModelPartBuilder.create().uv(6, 0).cuboid(0.0F, -3.0F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(6, 2).cuboid(-2.0F, -2.0F, -0.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(5, 6).cuboid(0.0F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, 2.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData Leaf3 = Stem.addChild("Leaf3", ModelPartBuilder.create().uv(6, 0).cuboid(0.0F, -3.0F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(6, 2).cuboid(-2.0F, -2.0F, -0.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(5, 6).cuboid(0.0F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData Base = Tulip.addChild("Base", ModelPartBuilder.create().uv(0, 13).cuboid(-1.0F, -0.5F, -4.0F, 2.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 6.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube_r1 = Base.addChild("cube_r1", ModelPartBuilder.create().uv(0, 13).cuboid(-1.0F, -0.5F, -4.0F, 2.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData Flower = Tulip.addChild("Flower", ModelPartBuilder.create().uv(0, 11).cuboid(1.0F, -0.25F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-1.0F, -1.25F, -2.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 11).cuboid(-2.0F, -0.25F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-1.0F, -1.25F, 1.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.0607F, -3.75F, -0.0607F, 0.0F, -0.7854F, 0.0F));
        return TexturedModelData.of(modelData, 22, 22);
    }

    @Override
    public void setAngles(TulipSummonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        Tulip.render(matrices, vertexConsumer, light, overlay, color);
    }

    public ModelPart getPart() {
        return Tulip;
    }
}