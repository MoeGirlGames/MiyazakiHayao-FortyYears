// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

package games.moegirl.fortyyears.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import games.moegirl.fortyyears.entity.EntityFairydust;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class ModelFairydust extends EntityModel<EntityFairydust> {

    private final ModelRenderer head;
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightLeg;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;

    private float swimAnimation;

    public ModelFairydust() {
        textureWidth = 16;
        textureHeight = 8;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.5F, 16.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-2.5F, -2.0F, -2.0F, 5.0F, 4.0F, 4.0F, 0.0F, false);

        leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(-2.0F, 18.0F, 0.0F);
        leftLeg.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);

        rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(2.0F, 18.0F, 0.0F);
        rightLeg.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, true);
        rightLeg.mirror = true;

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(-2.0F, 17.0F, 0.0F);
        leftArm.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(3.0F, 17.0F, 0.0F);
        rightArm.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, true);
        rightArm.mirror = true;
    }

    @Override
    public void setLivingAnimations(EntityFairydust entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        swimAnimation = entityIn.getSwingProgress(partialTick);
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void setRotationAngles(EntityFairydust entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        if (this.swimAnimation > 0.0F) {
            head.rotateAngleX = rotLerpRad(swimAnimation, head.rotateAngleX, headPitch * ((float)Math.PI / 180F));
        } else {
            head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        }

//        head.rotateAngleY = 0.0F;
//        rightArm.rotationPointZ = 0.0F;
//        rightArm.rotationPointX = -5.0F;
//        leftArm.rotationPointZ = 0.0F;
//        leftArm.rotationPointX = 5.0F;

        rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        rightArm.rotateAngleY = 0.0F;
        rightArm.rotateAngleZ = 0.0F;
        leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        leftArm.rotateAngleZ = 0.0F;
        leftArm.rotateAngleY = 0.0F;
        rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        rightLeg.rotateAngleY = 0.0F;
        rightLeg.rotateAngleZ = 0.0F;
        leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        leftLeg.rotateAngleY = 0.0F;
        leftLeg.rotateAngleZ = 0.0F;

//        rightLeg.rotationPointZ = 0.1F;
//        leftLeg.rotationPointZ = 0.1F;
//        rightLeg.rotationPointY = 12.0F;
//        leftLeg.rotationPointY = 12.0F;
//        head.rotationPointY = 0.0F;
//        body.rotationPointY = 0.0F;
//        leftArm.rotationPointY = 2.0F;
//        rightArm.rotationPointY = 2.0F;

        ModelHelper.func_239101_a_(rightArm, leftArm, ageInTicks);
//        if (this.swimAnimation > 0.0F) {
//            float f1 = limbSwing % 26.0F;
//            float f2 = this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
//            float f3 = this.swimAnimation;
//            if (f1 < 14.0F) {
//                leftArm.rotateAngleX = this.rotLerpRad(f3, leftArm.rotateAngleX, 0.0F);
//                rightArm.rotateAngleX = MathHelper.lerp(f2, rightArm.rotateAngleX, 0.0F);
//                leftArm.rotateAngleY = this.rotLerpRad(f3, leftArm.rotateAngleY, (float)Math.PI);
//                rightArm.rotateAngleY = MathHelper.lerp(f2, rightArm.rotateAngleY, (float)Math.PI);
//                leftArm.rotateAngleZ = this.rotLerpRad(f3, leftArm.rotateAngleZ, (float)Math.PI + 1.8707964F * this.getArmAngleSq(f1) / this.getArmAngleSq(14.0F));
//                rightArm.rotateAngleZ = MathHelper.lerp(f2, rightArm.rotateAngleZ, (float)Math.PI - 1.8707964F * this.getArmAngleSq(f1) / this.getArmAngleSq(14.0F));
//            } else if (f1 >= 14.0F && f1 < 22.0F) {
//                float f6 = (f1 - 14.0F) / 8.0F;
//                leftArm.rotateAngleX = this.rotLerpRad(f3, leftArm.rotateAngleX, ((float)Math.PI / 2F) * f6);
//                rightArm.rotateAngleX = MathHelper.lerp(f2, rightArm.rotateAngleX, ((float)Math.PI / 2F) * f6);
//                leftArm.rotateAngleY = this.rotLerpRad(f3, leftArm.rotateAngleY, (float)Math.PI);
//                rightArm.rotateAngleY = MathHelper.lerp(f2, rightArm.rotateAngleY, (float)Math.PI);
//                leftArm.rotateAngleZ = this.rotLerpRad(f3, leftArm.rotateAngleZ, 5.012389F - 1.8707964F * f6);
//                rightArm.rotateAngleZ = MathHelper.lerp(f2, rightArm.rotateAngleZ, 1.2707963F + 1.8707964F * f6);
//            } else if (f1 >= 22.0F && f1 < 26.0F) {
//                float f4 = (f1 - 22.0F) / 4.0F;
//                leftArm.rotateAngleX = this.rotLerpRad(f3, leftArm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
//                rightArm.rotateAngleX = MathHelper.lerp(f2, rightArm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
//                leftArm.rotateAngleY = this.rotLerpRad(f3, leftArm.rotateAngleY, (float)Math.PI);
//                rightArm.rotateAngleY = MathHelper.lerp(f2, rightArm.rotateAngleY, (float)Math.PI);
//                leftArm.rotateAngleZ = this.rotLerpRad(f3, leftArm.rotateAngleZ, (float)Math.PI);
//                rightArm.rotateAngleZ = MathHelper.lerp(f2, rightArm.rotateAngleZ, (float)Math.PI);
//            }
//
//            leftLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, leftLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float)Math.PI));
//            rightLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, rightLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
//        }
    }

    protected float rotLerpRad(float angleIn, float maxAngleIn, float mulIn) {
        float f = (mulIn - maxAngleIn) % ((float)Math.PI * 2F);
        if (f < -(float)Math.PI) {
            f += ((float)Math.PI * 2F);
        }

        if (f >= (float)Math.PI) {
            f -= ((float)Math.PI * 2F);
        }

        return maxAngleIn + angleIn * f;
    }

    private float getArmAngleSq(float limbSwing) {
        return -65.0F * limbSwing + limbSwing * limbSwing;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}