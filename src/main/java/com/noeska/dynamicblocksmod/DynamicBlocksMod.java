package com.noeska.dynamicblocksmod;

import com.noeska.dynamicblocksmod.block.ModBlocks;
import com.noeska.dynamicblocksmod.item.ModItems;
import com.noeska.dynamicblocksmod.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = DynamicBlocksMod.MODID, name = DynamicBlocksMod.NAME, version = DynamicBlocksMod.VERSION)
public class DynamicBlocksMod
{
    public static final String MODID = "dynamicblocksmod";
    public static final String NAME = "Dynamic Blocks";
    public static final String VERSION = "@VERSION@";
    
    @SidedProxy(serverSide = "com.noeska.dynamicblocksmod.proxy.CommonProxy", clientSide = "com.noeska.dynamicblocksmod.proxy.ClientProxy")
    public static CommonProxy proxy;
    
    @Mod.Instance(MODID)
	public static DynamicBlocksMod instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(NAME + " is loading!");
        ModBlocks.init();
	}
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }
    
    @Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
    
    @Mod.EventBusSubscriber
	public static class RegistrationHandler {
    	
    	@SubscribeEvent
    	public static void registerBlocks(RegistryEvent.Register<Block> event) {
    		ModBlocks.register(event.getRegistry());
    	}
    	
    	@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
    		ModItems.register(event.getRegistry());
    		ModBlocks.registerItemBlocks(event.getRegistry());
		}
    	
    	@SubscribeEvent
    	public static void registerItems(ModelRegistryEvent event) {
    		ModItems.registerModels();
    		ModBlocks.registerModels();
    	}
	
	}
}
