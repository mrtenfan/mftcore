package ru.mrtenfan.MTFCore;

import java.text.SimpleDateFormat;
import java.util.Date;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import ru.mrtenfan.MTFCore.proxy.CommonProxy;

@Mod(modid = MTFCoreMain.modID, name = MTFCoreMain.modName, version = MTFCoreMain.version)
public class MTFCoreMain {
	
	public static final String modID = "MTFCore";
	public static final String modName = "mr_ten_fan Core";
	public static final String version = "1.0.5.2";
	public static final String version_max = "1.0.5.999";
	
	public static final NBTTagCompound nbttagmod = new NBTTagCompound();

	public static final String version_group = "required-after:" + modID + "@[" + version + "," + version_max + ");";
	public static boolean is15november;

	@SidedProxy(clientSide = "ru.mrtenfan.MTFCore.proxy.ClientProxy", serverSide = "ru.mrtenfan.MTFCore.proxy.CommonProxy")
	public static CommonProxy proxy;

    @Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {
    	is15november = is15November();
    	System.out.println(is15november);
    	ItemInit.preInit();
    	
		new ForgeEventHandler();
		MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
	}

	@Mod.EventHandler
    public void init(FMLInitializationEvent e) {
		ItemInit.init();
    	if(is15november)
		ItemInit.recipes();
    }
	
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
    	proxy.serverStarting(event);
    }

	private boolean is15November() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("MM:dd");
        String[] date = dateFormater.format(new Date()).split(":");
        if (date.length == 2 && date[0].equalsIgnoreCase("11") && date[1].equalsIgnoreCase("15"))
            return true;
        else
            return false;
	}
}