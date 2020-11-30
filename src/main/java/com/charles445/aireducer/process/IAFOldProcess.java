package com.charles445.aireducer.process;

import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.reflect.ReflectorIAFOld;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.routine.IAFOldDeathWormRoutine;
import com.charles445.aireducer.routine.IAFOldMyrmexRoutine;
import com.charles445.aireducer.routine.Routine;

import net.minecraft.entity.EntityLiving;

public class IAFOldProcess extends ModProcess
{
	Routine deathWormRoutine = new IAFOldDeathWormRoutine();
	Routine myrmexRoutine = new IAFOldMyrmexRoutine();
	
	@Override
	public boolean canUse()
	{
		return ModConfig.iceandfireold.ENABLED && ReflectorIAFOld.reflector!=null && ReflectorMinecraft.reflector!=null;
	}
	
	@Override
	public void handle(EntityLiving entity, String domain, String path)
	{
		switch(path)
		{
			case "deathworm":
				deathWormRoutine.runRoutine(entity, domain, path);
				break;
				
			case "myrmex_worker":
			case "myrmex_soldier":
			case "myrmex_sentinel":
			case "myrmex_royal":
			case "myrmex_queen":
				myrmexRoutine.runRoutine(entity, domain, path);
				break;
			
			default: break;
		}
	}
	

}
