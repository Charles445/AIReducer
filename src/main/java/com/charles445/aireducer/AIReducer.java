package com.charles445.aireducer;

import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.charles445.aireducer.config.JsonConfig;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.handler.SpawnHandler;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod
(
	modid = AIReducer.MODID, 
	name = AIReducer.NAME, 
	version = AIReducer.VERSION,
	acceptedMinecraftVersions = "[1.12]",
	acceptableRemoteVersions = "*"
	//updateJSON = "https://raw.githubusercontent.com/Charles445/SimpleDifficulty/master/modupdatechecker.json"
	
)
public class AIReducer
{
	public static final String MODID = "aireducer";
	public static final String NAME = "AI Reducer";
	public static final String VERSION = "0.3.0";
	
	@Mod.Instance(AIReducer.MODID)
	public static AIReducer instance;
	
	public static Logger logger = LogManager.getLogger("AIReducer");
	
	public static File jsonDirectory;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		jsonDirectory = new File(event.getModConfigurationDirectory(), AIReducer.MODID);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		//Run this first to populate the JSON before the spawn handler is created
		JsonConfig.init();
		
		if(ModConfig.debug)
		{	
			printRegisteredEntities(false);
		}
		
		MinecraftForge.EVENT_BUS.register(new SpawnHandler());
		
	}
	
	private void printRegisteredEntities(boolean dumpAll)
	{
		//Debug function
		for(Map.Entry<ResourceLocation, EntityEntry> entry : ForgeRegistries.ENTITIES.getEntries())
		{
			if(!dumpAll)
			{
				if(EntityLiving.class.isAssignableFrom(entry.getValue().getEntityClass()))
				{
					AIReducer.logger.debug(entry.getKey().toString());
				}
				else if(entry.getValue().getEntityClass().isAssignableFrom(EntityLiving.class))
				{
					AIReducer.logger.debug(entry.getKey().toString());
				}
			}
			else
			{
				AIReducer.logger.debug(entry.getKey().toString());
			}
		}
	}
}
