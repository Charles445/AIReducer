package com.charles445.aireducer.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public abstract class WrappedTask extends EntityAIBase
{
	public EntityAIBase task;
	public EntityLivingBase entity;
	
	public WrappedTask(EntityLiving entity, EntityAIBase task)
	{
		this.task=task;
		this.entity=entity;
	}
	
	abstract public double getUpdateChance();
	
	@Override
	public boolean shouldExecute()
	{
		return task.shouldExecute();
	}
	
	@Override
    public boolean shouldContinueExecuting()
    {
        return task.shouldContinueExecuting();
    }
    
	@Override
    public boolean isInterruptible()
    {
        return task.isInterruptible();
    }

	@Override
    public void startExecuting()
    {
		task.startExecuting();
    }

    @Override
    public void resetTask()
    {
    	task.resetTask();
    }
    
    @Override
    public void updateTask()
    {
    	if(entity.getRNG().nextDouble() < this.getUpdateChance())
    		task.updateTask();
    }

    @Override
    public void setMutexBits(int mutexBitsIn)
    {
        task.setMutexBits(mutexBitsIn);
    }

    @Override
    public int getMutexBits()
    {
    	return task.getMutexBits();
    }
}
