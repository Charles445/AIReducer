package com.charles445.aireducer.ai.myrmexold;

import com.charles445.aireducer.ai.WrappedTask;
import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskOldMyrmexAIEscortEntity extends WrappedTaskOldMyrmex
{
	public WrappedTaskOldMyrmexAIEscortEntity(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}
	
	public double getUpdateChance()
	{
		return ModConfig.iceandfireold.myrmexUpdateChanceEscortEntity;
	}
	
	@Override
	public void updateTask()
	{
		if(entity.getRNG().nextDouble() < this.getUpdateChance())
			task.updateTask();
	}
}
