package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.ai.WrappedTask;
import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskMyrmexAIForage extends WrappedTask
{
	public WrappedTaskMyrmexAIForage(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}

	@Override
	public double getUpdateChance()
	{
		return ModConfig.iceandfire.myrmexUpdateChanceForage;
	}
	
}
