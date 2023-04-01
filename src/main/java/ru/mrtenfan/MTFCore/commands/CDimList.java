package ru.mrtenfan.MTFCore.commands;

import java.util.ArrayList;
import java.util.Comparator;
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
	ALIAS3 = "dl",
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
		int pageNumber = (args.length == 1 ? Integer.valueOf(args[0]) : 1);
		pageNumber--;
		int numberOfWorlds = DimensionManager.getWorlds().length;
		int maxPageNumber = numberOfWorlds / 6;
		if(pageNumber < 0 || pageNumber > maxPageNumber) {
			sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.RED + "The number you have entered is too " + (pageNumber < 0 ? "small, it must be at least 1" : "big, it must be at most " + (maxPageNumber + 1))));
			return;
		}
		
		ArrayList<WorldServer> dimensions = new ArrayList<WorldServer>();
		for(WorldServer w : DimensionManager.getWorlds())
			dimensions.add(w);
		dimensions.sort(new Comparator<WorldServer>() {

			@Override
			public int compare(WorldServer w1, WorldServer w2) {
				return (w1.provider.dimensionId < w2.provider.dimensionId ? -1 : 1);
			}
		});
		sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Next dimensions are registered here:"));
		int numberOfRepeating = (numberOfWorlds - (pageNumber * 6) < 6 ? numberOfWorlds - (pageNumber * 6) : 6);
		for(int i = 0; i < numberOfRepeating; i++) {
			WorldServer w = dimensions.get(i + (pageNumber * 6));
			sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Name: " + w.provider.getDimensionName() + ", ID: " + w.provider.dimensionId + ";"));
		}
		if(maxPageNumber > 0)
		sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Page number " + (pageNumber + 1) + "/" + (maxPageNumber + 1)));
	}
    
    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add(ALIAS1);
        aliases.add(ALIAS2);
        aliases.add(ALIAS3);
        return aliases;
    }
}