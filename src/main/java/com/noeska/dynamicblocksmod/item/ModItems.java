package com.noeska.dynamicblocksmod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

	public static ItemBase itemDynamic = new ItemBase("item_dynamic").setCreativeTab(CreativeTabs.MATERIALS);

	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				itemDynamic
		);
	}

	public static void registerModels() {
		itemDynamic.registerItemModel();
	}

}
