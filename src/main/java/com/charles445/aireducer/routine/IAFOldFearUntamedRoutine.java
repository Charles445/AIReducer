package com.charles445.aireducer.routine;

import javax.annotation.Nullable;

import com.charles445.aireducer.compat.iceandfireold.VillagerAIFearUntamedReduced;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.reflect.ReflectorIAFOld;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.util.ErrorUtil;
import com.google.common.base.Predicate;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class IAFOldFearUntamedRoutine extends Routine
{
	@Override
	public boolean canRun()
	{
		return(ModConfig.iceandfireold.replaceVillageAIFearUntamed && ReflectorIAFOld.reflector != null && ReflectorMinecraft.reflector != null);
	}
	
	@Override
	public void run(EntityLiving entity, String domain, String path)
	{
		try
		{
			if((boolean)ReflectorIAFOld.reflector.m_iceandfire_isLivestock.invoke(null, entity))
			{
				tryReplaceIceAndFireLivestockFearTask(entity);
			}
			else if((boolean)ReflectorIAFOld.reflector.m_iceandfire_isVillager.invoke(null, entity))
			{
				tryReplaceIceAndFireVillagerFearTask(entity);
			}
		}
		catch(Exception e)
		{
			ErrorUtil.debugError("Failed to check isLivestock!", e);
		}
	}
	
	private void tryReplaceIceAndFireVillagerFearTask(EntityLiving entity)
	{
		//Just in case
		if(entity instanceof EntityCreature)
		{
			tryAndReplaceAllTasks(entity, entity.tasks, ReflectorIAFOld.reflector.c_iceandfire_VillagerAIFearUntamed, task -> makeIceAndFireVillagerFearTask((EntityCreature)entity));
		}
	}
	
	private EntityAIBase makeIceAndFireVillagerFearTask(EntityCreature entity)
	{
		return new VillagerAIFearUntamedReduced(
			entity,
			EntityLivingBase.class, //Class to avoid
			new Predicate<EntityLivingBase>()
			{
				public boolean apply(@Nullable final EntityLivingBase targentity)
				{
					return targentity != null && ReflectorIAFOld.reflector.c_iceandfire_IVillagerFear.isInstance(targentity);		 	
				}
			},12.0f, 0.8, 0.8);
	}
	
	private void tryReplaceIceAndFireLivestockFearTask(EntityLiving entity)
	{
		//Just in case
		if(entity instanceof EntityCreature)
		{
			tryAndReplaceAllTasks(entity, entity.tasks, ReflectorIAFOld.reflector.c_iceandfire_VillagerAIFearUntamed, task -> makeIceAndFireLivestockFearTask((EntityCreature)entity));
		}
	}
	
	private EntityAIBase makeIceAndFireLivestockFearTask(EntityCreature entity)
	{
		return new VillagerAIFearUntamedReduced(
			entity, 
			EntityLivingBase.class, //Class to avoid
			//c_iceandfire_IAnimalFear, //Class to avoid ClassInheritanceMultiMap MOMENTS COME ON
			new Predicate<EntityLivingBase>()
			{
				public boolean apply(@Nullable final EntityLivingBase targentity)
				{
					try
					{
						return targentity != null && ReflectorIAFOld.reflector.c_iceandfire_IAnimalFear.isInstance(targentity) && (boolean)ReflectorIAFOld.reflector.m_iceandfire_shouldAnimalsFear.invoke(targentity, entity);
					}
					catch(Exception e)
					{
						ErrorUtil.debugError("Failed to makeIceAndFireLivestockFearTask!", e);
						return false;
					}		   	
				}
			},12.0f,1.2,1.5);
	}
}
