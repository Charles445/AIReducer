package com.charles445.aireducer.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;

public class AIAvoidReducedSimple<T extends Entity> extends EntityAIAvoidEntity
{
	//NOTE - Extending classes should override getExecuteDelay with their corresponding static config value!
	
	//Rearranging the predicate did hardly anything as it only polls when a valid monster to be scared of has been found
	//WRONG STATUS - VERY WRONG
	
	//This is currently unused and is probably going to stay unused, as this can now be done with WrappedTask
	
	private int shouldExecuteDelay = 0;
	
	public AIAvoidReducedSimple(EntityCreature entityIn, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
	{
		this(entityIn, classToAvoidIn, Predicates.alwaysTrue(), avoidDistanceIn, farSpeedIn, nearSpeedIn);
	}
	
	public AIAvoidReducedSimple(EntityCreature entityIn, Class classToAvoidIn, Predicate avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
	{
		super(entityIn, classToAvoidIn, avoidTargetSelectorIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
	}
	
	@Override
	public boolean shouldExecute()
 	{
		shouldExecuteDelay--;
		if(shouldExecuteDelay > 0)
		{
			return false;
		}
		shouldExecuteDelay = getShouldExecuteDelay();
		return super.shouldExecute();
	}
	
	//Override me!
	public int getShouldExecuteDelay()
	{
		//Default to instant
		return 1;
	}
	
}
