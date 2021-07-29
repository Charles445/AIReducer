package com.charles445.aireducer.routine;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import com.charles445.aireducer.AIReducer;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.google.common.collect.Sets;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;

public abstract class Routine
{
	public abstract boolean canRun();
	
	public void runRoutine(MobEntity entity, String domain, String path)
	{
		if(this.canRun())
			this.run(entity, domain, path);
	}
	
	protected abstract void run(MobEntity entity, String domain, String path);
	
	private Map<Class<?>, Constructor<?>> wrappedConstructorMap = new ConcurrentHashMap<Class<?>, Constructor<?>>();
	
	
	//Commonly used routines
	
	protected boolean removeAllTasksOfClass(MobEntity entity, Class<?> classToRemove)
	{
		//TODO replace completely
		
		boolean found = false;
		Set<PrioritizedGoal> availableGoals = ReflectorMinecraft.reflector.getAvailableGoals(entity.goalSelector);
		Iterator<PrioritizedGoal> iterator = availableGoals.iterator();
		while(iterator.hasNext())
		{
			PrioritizedGoal task = iterator.next();
			
			if(task.getGoal().getClass()==classToRemove)
			{
				found = true;
				iterator.remove();
			}
		}
		return found;
	}
	
	protected int tryAndReplaceAllTasks(MobEntity entity, GoalSelector goalSelector, Class<?> toMatch, Function<Goal,Goal> action)
	{
		int count = 0;
		Set<PrioritizedGoal> newAvailableGoals = Sets.newLinkedHashSet();
		
		Set<PrioritizedGoal> oldAvailableGoals = ReflectorMinecraft.reflector.getAvailableGoals(goalSelector);
		
		for(PrioritizedGoal entry : oldAvailableGoals)
		{
			//if(toMatch.isInstance(entry.getGoal()))
			if(toMatch == entry.getGoal().getClass())
			{
				Goal newAI = action.apply(entry.getGoal());
				
				if(newAI!=null)
				{
					newAvailableGoals.add(new PrioritizedGoal(entry.getPriority(), newAI));
					count++;
				}
				else
				{
					AIReducer.logger.warn("tryAndReplaceAllTasks ERROR "+entity.getClass().getName()+" "+toMatch.getClass().getName());
					newAvailableGoals.add(entry);
				}
			}
			else
			{
				newAvailableGoals.add(entry);
			}
		}
		
		if(count>0)
		{
			oldAvailableGoals.clear();
			oldAvailableGoals.addAll(newAvailableGoals);

			//DEBUG
			//AIReducer.logger.debug("Replaced "+count+" tasks of "+toMatch.getName()+" for "+entity.getName());
			//oldAvailableGoals.forEach(pgoal -> AIReducer.logger.debug(""+pgoal.getPriority()+" - "+pgoal.getGoal().toString()));
		}
		
		return count;
	}
	/*
	protected void wrapTask(EntityLiving entity, EntityAITasks tasks, Class clazz, Class<? extends WrappedTask> wrapperClazz)
	{
		tryAndReplaceAllTasks(entity, tasks, clazz, (Function<EntityAITaskEntry,EntityAIBase>) oldTaskEntry -> 
		{
			try
			{
				Constructor construct = wrappedConstructorMap.get(wrapperClazz);
				if(construct==null)
				{
					AIReducer.logger.info("Caching Wrapped Task Constructor: "+wrapperClazz.getName());
					construct = wrapperClazz.getDeclaredConstructor(EntityLiving.class, EntityAIBase.class);
					wrappedConstructorMap.put(wrapperClazz, construct);
				}
				
				WrappedTask wrapper = (WrappedTask) construct.newInstance(entity, oldTaskEntry.action);
				
				//entity.tasks.addTask(oldTaskEntry.priority, wrapper);
				ErrorUtil.debugDebug("Wrapped task with priority "+oldTaskEntry.priority+": "+oldTaskEntry.action.getClass().getName()+" "+wrapper.getClass().getName());
				return wrapper;
			}
			catch(Exception e)
			{
				AIReducer.logger.error("Failed to reflect to requested WrappedTask constructor!", e);
				return oldTaskEntry.action;
			}
		});
	}
	
	protected void debugPrintTasks(GoalSelector tasks)
	{
		MinecraftReflector.
		for(EntityAITaskEntry entry : tasks.taskEntries)
		{
			ErrorUtil.debugDebug("["+entry.priority+", "+entry.action.getClass().getName()+"]");
		}
	}
	*/
}
