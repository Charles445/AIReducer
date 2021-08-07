package com.charles445.aireducer.config;

import com.charles445.aireducer.AIReducer;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Config(modid = AIReducer.MODID)
public class ModConfig
{
	@Config.Comment("Options that affect all mobs")
	@Config.Name("Any")
	public static AnyConfig any = new AnyConfig();
	
	@Config.Comment("Ice and Fire 1.8+ AI Options")
	@Config.Name("IceAndFire")
	public static IceAndFireConfig iceandfire = new IceAndFireConfig();
	
	@Config.Comment("Ice and Fire 1.7.1 AI Options")
	@Config.Name("IceAndFire171")
	public static IceAndFireOldConfig iceandfireold = new IceAndFireOldConfig();
	
	@Config.Comment("Minecraft AI Options")
	@Config.Name("Minecraft")
	public static VanillaConfig vanilla = new VanillaConfig();
	
	@Config.Comment("Enables debug features. Will spam the console with unnecessary information, so leave this off unless you are trying to fix a problem!")
	@Config.Name("Debug Mode")
	public static boolean debug = false;
	
	@Config.Comment("Master switch, enables all features")
	@Config.Name("Reducer Enabled")
	public static boolean enabled = true;
	
	/*
	@Config.Comment("Toggler for runtime things, does nothing")
	@Config.Name("Toggler")
	public static boolean toggler = true;
	*/
	@Mod.EventBusSubscriber(modid = AIReducer.MODID)
	private static class EventHandler
	{
		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if(event.getModID().equals(AIReducer.MODID))
			{
				ConfigManager.sync(AIReducer.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
