package com.noeska.dynamicblocksmod.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockRedstoneLevel extends BlockBase {

	public BlockRedstoneLevel(String name) {
        super(Material.IRON, name);
        setHardness(2F);
        setResistance(10F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 1);
	}
	
	@Override
	public BlockRedstoneLevel setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}
