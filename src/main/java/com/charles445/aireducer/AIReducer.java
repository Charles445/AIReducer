package com.charles445.aireducer;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.charles445.aireducer.config.JsonConfig;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.config.ModConfigManager;
import com.charles445.aireducer.handler.SpawnHandler;
import com.charles445.aireducer.util.CollisionUtil;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLConfig;
import net.minecraftforge.fml.loading.FMLPaths;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("aireducer")
public class AIReducer
{
	public static final String MODID = "aireducer";
	public static final String NAME = "AI Reducer";
	public static final String VERSION = "0.1.0"; //Make sure to update mods toml and gradle
	
	public static AIReducer instance;
	
	public static Logger logger = LogManager.getLogger("AIReducer");
	
	public static File jsonDirectory;
	
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
    	jsonDirectory = new File(FMLPaths.CONFIGDIR.get().toFile(), MODID);
		mlc.registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfigManager.COMMON_SPEC);
		ModConfigManager.loadAll();
		ModConfigManager.updateCommon();
		JsonConfig.init();
		
		//Less Collisions
    	CollisionUtil.instance.isLessCollisionsEnabled = ModConfig.general.lessCollisions.get();
		
		//Handlers
    	MinecraftForge.EVENT_BUS.register(new SpawnHandler());
    }
    
}
