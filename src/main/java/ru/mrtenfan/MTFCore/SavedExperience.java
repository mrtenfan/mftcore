package ru.mrtenfan.MTFCore;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class SavedExperience extends WorldSavedData {
	
	private final String lvl = "levels", sbr = "bar";
	private int levels, bar;

	public SavedExperience(String id, int lvl, int bar) {
		super(id);
		this.levels = lvl;
		this.bar = bar;
		System.out.println(levels + ":" + bar);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		levels = nbt.getInteger(lvl);
		bar = nbt.getInteger(sbr);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger(lvl, levels);
		nbt.setInteger(sbr, bar);
	}
	
	public int[] getExperience() {
		return new int[] {levels, bar};
	}
	
	public int getLevels() {
		return levels;
	}
	
	public int getBarExp() {
		return bar;
	}
}