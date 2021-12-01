package com.charles445.aireducer.routine;

import com.charles445.aireducer.ai.WrappedProximityTask;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.reflect.ReflectorIAFOld;
import com.charles445.aireducer.reflect.ReflectorMinecraft;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class IAFOldDeathWormRoutine extends Routine
{
	@Override
	public boolean canRun()
	{
		return ModConfig.iceandfireold.deathworm && ReflectorIAFOld.reflector != null && ReflectorMinecraft.reflector != null;
	}

	@Override
	protected void run(EntityLiving entity, String domain, String path)
	{
		/*
		ErrorUtil.debugDebug("Applying deathworm delay: "+ModConfig.iceandfireold.deathworm_ai_delay);
		applyTickRate(entity.tasks, ModConfig.iceandfireold.deathworm_ai_delay);
		applyTickRate(entity.targetTasks, ModConfig.iceandfireold.deathworm_ai_delay);
		*/
		
		wrapTask(entity, entity.tasks, null, WrappedProximityTaskOldDeathWorm.class);
		wrapTask(entity, entity.targetTasks, null, WrappedProximityTaskOldDeathWorm.class);
	}
	
	public static class WrappedProximityTaskOldDeathWorm extends WrappedProximityTask
	{
		public WrappedProximityTaskOldDeathWorm(EntityLiving entity, EntityAIBase task)
		{
			super(entity, task);
		}
		
		@Override
		public double getProximity()
		{
			return ModConfig.iceandfireold.deathwormProximityRequirement;
		}

		@Override
		public boolean canModify()
		{
			return ModConfig.iceandfireold.deathwormProximityRequired;
		}
	}
}
