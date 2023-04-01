package ru.mrtenfan.MTFCore.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class CFill extends CommandBase {

	private final String
	NAME = "fill",
	ALIAS = "fill",
	USAGE = "/fill <0x> <0y> <0z> <x> <y> <z> <block>";
	
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
		int x0 = Integer.valueOf(args[0]), y0 = Integer.valueOf(args[1]), z0 = Integer.valueOf(args[2]),
				x = Integer.valueOf(args[3]), y = Integer.valueOf(args[4]), z = Integer.valueOf(args[5]);
        Block block = CommandBase.getBlockByText(sender, args[6]);
        if(block == null) {
    		sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.RED + "Block is null"));
        	return;
        }
        @SuppressWarnings("unused")
		World world = sender.getEntityWorld();
        
        int dx = x - x0, dy = y - y0, dz = z - z0;
        if(dx == 0 && dy == 0 && dz == 0) {
    		sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.RED + "Δx, Δy, Δz equals zero!"));
    		return;
        }
        
        for(int iy = 0; iy < dy; iy++) {
        	
        }
	}
    
    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add(ALIAS);
        return aliases;
    }
}