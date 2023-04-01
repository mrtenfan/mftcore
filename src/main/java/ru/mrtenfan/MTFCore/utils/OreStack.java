package ru.mrtenfan.MTFCore.utils;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class OreStack {

	private String name;
	public int stackSize;
	
	public OreStack(String name) {
		this(name, 1);
	}
	
	public OreStack(String name, int stackSize) {
		this.name = name;
		this.stackSize = stackSize;
	}
	
	public String getName() {
		return name;
	}
	
	public ItemStack getStack() {
		ItemStack ret = OreDictionary.getOres(name).get(0);
		if(stackSize == 1) return ret;
		ret.stackSize = stackSize;
		return ret;
	}
	
	public static ItemStack getStack(OreStack os) {
		ItemStack ret = OreDictionary.getOres(os.getName()).get(0);
		if(os.stackSize == 1) return ret;
		ret.stackSize = os.stackSize;
		return ret;
	}
	
	public ArrayList<ItemStack> getStacks() {
		if(stackSize == 1) return OreDictionary.getOres(name);
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		for(ItemStack is : OreDictionary.getOres(name)) {
			is.stackSize = stackSize;
			ret.add(is);
		}
		return ret;
	}
	
	public static ArrayList<ItemStack> getStacks(OreStack os) {
		if(os.stackSize == 1) return OreDictionary.getOres(os.getName());
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		for(ItemStack is : OreDictionary.getOres(os.getName())) {
			is.stackSize = os.stackSize;
			ret.add(is);
		}
		return ret;
	}
	
	@Override
	public boolean equals(Object obj) {
		return equals(obj, true);
	}
	
	public boolean equals(Object obj, boolean onlyos) {
		if(!(obj instanceof ItemStack || obj instanceof Item || obj instanceof Block || obj instanceof OreStack)) return false;
		if(obj instanceof OreStack) {
			OreStack os = (OreStack) obj;
			if(os.getName().equalsIgnoreCase(this.getName()) && this.stackSize == os.stackSize)
				return true;
		} else if(!onlyos) {
			ItemStack is = ItemUtils.toItemStack(obj);
			if(ItemUtils.isItemEqual(this.getStacks().get(0), is, true) && this.stackSize == is.stackSize)
				return true;
		}
		return false;
	}
	
	public static boolean areOreStacksEquals(OreStack os1, OreStack os2) {
		return os1.equals(os2);
	}
	
	@Override
	public String toString() {
		return this.stackSize + "x" + getName();
	}
}