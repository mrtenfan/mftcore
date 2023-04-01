package ru.mrtenfan.MTFCore;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class ForgeEventHandler {

	@SubscribeEvent
	public void onDeath(LivingDeathEvent e) {
		if(e.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)e.entityLiving;
			WorldSavedData wsd = new SavedInventory("Armor", player.inventory.armorInventory);
			((SavedInventory)wsd).writeToNBT(MTFCoreMain.nbttagmod);
			player.worldObj.mapStorage.setData("MTFC" + player.getDisplayName() + "A", wsd);
			wsd = new SavedInventory("Inventory", player.inventory.mainInventory);
			((SavedInventory)wsd).writeToNBT(MTFCoreMain.nbttagmod);
			player.worldObj.mapStorage.setData("MTFC" + player.getDisplayName() + "I", wsd);
			wsd = new SavedExperience("Experience", player.experienceLevel, player.experienceTotal);
			((SavedExperience)wsd).writeToNBT(MTFCoreMain.nbttagmod);
			player.worldObj.mapStorage.setData("MTFC" + player.getDisplayName() + "E", wsd);
		}
	}
}