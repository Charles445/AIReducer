package com.charles445.aireducer.process;

import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.routine.AvoidRoutine;
import com.charles445.aireducer.routine.NearestAttackRoutine;
import com.charles445.aireducer.routine.Routine;

import net.minecraft.entity.MobEntity;

public class VanillaProcess extends ModProcess
{
	private Routine avoidRoutine = new AvoidRoutine();
	private Routine nearestAttackRoutine = new NearestAttackRoutine();
	
	@Override
	public boolean canUse()
	{
		return ModConfig.minecraft.ENABLED.get();
	}

	@Override
	public void handle(MobEntity entity, String domain, String path)
	{
		//TODO would specifying which vanilla mobs to use make this run faster?
		//TODO consider migrating to AnyProcess... that might be bad though
		avoidRoutine.runRoutine(entity, domain, path);
		nearestAttackRoutine.runRoutine(entity, domain, path);
		
	}
}