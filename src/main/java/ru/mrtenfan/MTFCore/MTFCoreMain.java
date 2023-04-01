package ru.mrtenfan.MTFCore;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import ru.mrtenfan.MTFCore.proxy.CommonProxy;
import ru.mrtenfan.MTFCore.utils.EntityUtils;

@Mod(modid = MTFCoreMain.modID, name = MTFCoreMain.modName, version = MTFCoreMain.version)
public class MTFCoreMain {
	
	public static final String modID = "MTFCore";
	public static final String modName = "mr_ten_fan Core";
	public static final String version = "1.1.0.0";
	public static final String version_max = "1.1.0.999";
	
	public static final NBTTagCompound nbttagmod = new NBTTagCompound();

	public static final String version_group = "required-after:" + modID + "@[" + version + "," + version_max + "];";

	@SidedProxy(clientSide = "ru.mrtenfan.MTFCore.proxy.ClientProxy", serverSide = "ru.mrtenfan.MTFCore.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static String gameFolderPath;
	
	public static Item item;

    @Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {
    	gameFolderPath = e.getModConfigurationDirectory().getAbsoluteFile().getParent();
    	
    	item = new Item().setCreativeTab(CreativeTabs.tabAllSearch).setUnlocalizedName("test_item");
    	GameRegistry.registerItem(item, "test_item");
    	
		new ForgeEventHandler();
		MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
	}
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
    	EntityUtils.updateListEntities();
    	proxy.serverStarting(event);
    }
}