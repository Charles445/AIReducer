package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public abstract class WrappedTaskMyrmexRunDelay extends WrappedTaskMyrmex
{
	int lastRun = -10000;
	
	public WrappedTaskMyrmexRunDelay(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}
	
	public abstract boolean canModify();
	public abstract int getRunDelay();
	public abstract double getUpdateChance();
	
	@Override
	public boolean shouldExecute()
	{
		if(!this.canModify())
			return super.shouldExecute();
		
		if(lastRun < (entity.ticksExisted - this.getRunDelay()))
		{
			return super.shouldExecute();
		}
		
		return false;
	}
	
	@Override
	public void startExecuting()
	{
		if(!this.canModify())
		{
			super.startExecuting();
			return;
		}
		
		lastRun = entity.ticksExisted;
		
		super.startExecuting();
	}
	
	@Override
	public void updateTask()
	{
		if(!this.canModify())
		{
			super.updateTask();
			return;
		}

		double updateChance = this.getUpdateChance();
		
		if(updateChance <= 0.0d)
			return;
		
		if(updateChance >= 1.0d)
		{
			super.updateTask();
			return;
		}
		
		if(entity.getRNG().nextDouble() < updateChance)
		{
			super.updateTask();
			return;
		}
	}
}
