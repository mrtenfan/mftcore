package ru.mrtenfan.MTFCore.proxy;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import ru.mrtenfan.MTFCore.commands.CDimList;
import ru.mrtenfan.MTFCore.commands.CDimTeleport;
import ru.mrtenfan.MTFCore.commands.CRestoreInventory;
import ru.mrtenfan.MTFCore.commands.CSaveInventory;

public class CommonProxy {
	
	public void serverStarting(FMLServerStartingEvent e) {
		e.registerServerCommand(new CDimTeleport());
		e.registerServerCommand(new CDimList());
		e.registerServerCommand(new CRestoreInventory());
		e.registerServerCommand(new CSaveInventory());
	}
}