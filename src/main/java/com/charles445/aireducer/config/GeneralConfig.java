package com.charles445.aireducer.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GeneralConfig extends ConfigItemHolder
{
	public ConfigItem<Boolean> enabled;
	public ConfigItem<Boolean> lessCollisions;
	
	@Override
	protected void addAll(ForgeConfigSpec.Builder builder)
	{
		enabled = add(builder, "Reducer Enabled", "Master switch, enables all features", true);
		lessCollisions = add(builder, "Custom Collision Radius", "Reads lessCollisions json to determine entity radius, increases performance with mods like LycanitesMobs", true);
	}
	
}
