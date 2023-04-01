package ru.mrtenfan.MTFCore;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.WorldSavedData;

public class SavedInventory extends WorldSavedData {

	private ItemStack[] slots;
	private String id;
	
	public SavedInventory(String id, ItemStack[] is) {
		super(id);
		this.id = id;
		this.slots = is;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		slots = new ItemStack[(id.equalsIgnoreCase("armor") ? 4 : 36)];
		NBTTagList list = nbt.getTagList(id, 10);
		
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound nbt1 = (NBTTagCompound)list.getCompoundTagAt(i);
			byte b0 = nbt1.getByte("Slot");

			if (b0 >= 0 && b0 < slots.length) {
				slots[b0] = ItemStack.loadItemStackFromNBT(nbt1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();

		for (int i = 0; i < slots.length; i++) {
			if (slots[i] != null) {
				NBTTagCompound nbt1 = new NBTTagCompound();
				nbt1.setByte("Slot", (byte)i);
				slots[i].writeToNBT(nbt1);
				list.appendTag(nbt1);
			}
		}
		
		nbt.setTag(this.id, list);
	}
	
	public ItemStack[] getStacks() {
		return slots;
	}
	
	public String getName() {
		return id;
	}
}