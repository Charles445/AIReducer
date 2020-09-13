package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.ai.WrappedTask;
import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskMyrmexAIEscortEntity extends WrappedTaskMyrmex
{
	public WrappedTaskMyrmexAIEscortEntity(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}
	
	public double getUpdateChance()
	{
		return ModConfig.iceandfire.myrmexUpdateChanceEscortEntity;
	}
	
	@Override
	public void updateTask()
	{
		if(entity.getRNG().nextDouble() < this.getUpdateChance())
			task.updateTask();
	}
}
