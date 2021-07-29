package com.charles445.aireducer.config;

import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class VanillaConfig extends ConfigItemHolder
{
	public ConfigItem<Boolean> ENABLED;
	public ConfigItem<Boolean> avoidTaskReplacement;
	public ConfigItem<Boolean> nearestAttackTaskReplacement;
	
	@Override
	protected void addAll(Builder builder)
	{
		ENABLED = add(builder, "ENABLED", "Master switch for this section", true);
		avoidTaskReplacement = add(builder, "avoidTaskReplacement", "Replace vanilla entity avoid tasks for better performance", true);
		nearestAttackTaskReplacement = add(builder, "nearestAttackTaskReplacement", "Replace vanilla entity nearest attack tasks for better performance", true);
	}

}
