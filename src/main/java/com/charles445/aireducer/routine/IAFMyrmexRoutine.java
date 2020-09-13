package com.charles445.aireducer.routine;

import com.charles445.aireducer.ai.myrmex.PathNavigateMyrmexAlternate;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAIEscortEntity;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAIFindHidingSpot;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAIForage;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAILeaveHive;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAIMoveThroughHive;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAIReEnterHive;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.reflect.ReflectorIAF;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.util.ErrorUtil;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;

public class IAFMyrmexRoutine extends Routine
{
	@Override
	public boolean canRun()
	{
		return ModConfig.iceandfire.myrmex && ReflectorIAF.reflector != null && ReflectorMinecraft.reflector != null;
	}

	@Override
	protected void run(EntityLiving entity, String domain, String path)
	{
		if(ModConfig.iceandfire.myrmexAlternateNavigation && !path.equals("myrmex_royal") && !path.equals("myrmex_queen"))
		{
			ErrorUtil.debugDebug("Swapping out myrmex navigator");
			setNavigator(entity, new PathNavigateMyrmexAlternate(entity, entity.getEntityWorld()));
		}
		
		entity.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(ModConfig.iceandfire.myrmexFollowRange);
		
		ErrorUtil.debugDebug("Applying myrmex delay: "+ModConfig.iceandfire.myrmex_ai_delay);
		applyTickRate(entity.tasks, ModConfig.iceandfire.myrmex_ai_delay);
		applyTickRate(entity.targetTasks, ModConfig.iceandfire.myrmex_ai_delay);
		
		ErrorUtil.debugDebug("Current Tasks Count: "+entity.tasks.taskEntries.size());
		wrapTask(entity, entity.tasks, ReflectorIAF.reflector.c_iceandfire_MyrmexAIEscortEntity, WrappedTaskMyrmexAIEscortEntity.class);
		wrapTask(entity, entity.tasks, ReflectorIAF.reflector.c_iceandfire_MyrmexAIForage, WrappedTaskMyrmexAIForage.class);
		wrapTask(entity, entity.tasks, ReflectorIAF.reflector.c_iceandfire_MyrmexAIFindHidingSpot, WrappedTaskMyrmexAIFindHidingSpot.class);
		wrapTask(entity, entity.tasks, ReflectorIAF.reflector.c_iceandfire_MyrmexAILeaveHive, WrappedTaskMyrmexAILeaveHive.class);
		wrapTask(entity, entity.tasks, ReflectorIAF.reflector.c_iceandfire_MyrmexAIMoveThroughHive, WrappedTaskMyrmexAIMoveThroughHive.class);
		wrapTask(entity, entity.tasks, ReflectorIAF.reflector.c_iceandfire_MyrmexAIReEnterHive, WrappedTaskMyrmexAIReEnterHive.class);
		ErrorUtil.debugDebug("Final Tasks Count: "+entity.tasks.taskEntries.size());
	}

}
