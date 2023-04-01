package ru.mrtenfan.MTFCore.commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import ru.mrtenfan.MTFCore.MTFCoreMain;
import ru.mrtenfan.MTFCore.utils.EntityUtils;
import ru.mrtenfan.MTFCore.utils.EntityUtils.CustomEntity;

public class CEntityList extends CommandBase {
	
	private final String
	NAME = "entitylist",
	ALIAS1 = "entitylist",
	ALIAS2 = "enlist",
	ALIAS3 = "enl",
	ALIAS4 = "el",
	USAGE = "/entitylist";

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
		EntityUtils.updateListEntities();
		
		sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "File will generate in " + (Object)EnumChatFormatting.AQUA + "\"" + (Object)EnumChatFormatting.AQUA + MTFCoreMain.gameFolderPath + "\""));
		
    	File entitiesFile = new File(MTFCoreMain.gameFolderPath + "/registered_entities.txt");
    	
    	if(!entitiesFile.exists())
			try {
				entitiesFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	
    	String inputStr = "";
		inputStr += "File with all registered EntityLiving\n";
    	
    	for(CustomEntity en : EntityUtils.entities) {
    		inputStr += en.toString() + "\n";
    	}
    	
		try {
	    	FileOutputStream fileOut;
			fileOut = new FileOutputStream(entitiesFile);
			fileOut.write(inputStr.getBytes());
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sender.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.AQUA + "File generated successfully"));
	}
    
    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add(ALIAS1);
        aliases.add(ALIAS2);
        aliases.add(ALIAS3);
        aliases.add(ALIAS4);
        return aliases;
    }
}