package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.debug.DebugUtil;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskMyrmexAILeaveHive extends WrappedTaskMyrmex
{
	public WrappedTaskMyrmexAILeaveHive(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}
	
	@Override
	public void resetTask()
	{
		
	}
	
	@Override
	public boolean shouldExecute()
	{
		if(!ModConfig.iceandfire.myrmexModifyLeaveHive)
			return super.shouldExecute();
		
		//Follow later versions' code to use shouldEnterHive early on
		
		if(reflector.c_EntityMyrmexQueen.isInstance(entity))
			return false;
		
		if(entity.isChild())
			return false;
		
		if(!reflector.canMove(entity))
			return false;
		
		if(!reflector.shouldLeaveHive(entity))
			return false;
		
		//The change
		if(reflector.shouldEnterHive(entity))
			return false;
		
		//Go back to normal
		boolean shouldExecute = super.shouldExecute();
		
		/*
		if(shouldExecute)
		{
			//Show the path with fire
			Path path = reflector.getMyrmexAILeaveHive_path(task);
			DebugUtil.dotPathPoints(entity.getEntityWorld(), path);
			BlockPos nextEntrance = reflector.getMyrmexAILeaveHive_nextEntrance(task);
			DebugUtil.sendParticle(entity.getEntityWorld(), EnumParticleTypes.BARRIER, nextEntrance.getX(), nextEntrance.getY(), nextEntrance.getZ());
		}
		*/
		
		return shouldExecute;
	}
	
	
	@Override
	public boolean shouldContinueExecuting()
	{
		//Complete override
		
		if(!ModConfig.iceandfire.myrmexModifyLeaveHive)
			return super.shouldContinueExecuting();
		
		//Rearranged a little bit
		
		if(!reflector.shouldLeaveHive(entity))
			return false;
		
		if(entity.getNavigator().noPath())
		{
			return false;
		}
		
		double distanceCheck = entity.getDistanceSq(reflector.getMyrmexAILeaveHive_nextEntrance(task));
		
		if(distanceCheck <= 3.0)
		{
			return false;
		}
		
		if(reflector.shouldEnterHive(entity))
			return false;
		
		
		
		return true;
	}
}
