package ru.mrtenfan.MTFCore.utils;

import static minetweaker.api.minecraft.MineTweakerMC.getItemStack;
import static minetweaker.api.minecraft.MineTweakerMC.getLiquidStack;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.ReflectionHelper;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.item.IngredientStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.oredict.IOreDictEntry;
import minetweaker.mc1710.oredict.MCOreDictEntry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class MinetweakerUtils {
    
    public static ItemStack toStack(IItemStack iStack) {
        return getItemStack(iStack);
    }
    
    public static Object toIoOStack(IIngredient iStack) {
    	if(iStack == null)
    		return null;
    	else {
    		if(iStack instanceof IItemStack)
                return getItemStack((IItemStack) iStack);
    		else if(iStack instanceof IngredientStack) {
    			IIngredient ingr = ReflectionHelper.getPrivateValue(IngredientStack.class, (IngredientStack) iStack, "ingredient");
                return new OreStack(((IOreDictEntry) ingr).getName(), iStack.getAmount());
    		} else if(iStack instanceof MCOreDictEntry) {
    			return new OreStack(((MCOreDictEntry) iStack).getName());
    		} else
    			return null;
    	}
    }
    
    public static Object toStackA(IIngredient iStack) {
    	if(iStack == null)
    		return null;
    	else {
    		if(iStack instanceof IItemStack)
                return getItemStack((IItemStack) iStack);
    		else if(iStack instanceof IngredientStack) {
                return getListOfItemStacks(iStack);
    		} else
    			return null;
    	}
    }

    public static ArrayList<ItemStack> getListOfItemStacks(IIngredient iStack) {
    	ArrayList<ItemStack> al = new ArrayList<ItemStack>();
		for(IItemStack iis : iStack.getItems()) {
			ItemStack is = toStack(iis);
			is.stackSize = iStack.getAmount();
			al.add(is);
		}
		return al;
	}

	public static Object toObject(IIngredient iStack) {
        if (iStack == null)
            return null;
        else {
            if (iStack instanceof IOreDictEntry)
                return ((IOreDictEntry) iStack).getName();
            else if (iStack instanceof IItemStack)
                return getItemStack((IItemStack) iStack);
            else if (iStack instanceof IngredientStack) {
                IIngredient ingr = ReflectionHelper.getPrivateValue(IngredientStack.class, (IngredientStack) iStack, "ingredient");
                return toObject(ingr);
            } else
                return null;
        }
    }

    public static FluidStack toFluidStack(ILiquidStack iStack) {
        return getLiquidStack(iStack);
    }
}