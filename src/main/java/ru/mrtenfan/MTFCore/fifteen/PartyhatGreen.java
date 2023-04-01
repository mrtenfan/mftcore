package ru.mrtenfan.MTFCore.fifteen;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import ru.mrtenfan.MTFCore.ItemInit;
import ru.mrtenfan.MTFCore.MTFCoreMain;

public class PartyhatGreen extends ItemArmor {

	public PartyhatGreen(int id, int armorType) {
		super(ItemInit.PARTYHAT, id, armorType);
		if(MTFCoreMain.is15november)
			this.setCreativeTab(CreativeTabs.tabCombat);  
		else
			this.setCreativeTab(null);                                   
		this.setMaxStackSize(1);
		this.setUnlocalizedName("partyhatgreen");
		this.setTextureName("mtfc:partyhatgreen");
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		return "mtfc:textures/models/armors/partyhatgreen.png";
	}
	
	ModelBiped PartyHat = new ModelBiped();
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving,
			ItemStack itemStack, int armorSlot) {
		if(itemStack != null){
			if(itemStack.getItem() instanceof PartyhatGreen)
				PartyHat = new ModelPartyhat(-1.0F);  
			
			if(PartyHat != null){
				PartyHat.bipedHead.showModel = armorSlot == 0;
				PartyHat.bipedHeadwear.showModel = armorSlot == 10;
				PartyHat.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
				PartyHat.bipedRightArm.showModel = armorSlot == 1;
				PartyHat.bipedLeftArm.showModel = armorSlot == 1;
				PartyHat.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
				PartyHat.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
				PartyHat.isSneak = entityLiving.isSneaking();
				PartyHat.isRiding = entityLiving.isRiding();
				PartyHat.isChild = entityLiving.isChild();
				if(entityLiving instanceof EntityPlayer)
					PartyHat.aimedBow = ((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
			}
		}
		return PartyHat;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add((Object)EnumChatFormatting.DARK_GREEN + I18n.format("partyhat.info", new Object[0]));
	}
	
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		if (world.isRemote) {
			player.addChatMessage(new ChatComponentText((Object)EnumChatFormatting.DARK_GREEN + I18n.format("partyhat.create", new Object[0])));
		}
	}
}