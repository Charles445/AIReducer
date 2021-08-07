package com.charles445.aireducer.process;

import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.routine.IAFOldFearUntamedRoutine;
import com.charles445.aireducer.routine.Routine;
import com.charles445.aireducer.routine.TaskDelayRoutine;

import net.minecraft.entity.EntityLiving;

/** Gets passed all EntityLiving entities */
public class AnyProcess extends ModProcess
{
	private Routine iafFearUntamedRoutine = new IAFOldFearUntamedRoutine();
	private Routine taskDelayRoutine = new TaskDelayRoutine();
	
	@Override
	public boolean canUse()
	{
		return ReflectorMinecraft.reflector!=null;
	}
	
	@Override
	public void handle(EntityLiving entity, String domain, String path)
	{
		taskDelayRoutine.runRoutine(entity, domain, path);
		iafFearUntamedRoutine.runRoutine(entity, domain, path);
	}
}
