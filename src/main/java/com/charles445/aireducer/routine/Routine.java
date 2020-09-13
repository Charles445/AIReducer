package com.charles445.aireducer.routine;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.charles445.aireducer.AIReducer;
import com.charles445.aireducer.ai.WrappedTask;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.util.ErrorUtil;
import com.google.common.base.Function;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.pathfinding.PathNavigate;

public abstract class Routine
{
	public abstract boolean canRun();
	
	public void runRoutine(EntityLiving entity, String domain, String path)
	{
		if(this.canRun())
			this.run(entity, domain, path);
	}
	
	protected abstract void run(EntityLiving entity, String domain, String path);
	
	private Map<Class, Constructor> wrappedConstructorMap = new ConcurrentHashMap<Class, Constructor>();
	
	
	//Commonly used routines
	
	protected void applyTickRate(EntityAITasks tasks, int tickRate)
	{
		if(ReflectorMinecraft.reflector == null)
			return;
		
		try
		{
			ReflectorMinecraft.reflector.f_EntityAITasks_tickRate.setInt(tasks, tickRate);
		}
		catch (Exception e)
		{
			
		}
	}
	
	protected void setNavigator(EntityLiving entity, PathNavigate navigator)
	{
		if(ReflectorMinecraft.reflector == null)
			return;
		
		try
		{
			ReflectorMinecraft.reflector.f_EntityLiving_navigator.set(entity, navigator);
		}
		catch(Exception e)
		{
			
		}
	}
	
	protected boolean removeAllTasksOfClass(EntityLiving entity, Class classToRemove)
	{
		//TODO replace completely
		
		boolean found = false;
		Iterator<EntityAITaskEntry> iterator = entity.tasks.taskEntries.iterator();
		while(iterator.hasNext())
		{
			EntityAITaskEntry task = iterator.next();
			
			if(task.action.getClass()==classToRemove)
			{
				ErrorUtil.debugDebug("Removing "+classToRemove.getName()+" from "+entity.getClass().getName());
				found = true;
				iterator.remove();
			}
		}
		return found;
	}
	
	protected int tryAndReplaceAllTasks(EntityLiving entity, EntityAITasks tasks, Class toMatch, Function<EntityAITaskEntry,EntityAIBase> action)
	{
		int count = 0;
		LinkedList<EntityAITaskEntry> entries = new LinkedList<>();
		for(EntityAITaskEntry entry : tasks.taskEntries)
		{
			//if(toMatch.isInstance(entry.action))
			if(toMatch == entry.action.getClass())
			{
				EntityAIBase newAI = action.apply(entry);
				
				if(newAI!=null)
				{
					entries.add(tasks.new EntityAITaskEntry(entry.priority, action.apply(entry)));
					count++;
				}
				else
				{
					ErrorUtil.debugError("tryAndReplaceAllTasks ERROR "+entity.getClass().getName()+" "+toMatch.getClass().getName());
					entries.add(entry);
				}
			}
			else
			{
				entries.add(entry);
			}
		}
		if(count>0)
		{
			ErrorUtil.debugDebug("Replacing "+count+" tasks of "+toMatch.getName());
			tasks.taskEntries.clear();
			tasks.taskEntries.addAll(entries);
		}
		
		return count;
	}
	
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
	
	protected void debugPrintTasks(EntityAITasks tasks)
	{
		for(EntityAITaskEntry entry : tasks.taskEntries)
		{
			ErrorUtil.debugDebug("["+entry.priority+", "+entry.action.getClass().getName()+"]");
		}
	}
}