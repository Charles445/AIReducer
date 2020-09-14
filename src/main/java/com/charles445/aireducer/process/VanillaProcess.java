package com.charles445.aireducer.process;

import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.routine.AvoidRoutine;
import com.charles445.aireducer.routine.RabbitRoutine;
import com.charles445.aireducer.routine.Routine;

import net.minecraft.entity.EntityLiving;

public class VanillaProcess extends ModProcess
{
	private Routine rabbitRoutine = new RabbitRoutine();
	private Routine avoidRoutine = new AvoidRoutine();
	
	@Override
	public boolean canUse()
	{
		return ModConfig.vanilla.ENABLED && ReflectorMinecraft.reflector!=null;
	}

	@Override
	public void handle(EntityLiving entity, String domain, String path)
	{
		//TODO would specifying which vanilla mobs to use make this run faster?
		//TODO consider migrating to AnyProcess... that might be bad though
		avoidRoutine.runRoutine(entity, domain, path);
		
		switch(path)
		{
			case "rabbit": 
				//RabbitRoutine includes AvoidRoutine handling
				rabbitRoutine.runRoutine(entity, domain, path);
				break;
			
			default: break;
		}
	}
}
