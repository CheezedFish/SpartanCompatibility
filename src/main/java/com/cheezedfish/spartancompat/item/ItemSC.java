package com.cheezedfish.spartancompat.item;

import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;

import net.minecraft.item.Item;

public class ItemSC extends Item {
	
	public ItemSC(String unlocName) {
		this.setUnlocalizedName(unlocName);
		this.setRegistryName(unlocName);
		this.setCreativeTab(CreativeTabsSW.TAB_SW_MOD);
	}

}
