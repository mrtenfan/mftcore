package ru.mrtenfan.MTFCore.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.DimensionManager;

@SuppressWarnings("rawtypes")
public class CDimTeleport extends CommandBase {

	private final String
	NAME = "dimtp",
	ALIAS1 = "dtp",
	ALIAS2 = "dimtp",
	USAGE = "/dimtp <dimension_id> OR /dimtp [player] <dimension_id>";
	
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
		if(args.length > 0 && args.length < 3) {
			EntityPlayerMP player;
			int dimID;
			if(args.length != 1) {
				player = getPlayer(sender, args[0]);
				dimID = Integer.valueOf(args[1]);
			} else {
				player = ((EntityPlayerMP) sender);
				dimID = Integer.valueOf(args[0]);
			}
			if(dimID == player.dimension) {
				sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.RED + (player.isEntityEqual(((EntityPlayer)sender)) ? "You" : player.getDisplayName()) + " is already in this dimension"));
				return;
			}
			String dimName = DimensionManager.getWorld(dimID).provider.getDimensionName();
			sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Teleporting in dimension: " + dimName));
			player.mcServer.getConfigurationManager().transferPlayerToDimension(player, dimID, new FreeTeleporter(DimensionManager.getWorld(dimID)));
			sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "Teleportation complete."));
		} else
			sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.RED + USAGE));
	}
    
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return sender instanceof EntityPlayer ? MinecraftServer.getServer().getConfigurationManager().func_152596_g(((EntityPlayer) sender).getGameProfile()) : false;
    }
    
	public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
    }
    
    @Override
    public List<String> getCommandAliases() {
  
        List<String> aliases = new ArrayList<String>();
        aliases.add(ALIAS1);
        aliases.add(ALIAS2);
        return aliases;
    }
}