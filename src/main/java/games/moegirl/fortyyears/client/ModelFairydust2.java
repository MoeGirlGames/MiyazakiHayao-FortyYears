// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

package games.moegirl.fortyyears.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import games.moegirl.fortyyears.entity.EntityFairydust;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelFairydust2 extends EntityModel<EntityFairydust> {

	private final ModelRenderer head;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;

	public ModelFairydust2() {
		textureWidth = 16;
		textureHeight = 8;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-2.0F, -10.0F, -2.0F, 5.0F, 4.0F, 4.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
		leftLeg.setTextureOffset(0, 0).addBox(-2.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
		rightLeg.setTextureOffset(0, 0).addBox(2.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		rightLeg.mirror = true;

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(0.0F, 24.0F, 0.0F);
		leftArm.setTextureOffset(0, 0).addBox(-3.0F, -7.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(0.0F, 24.0F, 0.0F);
		rightArm.setTextureOffset(0, 0).addBox(3.0F, -7.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		rightArm.mirror = true;
	}

	@Override
	public void setRotationAngles(EntityFairydust entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}