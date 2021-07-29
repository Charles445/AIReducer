package com.charles445.aireducer.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public abstract class ConfigItemHolder
{
	private List<ConfigItem<?>> configItems = new ArrayList<>();
	
	public void updateAll()
	{
		for(ConfigItem<?> item : configItems)
		{
			item.update();
		}
	}
	
	protected abstract void addAll(ForgeConfigSpec.Builder builder);
	
	protected <E> ConfigItem<E> add(ForgeConfigSpec.Builder builder, String name, String comment, E defaultValue)
	{
		ConfigItem<E> item = new ConfigItem<E>(builder, name, comment, defaultValue);
		configItems.add(item);
		return item;
	}
}
