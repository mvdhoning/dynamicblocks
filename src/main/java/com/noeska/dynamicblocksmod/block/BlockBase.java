package com.noeska.dynamicblocksmod.block;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.noeska.dynamicblocksmod.DynamicBlocksMod;
import com.noeska.dynamicblocksmod.util.IVariant;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBase extends Block {

	protected String name;
	public static final IProperty<EnumType> REDSTONE = PropertyEnum.create("redstone", EnumType.class);

	public BlockBase(Material material, String name) {
		super(material);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	public void registerItemModel(Item itemBlock) {
		DynamicBlocksMod.proxy.registerBlockItemRenderer(itemBlock, 0, name);
	}

	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}

	@Override
	public BlockBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
		
		//TODO: ignore being called twice from lever?
		//TODO: need to remember previous power state?
		//TODO: if powered by 2 sources and first goes of retain check again for lever?
		//TODO: also check all nearby neighbor blocks?
		
		if (!world.isRemote) {
			if (!world.isBlockPowered(pos)) {
				world.scheduleUpdate(pos, this, 4);
			} else if (world.isBlockPowered(pos)) {
				int redPower = getRedstonePowerLevel(world, pos, fromPos);
				world.setBlockState(pos, this.getDefaultState().withProperty(REDSTONE, EnumType.byMetadata(redPower)), 3);
			}
		}

	}

	public int getRedstonePowerLevel(World world, BlockPos pos, BlockPos fromPos) {
		int strongPower = world.getStrongPower(pos);
		int xDir = pos.getX() - fromPos.getX();
		int yDir = pos.getY() - fromPos.getY();
		int zDir = pos.getZ() - fromPos.getZ();
		int weakPower =  world.getRedstonePower(fromPos, EnumFacing.getFacingFromVector(xDir, yDir, zDir));
		int redPower = strongPower;
		if (strongPower <= weakPower) {
			redPower = weakPower;
		}
		return redPower;
	}

	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			if (!world.isBlockPowered(pos)) {
				world.setBlockState(pos, this.getDefaultState().withProperty(REDSTONE, EnumType.LEVEL_00), 3);
			}
		}
	}

	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		//TODO: fix does not detect lever powering block?
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		if (world.isBlockPowered(new BlockPos(i, j, k)) ) {
			int redPower = world.getStrongPower(new BlockPos(i, j, k));
			System.out.println("POWERED "+redPower);
			if (redPower !=15 && world.isBlockIndirectlyGettingPowered(new BlockPos(i, j, k)) > 0) {
				redPower = getRedstonePowerLevel(world, pos, pos); //TODO: 2nd pos needs to be not the block itself
				System.out.println("INDIRECT POWERED "+redPower);
			} 
			if (redPower==0) redPower=15; //if powered and power level is 0 just make it 15?
			world.setBlockState(pos, this.getDefaultState().withProperty(REDSTONE, EnumType.byMetadata(redPower)), 3);	
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(REDSTONE, EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(REDSTONE).getMeta();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, REDSTONE);
	}

	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		for (final EnumType enumType : EnumType.values()) {
			list.add(new ItemStack(this, 1, enumType.getMeta()));
		}
	}

	public enum EnumType implements IVariant {
		LEVEL_00(0,  "level_00"), 
		LEVEL_01(1,  "level_01"),
		LEVEL_02(2,  "level_02"),
		LEVEL_03(3,  "level_03"),
		LEVEL_04(4,  "level_04"),
		LEVEL_05(5,  "level_05"),
		LEVEL_06(6,  "level_06"),
		LEVEL_07(7,  "level_07"),
		LEVEL_08(8,  "level_08"),
		LEVEL_09(9,  "level_09"),
		LEVEL_10(10, "level_10"),
		LEVEL_11(11, "level_11"),
		LEVEL_12(12, "level_12"),
		LEVEL_13(13, "level_13"),
		LEVEL_14(14, "level_14"),
		LEVEL_15(15, "level_15");

		private static final EnumType[] META_LOOKUP = Stream.of(values())
				.sorted(Comparator.comparing(EnumType::getMeta)).toArray(EnumType[]::new);

		private final int meta;
		private final String name;

		EnumType(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		public int getMeta() {
			return meta;
		}

		@Override
		public String getName() {
			return name;
		}

		public static EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public static String[] getNames() {
			return Stream.of(META_LOOKUP).map(EnumType::getName).toArray(String[]::new);
		}
	}

}