package com.charles445.aireducer.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GeneralConfig extends ConfigItemHolder
{
	public ConfigItem<Boolean> enabled;
	
	@Override
	protected void addAll(ForgeConfigSpec.Builder builder)
	{
		enabled = add(builder, "Reducer Enabled", "Master switch, enables all features", true);
	}
	
}
