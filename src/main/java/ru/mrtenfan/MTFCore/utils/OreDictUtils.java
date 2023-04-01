package ru.mrtenfan.MTFCore.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ru.mrtenfan.MTFCore.Debuging;

public class OreDictUtils {

	/**Returns item stack with oreDictionary tag
	 * @param oreDict ore dictionary name
	 * @return ItemStack with ore dictionary tag
	 */
	public static ItemStack getItemStackFromOreDict(String oreDict) {
		return getItemStackFromOreDict(oreDict, 1);
	}

	/**Returns item stack with oreDictionary tag
	 * @param oreDict ore dictionary name
	 * @param stackSize return itemstack
	 * @return ItemStack with ore dictionary tag
	 */
	public static ItemStack getItemStackFromOreDict(String oreDict, int stackSize) {
		if(doesOreExist(oreDict)) {
			ItemStack stack = OreDictionary.getOres(oreDict).get(0).copy();
			stack.stackSize = stackSize;
			return stack;
		} else {
			Debuging.warnOutput("There is no item/block for " + oreDict);
			return null;
		}
	}

	/**Returns does ore dictionary tag exist
	 * @param ore ore dictionary tag
	 * @return boolean does ore dictionary tag exist
	 */
	public static boolean doesOreExist(String ore) { 
		ArrayList<ItemStack> oreDict = OreDictionary.getOres(ore);
		return OreDictionary.doesOreNameExist(ore) && oreDict.toArray().length > 0;
	}

	/**Returns list of ItemStack with ore dictionary tag on stack
	 * @param stack ItemStack with necessary tag
	 * @return {@link List} list of ItemStacks with same ore dictionary tags
	 */
	public static List<ItemStack> getStackWithAllOre(ItemStack stack) {
		if (stack == null) {
			return new ArrayList<ItemStack>();
		}
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		for (int oreID : OreDictionary.getOreIDs(stack)) {
			for (ItemStack ore : OreDictionary.getOres(OreDictionary.getOreName(oreID))) {
				ItemStack newOre = ore.copy();
				newOre.stackSize = stack.stackSize;
				list.add(newOre);
			}
		}
		if (list.isEmpty()) {
			list.add(stack);
		}
		return list;
	}

	/**Returns list of ItemStack with ore dictionary tag
	 * @param tag necessary tag
	 * @return {@link List} list of ItemStacks with same ore dictionary tags
	 */
	public static List<ItemStack> getStackWithTag(String tag){
		if(tag.isEmpty())
			return new ArrayList<ItemStack>();
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		for(ItemStack is : OreDictionary.getOres(tag)) {
			ItemStack isore = is.copy();
			isore.stackSize = is.stackSize;
			list.add(isore);
		}
		return list;
	}

	/**Returns does item/block/itemstack have tag
	 * @param tag ore dictionary tag
	 * @param obj item/block which will be checked on having same tag
	 * @return boolean does item/block have necessary tag
	 */
	public static boolean doesHaveOreTag(String tag, Object obj) {
		ItemStack is = null;
		if(obj instanceof Item)
			is = new ItemStack((Item)obj);
		else if(obj instanceof Block)
			is = new ItemStack((Block)obj);
		else if(obj instanceof ItemStack)
			is = (ItemStack)obj;
		else
			Debuging.warnOutput("obj is not an Item, Block or ItemStack.This is an error, please let the author know.");

		for(ItemStack is2 : OreDictionary.getOres(tag, false)) {
			if(ItemUtils.isItemEqual(is, is2, false) && OreDictionary.doesOreNameExist(tag))
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param itemStack
	 * @return
	 */
	public static String getOreDict(ItemStack itemStack) {
		return OreDictionary.getOreName(OreDictionary.getOreIDs(itemStack)[0]);
	}
}