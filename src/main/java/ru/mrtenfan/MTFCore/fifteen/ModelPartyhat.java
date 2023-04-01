// Date: 02.11.2019 15:47:52

package ru.mrtenfan.MTFCore.fifteen;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPartyhat extends ModelBiped {
	//fields
	ModelRenderer cube1;
	ModelRenderer Cube2;
	ModelRenderer Cube3;
	ModelRenderer Cube4;
	ModelRenderer Cube5;

	public ModelPartyhat(float f) {
		textureWidth = 64;
		textureHeight = 32;

		cube1 = new ModelRenderer(this, 0, 0);
		cube1.addBox(-5F, -9F, -5F, 10, 2, 10);
		cube1.setRotationPoint(0F, 0F, 0F);
		cube1.setTextureSize(64, 32);
		cube1.mirror = true;
		setRotation(cube1, 0F, 0F, 0F);
		Cube2 = new ModelRenderer(this, 0, 14);
		Cube2.addBox(-4F, -12F, -4F, 8, 3, 8);
		Cube2.setRotationPoint(0F, 0F, 0F);
		Cube2.setTextureSize(64, 32);
		Cube2.mirror = true;
		setRotation(Cube2, 0F, 0F, 0F);
		Cube3 = new ModelRenderer(this, 40, 0);
		Cube3.addBox(-3F, -14F, -3F, 6, 2, 6);
		Cube3.setRotationPoint(0F, 0F, 0F);
		Cube3.setTextureSize(64, 32);
		Cube3.mirror = true;
		setRotation(Cube3, 0F, 0F, 0F);
		Cube4 = new ModelRenderer(this, 34, 14);
		Cube4.addBox(-2F, -15F, -2F, 4, 1, 4);
		Cube4.setRotationPoint(0F, 0F, 0F);
		Cube4.setTextureSize(64, 32);
		Cube4.mirror = true;
		setRotation(Cube4, 0F, 0F, 0F);
		Cube5 = new ModelRenderer(this, 0, 29);
		Cube5.addBox(-1F, -16F, -1F, 2, 1, 2);
		Cube5.setRotationPoint(0F, 0F, 0F);
		Cube5.setTextureSize(64, 32);
		Cube5.mirror = true;
		setRotation(Cube5, 0F, 0F, 0F);

		this.bipedHead.addChild(cube1);
		this.bipedHead.addChild(Cube2);
		this.bipedHead.addChild(Cube3);
		this.bipedHead.addChild(Cube4);
		this.bipedHead.addChild(Cube5);
		this.bipedHead.cubeList.clear();
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}