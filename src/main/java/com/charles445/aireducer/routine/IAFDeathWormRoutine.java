package com.charles445.aireducer.routine;

import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.reflect.ReflectorIAF;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.util.ErrorUtil;

import net.minecraft.entity.EntityLiving;

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
		ErrorUtil.debugDebug("Applying deathworm delay: "+ModConfig.iceandfire.deathworm_ai_delay);
		applyTickRate(entity.tasks, ModConfig.iceandfire.deathworm_ai_delay);
		applyTickRate(entity.targetTasks, ModConfig.iceandfire.deathworm_ai_delay);
	}

}
