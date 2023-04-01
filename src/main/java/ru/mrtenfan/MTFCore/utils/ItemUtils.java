package ru.mrtenfan.MTFCore.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ru.mrtenfan.MTFCore.Debuging;

public class ItemUtils {

	/**Returns is items equal, also check his tags
	 * @param item1 first item
	 * @param item2 second item
	 * @param isOreDict whether to check the ore dictionary
	 * @param checkTags whether to check the item tags
	 * @return boolean is items equal
	 */
	public static boolean isItemEqual(Object item1, Object item2, boolean isOreDict, boolean checkTags) {
		if(item1 == null || item2 == null) return false;
		
		ItemStack item1is = toItemStack(item1), item2is = toItemStack(item2);
		
		return isItemEqual(item1is, item2is, isOreDict) && (ItemStack.areItemStackTagsEqual(item1is, item2is) && checkTags);
	}
	
	private static boolean isItemEqual(ItemStack item1is, ItemStack item2is, boolean isOreDict) {
		if(item1is.getItem() == item2is.getItem() && (item1is.getItemDamage() == 32767 || item1is.getItemDamage() == item2is.getItemDamage()))
			return true;
		if(isOreDict)
			for(int intItem1 : OreDictionary.getOreIDs(item1is))
				for(int intItem2 : OreDictionary.getOreIDs(item2is))
					if(intItem1 == intItem2)
						return true;
		return false;
	}

	/**Returns is items equal
	 * @param item1 first item
	 * @param item2 second item
	 * @param isOreDict whether to check the ore dictionary
	 * @return boolean is items equal
	 */
	public static boolean isItemEqual(Object item1, Object item2, boolean isOreDict) {
		if(item1 == null || item2 == null) return false;
		
		ItemStack item1is = toItemStack(item1), item2is = toItemStack(item2);
		
		if(item1is.getItem() == item2is.getItem() && (item1is.getItemDamage() == 32767 || item1is.getItemDamage() == item2is.getItemDamage()))
			return true;
		if(isOreDict)
			for(int intItem1 : OreDictionary.getOreIDs(item1is))
				for(int intItem2 : OreDictionary.getOreIDs(item2is))
					if(intItem1 == intItem2)
						return true;
		return false;
	}
	
	public static ItemStack toItemStack(Object obj) {
		if(obj == null)
			return null;
		if(obj instanceof Item)
			return new ItemStack((Item)obj);
		else if(obj instanceof Block)
			return new ItemStack((Block)obj);
		else if(obj instanceof ItemStack)
			return (ItemStack)obj;
		else {
			Debuging.warnOutput("obj is not an Item, Block or ItemStack.This is an error, please let the author know.");
			return null;
		}		
	}
}