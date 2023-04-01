package ru.mrtenfan.MTFCore.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class CDimList extends CommandBase {

	private final String
	NAME = "dimlist",
	ALIAS1 = "dlist",
	ALIAS2 = "dimlist",
	USAGE = "/dimlist";
	
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
		sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Next dimensions are registered here:"));
		for(WorldServer w : DimensionManager.getWorlds()) {
			sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Name: " + w.provider.getDimensionName() + ", ID: " + w.provider.dimensionId + ";"));
		}
	}
    
    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add(ALIAS1);
        aliases.add(ALIAS2);
        return aliases;
    }
}