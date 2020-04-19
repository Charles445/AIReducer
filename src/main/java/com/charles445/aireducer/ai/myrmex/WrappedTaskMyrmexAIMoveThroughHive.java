package com.charles445.aireducer.ai.myrmex;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskMyrmexAIMoveThroughHive extends WrappedTaskMyrmex
{

	public WrappedTaskMyrmexAIMoveThroughHive(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}

	@Override
	public void resetTask()
	{
		
	}
}
