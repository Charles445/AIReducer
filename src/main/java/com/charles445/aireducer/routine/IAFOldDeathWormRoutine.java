package com.charles445.aireducer.routine;

import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.reflect.ReflectorIAFOld;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.util.ErrorUtil;

import net.minecraft.entity.EntityLiving;

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
	}

}
