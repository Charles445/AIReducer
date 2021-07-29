package com.charles445.aireducer.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ConfigItem<E>
{
	protected E referenced;
	protected final ConfigValue<E> stored;
	
	public ConfigItem(ForgeConfigSpec.Builder builder, String name, String comment, E defaultValue)
	{
		stored = builder
				.comment(comment)
				.define(name, defaultValue);
	}
	
	public void update()
	{
		this.referenced = stored.get();
	}

	public E get()
	{
		return this.referenced;
	}
}
