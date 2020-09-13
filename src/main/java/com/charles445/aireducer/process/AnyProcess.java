package com.charles445.aireducer.process;

import com.charles445.aireducer.routine.IAFFearUntamedRoutine;
import com.charles445.aireducer.routine.Routine;

import net.minecraft.entity.EntityLiving;

/** Gets passed all EntityLiving entities */
public class AnyProcess extends ModProcess
{
	private Routine iafFearUntamedRoutine = new IAFFearUntamedRoutine();
	
	@Override
	public boolean canUse()
	{
		return true;
	}
	
	@Override
	public void handle(EntityLiving entity, String domain, String path)
	{
		iafFearUntamedRoutine.runRoutine(entity, domain, path);
	}
}
