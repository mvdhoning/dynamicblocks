package com.noeska.dynamicblocksmod.config;

import com.noeska.dynamicblocksmod.DynamicBlocksMod;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = DynamicBlocksMod.MODID)
@Config.LangKey("dynamicblocksmod.config.title")
public class ModConfig {

	@Config.Comment("BlockNames (needs restart after adding new entries)")
	public static String[] blockNames = new String[] { };

	@Config.Comment("ItemNames (needs restart after adding new entries)")
	public static String[] itemNames = new String[] { };
	
	@Mod.EventBusSubscriber(modid = DynamicBlocksMod.MODID)
	private static class EventHandler {

		/**
		 * Inject the new values and save to the config file when the config has been changed from the GUI.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(DynamicBlocksMod.MODID)) {
				ConfigManager.sync(DynamicBlocksMod.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
