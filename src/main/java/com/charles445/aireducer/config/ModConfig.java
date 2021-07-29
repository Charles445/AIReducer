package com.charles445.aireducer.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfig
{
	public static GeneralConfig general;
	public static VanillaConfig minecraft;
	
	public ModConfig(ForgeConfigSpec.Builder builder)
	{
		general = makeCategory(builder, "general", new GeneralConfig());
		minecraft = makeCategory(builder, "minecraft", new VanillaConfig());
	}
	
	//
	//
	//
	
	protected final List<ConfigItemHolder> commonHolders = new ArrayList<>();
	
	private <E extends ConfigItemHolder> E makeCategory(ForgeConfigSpec.Builder builder, String category, E cih)
	{
		builder.push(category);
		cih.addAll(builder);
		builder.pop();
		
		commonHolders.add(cih);
		
		return cih;
	}
	
	public void updateAll()
	{
		for(ConfigItemHolder holder : commonHolders)
		{
			holder.updateAll();
		}
	}
}
