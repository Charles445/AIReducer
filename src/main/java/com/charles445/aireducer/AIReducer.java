package com.charles445.aireducer;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.handler.SpawnHandler;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
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
    public static final String VERSION = "0.1.1";
    
    @Mod.Instance(AIReducer.MODID)
	public static AIReducer instance;
	
	public static Logger logger = LogManager.getLogger("AIReducer");
	
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	if(ModConfig.debug)
    	{	
    		printRegisteredEntities();
    	}
        
    	MinecraftForge.EVENT_BUS.register(new SpawnHandler());
    }
    
    private void printRegisteredEntities()
    {
    	//Debug function
    	for(Map.Entry<ResourceLocation, EntityEntry> entry : ForgeRegistries.ENTITIES.getEntries())
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
    }
    
    public static void debugError(String s, Exception e)
    {
    	if(ModConfig.debug)
    		AIReducer.logger.error(s,e);
    }
    
    public static void debugError(String s)
    {
    	if(ModConfig.debug)
    		AIReducer.logger.error(s);
    }
    
    public static void debugDebug(String s)
    {
    	if(ModConfig.debug)
    		AIReducer.logger.debug(s);
    }
}
