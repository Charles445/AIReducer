package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskMyrmexAIWanderHiveCenter extends WrappedTaskMyrmexRunDelay
{
	public WrappedTaskMyrmexAIWanderHiveCenter(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}

	@Override
	public boolean canModify()
	{
		return ModConfig.iceandfire.myrmexModifyWanderHiveCenter;
	}

	@Override
	public int getRunDelay()
	{
		return ModConfig.iceandfire.myrmexRunDelayWanderHiveCenter;
	}
	
	@Override
	public double getUpdateChance()
	{
		return ModConfig.iceandfire.myrmexUpdateChanceWanderHiveCenter;
	}
}
