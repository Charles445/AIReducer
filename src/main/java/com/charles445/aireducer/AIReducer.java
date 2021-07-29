package com.charles445.aireducer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.charles445.aireducer.config.ModConfigManager;
import com.charles445.aireducer.handler.SpawnHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("aireducer")
public class AIReducer
{
	public static final String MODID = "aireducer";
	public static final String NAME = "AI Reducer";
	public static final String VERSION = "0.1.0"; //Make sure to update mods toml and gradle
	
	public static AIReducer instance;
	
	public static Logger logger = LogManager.getLogger("AIReducer");
	
    public AIReducer()
    {
    	if(instance == null)
    		instance = this;
    	
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	//Config
		ModLoadingContext mlc = ModLoadingContext.get();
		mlc.registerConfig(ModConfig.Type.COMMON, ModConfigManager.COMMON_SPEC);
		ModConfigManager.loadAll();
		ModConfigManager.updateCommon();
    	
		//Handlers
    	MinecraftForge.EVENT_BUS.register(new SpawnHandler());
    }
    
}
