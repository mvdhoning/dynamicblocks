package com.noeska.dynamicblocksmod.item;

import java.util.LinkedHashMap;

import com.noeska.dynamicblocksmod.DynamicBlocksMod;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item {

	protected String name;

	public ItemBase(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		this.initModel();
	}

	public void registerItemModel() {
		DynamicBlocksMod.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, EnumHand hand) {
		//some debug code for quicky changing nbt data
		ItemStack stack = null;
		playerIn.setActiveHand(hand);
		stack = playerIn.getHeldItem(hand);
		if (!world.isRemote) {
			String name = getName(stack);
			if ("test2".equalsIgnoreCase(name)) {
				getTagCompoundSafe(stack).setString("name", "test1");
			} else {
				getTagCompoundSafe(stack).setString("name", "test2");
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		}

		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		LinkedHashMap<String, ModelResourceLocation> test = new LinkedHashMap<String, ModelResourceLocation>();

		//default look without nbt name data
		ModelResourceLocation defaultLook = new ModelResourceLocation(getRegistryName(), "inventory");
		ModelBakery.registerItemVariants(this, defaultLook);
		
		// Loop trough config names for additional looks
		for (String iname : com.noeska.dynamicblocksmod.config.ModConfig.itemNames) {
			test.put(iname, new ModelResourceLocation(getRegistryName() + "_" + iname, "inventory"));
			// Add all modelresources
			ModelBakery.registerItemVariants(this, test.get(iname));
		}

		ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				//retrieve model by name
				String name = getName(stack);
				if (name==null) return defaultLook;
				return test.get(name);
			}
		});
		
	}

	private String getName(ItemStack stack) {
		String name = null;
		if (getTagCompoundSafe(stack).hasKey("name")) {
			name = getTagCompoundSafe(stack).getString("name");
		}
		return name;
	}

	private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
		NBTTagCompound tagCompound = stack.getTagCompound();
		if (tagCompound == null) {
			tagCompound = new NBTTagCompound();
			stack.setTagCompound(tagCompound);
		}
		return tagCompound;
	}

}
