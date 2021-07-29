package com.charles445.aireducer.config;

import org.apache.commons.lang3.tuple.Pair;

import com.charles445.aireducer.AIReducer;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

public class ModConfigManager
{
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final CommonConfig COMMON_CONFIG;
	
	static
	{	
		Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		COMMON_CONFIG = pair.getLeft();
		COMMON_SPEC = pair.getRight();
	}
	
	protected static class CommonConfig
	{
		protected final ModConfig modConfig;
		
		protected CommonConfig(final ForgeConfigSpec.Builder builder)
		{
			modConfig = new ModConfig(builder);
		}
		
	}
	
	public static void loadAll()
	{
		CommentedFileConfig commonData = CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get()
				.resolve(AIReducer.MODID+"-common.toml"))
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();
		commonData.load();
		COMMON_SPEC.setConfig(commonData);
	}
	
	public static void updateCommon()
	{
		//COMMON_SPEC
		COMMON_CONFIG.modConfig.updateAll();
	}
}
