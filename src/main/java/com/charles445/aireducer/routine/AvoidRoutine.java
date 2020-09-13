package com.charles445.aireducer.routine;

import com.charles445.aireducer.ai.AIAvoidReduced;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.google.common.base.Function;
import com.google.common.base.Predicate;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;

public class AvoidRoutine extends Routine
{
	@Override
	public boolean canRun()
	{
		return ReflectorMinecraft.reflector != null;
	}

	@Override
	protected void run(EntityLiving entity, String domain, String path)
	{
		if(entity instanceof EntityCreature)
		{
			findAndReplaceAvoids((EntityCreature)entity);
		}
	}
	
	protected EntityAIBase constructAvoid(EntityCreature entity, Class<?> classToAvoid, Predicate<?> avoidTargetSelector, float avoidDistance, double farSpeed, double nearSpeed)
	{
		return new AIAvoidReduced(entity, classToAvoid, avoidTargetSelector, avoidDistance, farSpeed, nearSpeed);
	}

	
	private void findAndReplaceAvoids(EntityCreature entityIn)
	{
		//Duplicate the avoid task (same settings, just with a different class)
		
		int count = tryAndReplaceAllTasks(entityIn, entityIn.tasks, EntityAIAvoidEntity.class, (Function<EntityAITaskEntry,EntityAIBase>) oldTaskEntry -> 
		{
			try
			{
				Class<?> classToAvoid = (Class<?>) ReflectorMinecraft.reflector.f_EntityAIAvoidEntity_classToAvoid.get(oldTaskEntry.action);
				Predicate <?> avoidTargetSelector = (Predicate<?>) ReflectorMinecraft.reflector.f_EntityAIAvoidEntity_avoidTargetSelector.get(oldTaskEntry.action);
				float avoidDistance = (float) ReflectorMinecraft.reflector.f_EntityAIAvoidEntity_avoidDistance.get(oldTaskEntry.action);
				double farSpeed = (double) ReflectorMinecraft.reflector.f_EntityAIAvoidEntity_farSpeed.get(oldTaskEntry.action);
				double nearSpeed = (double) ReflectorMinecraft.reflector.f_EntityAIAvoidEntity_nearSpeed.get(oldTaskEntry.action);
				
				EntityCreature entity = (EntityCreature) ReflectorMinecraft.reflector.f_EntityAIAvoidEntity_entity.get(oldTaskEntry.action);
				
				return constructAvoid(entity,classToAvoid,avoidTargetSelector, avoidDistance, farSpeed, nearSpeed);
			}
			catch(Exception e)
			{
				return null;
			}
		});
		
		if(count > 0)
			debugPrintTasks(entityIn.tasks);
	}
}
