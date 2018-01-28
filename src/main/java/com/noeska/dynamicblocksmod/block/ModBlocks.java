package com.noeska.dynamicblocksmod.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

	public static BlockRedstoneLevel blockDynamic = new BlockRedstoneLevel("block_dynamic").setCreativeTab(CreativeTabs.MATERIALS);

	public static List<BlockRedstoneLevel> cb = new ArrayList<BlockRedstoneLevel>();

	public static void init() {
		for (String s : com.noeska.dynamicblocksmod.config.ModConfig.blockNames) {
			cb.add(new BlockRedstoneLevel(s).setCreativeTab(CreativeTabs.MATERIALS));
		}
	}

	public static void register(IForgeRegistry<Block> registry) {

		for (BlockRedstoneLevel b : cb) {
			registry.registerAll(b);
		}

		registry.registerAll(blockDynamic);
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for (BlockRedstoneLevel b : cb) {
			registry.registerAll(b.createItemBlock());
		}

		registry.registerAll(blockDynamic.createItemBlock());
	}

	public static void registerModels() {
		blockDynamic.registerItemModel(Item.getItemFromBlock(blockDynamic));
		for (BlockRedstoneLevel b : cb) {
			b.registerItemModel(Item.getItemFromBlock(b));
		}

	}

}
