package ru.mrtenfan.MTFCore;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import ru.mrtenfan.MTFCore.fifteen.PartyhatBlue;
import ru.mrtenfan.MTFCore.fifteen.PartyhatCyan;
import ru.mrtenfan.MTFCore.fifteen.PartyhatGreen;

public class ItemInit {
	
	public static final ArmorMaterial PARTYHAT = EnumHelper.addArmorMaterial("PARTYHAT", 3, new int[] {0}, 0);

    public static Item Partyhatcyan;
    public static Item Partyhatgreen;
    public static Item Partyhatblue;
	
	public static void preInit() {
        Partyhatcyan = new PartyhatCyan(0, 0);
        Partyhatgreen = new PartyhatGreen(0, 0);
        Partyhatblue = new PartyhatBlue(0, 0);
	}

	public static void init() {
	    GameRegistry.registerItem(Partyhatcyan, "item.partyhatcyan");
	    GameRegistry.registerItem(Partyhatgreen, "item.partyhatgreen");
	    GameRegistry.registerItem(Partyhatblue, "item.partyhatblue");
	}

	public static void recipes() {
        GameRegistry.addRecipe(new ItemStack(Partyhatcyan, 1),
        		  new Object[]{
        		    " X ", "XYX",
        		    ('X'), Items.paper,
        		    ('Y'), new ItemStack(Items.dye, 1, 6)
        		  }
        		);
           GameRegistry.addRecipe(new ItemStack(Partyhatgreen, 1),
         		  new Object[]{
         		    " X ", "XYX",
         		    ('X'), Items.paper,
         		    ('Y'), new ItemStack(Items.dye, 1, 10)
         		  }
         		);
           GameRegistry.addRecipe(new ItemStack(Partyhatblue, 1),
         		  new Object[]{
         		    " X ", "XYX", " B ",
         		    ('X'), Items.paper,
         		    ('Y'), new ItemStack(Items.dye, 1, 4),
         		    ('B'), new ItemStack(Items.dye, 1, 11)
         		  }
         		);
	}
}