package com.charles445.aireducer.ai.myrmexold;

import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskOldMyrmexAIMoveThroughHive extends WrappedTaskOldMyrmex
{

	public WrappedTaskOldMyrmexAIMoveThroughHive(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}

	@Override
	public void resetTask()
	{
		if(!ModConfig.iceandfireold.myrmexMoveThroughHive)
		{
			super.resetTask();
			return;
		}
	}
}
