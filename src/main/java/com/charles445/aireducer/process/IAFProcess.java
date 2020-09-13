package com.charles445.aireducer.process;

import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.routine.IAFDeathWormRoutine;
import com.charles445.aireducer.routine.IAFMyrmexRoutine;
import com.charles445.aireducer.routine.Routine;

import net.minecraft.entity.EntityLiving;

public class IAFProcess extends ModProcess
{
	Routine deathWormRoutine = new IAFDeathWormRoutine();
	Routine myrmexRoutine = new IAFMyrmexRoutine();
	
	@Override
	public boolean canUse()
	{
		return ModConfig.iceandfire.ENABLED;
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
