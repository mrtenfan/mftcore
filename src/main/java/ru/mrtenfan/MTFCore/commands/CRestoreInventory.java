package ru.mrtenfan.MTFCore.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import ru.mrtenfan.MTFCore.MTFCoreMain;
import ru.mrtenfan.MTFCore.SavedExperience;
import ru.mrtenfan.MTFCore.SavedInventory;

public class CRestoreInventory extends CommandBase {

	private final String
	NAME = "invrest",
	ALIAS1 = "ir",
	ALIAS2 = "invrest",
	USAGE = "/invrest";
	
	@Override
	public String getCommandName() {
		return NAME;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return USAGE;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Restoring inventory..."));
		EntityPlayer player = (EntityPlayer)sender;
		if(isInventoryEmpty(player)) {
			ItemStack[] aslots = new ItemStack[4];
			ItemStack[] islots = new ItemStack[36];
			int explvl = 0;
			int expbar = 0;
			MapStorage mp = player.worldObj.mapStorage;
			SavedInventory adata = (SavedInventory) mp.loadData(SavedInventory.class, "MTFC" + player.getDisplayName() + "A");
			if(adata != null) {
				adata.readFromNBT(MTFCoreMain.nbttagmod);
				aslots = adata.getStacks();
			}
			SavedInventory idata = (SavedInventory) mp.loadData(SavedInventory.class, "MTFC" + player.getDisplayName() + "I");
			if(idata != null) {
				idata.readFromNBT(MTFCoreMain.nbttagmod);
				islots = idata.getStacks();
			}
			SavedExperience edata = (SavedExperience) mp.loadData(SavedExperience.class, "MTFC" + player.getDisplayName() + "E");
			if(edata != null) {
				edata.readFromNBT(MTFCoreMain.nbttagmod);
				explvl = edata.getLevels();
				expbar = edata.getBarExp();
				System.out.println(explvl + ":" + expbar);
			}
			
			boolean flag1 = isSlotsEmpty(aslots, islots);
			boolean flag2 = explvl <= 0;
			boolean flag3 = expbar <= 0;
			
			if(flag1 && flag2 && flag3) {
				sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Nothing to restore."));
				return;
			}
			
			if(!flag1) {
				player.inventory.armorInventory = aslots;
				player.inventory.mainInventory = islots;
			}
			if(!flag2)
				player.addExperienceLevel(explvl);
			if(!flag3)
				player.addExperience(expbar);
			
			clearSlots(player);
			sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Restoring done."));
		} else
			sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.RED + "Restoring can't complete.Inventory isn't empty."));	
	}

	private boolean isSlotsEmpty(ItemStack[] aslots, ItemStack[] islots) {
    	for(ItemStack is : aslots)
    		if(is != null)
    			return false;
    	for(ItemStack is : islots)
    		if(is != null)
    			return false;
		return true;
	}

	private void clearSlots(EntityPlayer player) {
    	MapStorage mp = player.worldObj.mapStorage;
    	WorldSavedData wsd = new SavedInventory("Armor", new ItemStack[4]);
    	((SavedInventory)wsd).writeToNBT(MTFCoreMain.nbttagmod);
    	mp.setData("MTFC" + player.getDisplayName() + "A", wsd);
    	wsd = new SavedInventory("Inventory", new ItemStack[36]);
    	((SavedInventory)wsd).writeToNBT(MTFCoreMain.nbttagmod);
    	mp.setData("MTFC" + player.getDisplayName() + "I", wsd);
    	wsd = new SavedExperience("Experience", 0, 0);
    	((SavedExperience)wsd).writeToNBT(MTFCoreMain.nbttagmod);
    	mp.setData("MTFC" + player.getDisplayName() + "E", wsd);
	}

	public boolean isInventoryEmpty(EntityPlayer player) {
    	ItemStack[] aslots = player.inventory.armorInventory;
    	ItemStack[] islots = player.inventory.mainInventory;
    	
    	for(ItemStack is : aslots)
    		if(is != null) return false;
    	for(ItemStack is : islots)
    		if(is != null) return false;
    	
		return true;
	}
    
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return sender instanceof EntityPlayer ? MinecraftServer.getServer().getConfigurationManager().func_152596_g(((EntityPlayer) sender).getGameProfile()) : false;
    }

	@Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add(ALIAS1);
        aliases.add(ALIAS2);
        return aliases;
    }
}