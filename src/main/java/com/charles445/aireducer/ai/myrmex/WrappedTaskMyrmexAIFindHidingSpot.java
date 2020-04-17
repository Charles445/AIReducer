package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.ai.WrappedTask;
import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskMyrmexAIFindHidingSpot extends WrappedTaskMyrmex
{
	public WrappedTaskMyrmexAIFindHidingSpot(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}

	public double getUpdateChance()
	{
		return ModConfig.iceandfire.myrmexUpdateChanceFindHidingSpot;
	}
	
	@Override
    public void updateTask()
    {
    	if(entity.getRNG().nextDouble() < this.getUpdateChance())
    		task.updateTask();
    }
}
