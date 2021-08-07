package com.charles445.aireducer.config;

import net.minecraftforge.common.config.Config;

public class AnyConfig
{
	@Config.Comment("Enables tweaking task delay with the JSON config")
	@Config.Name("Task Delay Tweaks")
	public boolean taskDelayTweaks = true;
}
