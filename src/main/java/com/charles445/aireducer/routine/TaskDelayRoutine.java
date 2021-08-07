package com.charles445.aireducer.routine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.charles445.aireducer.config.JsonConfig;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.util.ErrorUtil;

import net.minecraft.entity.EntityLiving;

public class TaskDelayRoutine extends Routine
{
	//Applies tickrate to mobs based on their json setting
	
	public Map<String,Integer> taskDelayMap;
	
	public TaskDelayRoutine()
	{
		this.taskDelayMap = new ConcurrentHashMap<>();
		this.taskDelayMap.putAll(JsonConfig.taskDelay);
	}
	
	@Override
	public boolean canRun()
	{
		return ModConfig.any.taskDelayTweaks && ReflectorMinecraft.reflector != null;
	}

	@Override
	protected void run(EntityLiving entity, String domain, String path)
	{
		String resource = domain + ':' + path;
		Integer override = this.taskDelayMap.get(resource);
		if(override != null && override > 3)
		{
			ErrorUtil.debugDebug("Applying "+resource+" delay: "+override);
			applyTickRate(entity.tasks, override);
			applyTickRate(entity.targetTasks, override);
		}
	}
}
