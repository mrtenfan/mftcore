package ru.mrtenfan.MTFCore;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.minecraft.item.ItemStack;

public class NEIConfig implements IConfigureNEI {

	@Override
	public String getName() {
		return "MTFCore";
	}

	@Override
	public String getVersion() {
		return "${version}";
	}

	@Override
	public void loadConfig() {
		API.hideItem(new ItemStack(ItemInit.Partyhatblue));
		API.hideItem(new ItemStack(ItemInit.Partyhatcyan));
		API.hideItem(new ItemStack(ItemInit.Partyhatgreen));
	}
}