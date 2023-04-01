package ru.mrtenfan.MTFCore.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSavedData;
import ru.mrtenfan.MTFCore.MTFCoreMain;
import ru.mrtenfan.MTFCore.SavedExperience;
import ru.mrtenfan.MTFCore.SavedInventory;

public class CSaveInventory extends CommandBase {

	private final String
	NAME = "invsave",
	ALIAS1 = "is",
	ALIAS2 = "invsave",
	USAGE = "/invsave";
	
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
		sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Saving inventory..."));
		EntityPlayerMP player = (EntityPlayerMP)sender;
		
		if(isSlotsEmpty(player.inventory.armorInventory, player.inventory.mainInventory)) {
			sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Nothing to save."));
			return;
		}

		WorldSavedData wsd = new SavedInventory("Armor", player.inventory.armorInventory);
		((SavedInventory)wsd).writeToNBT(MTFCoreMain.nbttagmod);
		player.worldObj.mapStorage.setData("MTFC" + player.getDisplayName() + "A", wsd);
		wsd = new SavedInventory("Inventory", player.inventory.mainInventory);
		((SavedInventory)wsd).writeToNBT(MTFCoreMain.nbttagmod);
		player.worldObj.mapStorage.setData("MTFC" + player.getDisplayName() + "I", wsd);
		wsd = new SavedExperience("Experience", player.experienceLevel, player.experienceTotal);
		((SavedExperience)wsd).writeToNBT(MTFCoreMain.nbttagmod);
		player.worldObj.mapStorage.setData("MTFC" + player.getDisplayName() + "E", wsd);
		
		sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Saving done."));
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