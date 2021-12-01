package com.charles445.aireducer.routine;

import com.charles445.aireducer.ai.WrappedProximityTask;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.reflect.ReflectorIAF;
import com.charles445.aireducer.reflect.ReflectorMinecraft;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class IAFDeathWormRoutine extends Routine
{
	@Override
	public boolean canRun()
	{
		return ModConfig.iceandfire.deathworm && ReflectorIAF.reflector != null && ReflectorMinecraft.reflector != null;
	}

	@Override
	protected void run(EntityLiving entity, String domain, String path)
	{
		/*
		ErrorUtil.debugDebug("Applying deathworm delay: "+ModConfig.iceandfire.deathworm_ai_delay);
		applyTickRate(entity.tasks, ModConfig.iceandfire.deathworm_ai_delay);
		applyTickRate(entity.targetTasks, ModConfig.iceandfire.deathworm_ai_delay);
		*/
		
		wrapTask(entity, entity.tasks, null, WrappedProximityTaskDeathWorm.class);
		wrapTask(entity, entity.targetTasks, null, WrappedProximityTaskDeathWorm.class);
	}
	
	public static class WrappedProximityTaskDeathWorm extends WrappedProximityTask
	{
		public WrappedProximityTaskDeathWorm(EntityLiving entity, EntityAIBase task)
		{
			super(entity, task);
		}
		
		@Override
		public double getProximity()
		{
			return ModConfig.iceandfire.deathwormProximityRequirement;
		}

		@Override
		public boolean canModify()
		{
			return ModConfig.iceandfire.deathwormProximityRequired;
		}
	}
}
