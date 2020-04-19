package com.charles445.aireducer.handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.annotation.Nullable;

import com.charles445.aireducer.AIReducer;
import com.charles445.aireducer.ai.AIAvoidReducedRabbit;
import com.charles445.aireducer.ai.WrappedTask;
import com.charles445.aireducer.ai.myrmex.PathNavigateMyrmexAlternate;
import com.charles445.aireducer.ai.myrmex.ReflectMyrmex;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAIEscortEntity;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAIFindHidingSpot;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAIForage;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAILeaveHive;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAIMoveThroughHive;
import com.charles445.aireducer.ai.myrmex.WrappedTaskMyrmexAIReEnterHive;
import com.charles445.aireducer.compat.iceandfire.VillagerAIFearUntamedReduced;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.util.ModNames;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpawnHandler
{
	//TODO This is messy! It needs organizing
	
	public boolean loaded_iceandfire;
	public boolean loaded_minecraft;
	public boolean can_apply_tickrate;
	
	public Field f_EntityLiving_navigator;
	public Field f_tickRate;
	
	public Class c_rabbit_AIAvoidEntity;
	
	public Class c_iceandfire_VillagerAIFearUntamed;
	public Class c_iceandfire_IAnimalFear;
	public Method m_iceandfire_shouldAnimalsFear;
	public Class c_iceandfire_IVillagerFear;
	public Class c_iceandfire_DragonUtils;
	public Method m_iceandfire_isLivestock;
	public Method m_iceandfire_isVillager;
	
	public Class c_iceandfire_MyrmexAIForage;
	public Class c_iceandfire_MyrmexAIFindHidingSpot;
	public Class c_iceandfire_MyrmexAILeaveHive;
	public Class c_iceandfire_MyrmexAIMoveThroughHive;
	public Class c_iceandfire_MyrmexAIReEnterHive;
	public Class c_iceandfire_MyrmexAIEscortEntity;
	
	public Map<Class, Constructor> wrappedConstructorMap;
	
	public SpawnHandler()
	{
		loaded_iceandfire = Loader.isModLoaded(ModNames.ICEANDFIRE);
		loaded_minecraft = true;
		wrappedConstructorMap = new HashMap<Class, Constructor>();
		
		try
		{
			try
			{
				f_tickRate = EntityAITasks.class.getDeclaredField("field_75779_e");
			}
			catch(Exception e) //Attempt deobfuscated 
			{
				f_tickRate = EntityAITasks.class.getDeclaredField("tickRate");
			}
			
			f_tickRate.setAccessible(true);
			
			try
			{
				f_EntityLiving_navigator = EntityLiving.class.getDeclaredField("field_70699_by");
			}
			catch(Exception e) //Attempt deobfuscated
			{
				f_EntityLiving_navigator = EntityLiving.class.getDeclaredField("navigator");
			}
			
			f_EntityLiving_navigator.setAccessible(true);
		}
		catch(Exception e)
		{
			AIReducer.logger.error("Failed to setup tickrate tweaking in SpawnHandler!",e);
			f_tickRate = null;
		}
		
		try
		{
			//Vanilla
			c_rabbit_AIAvoidEntity = Class.forName("net.minecraft.entity.passive.EntityRabbit$AIAvoidEntity");
		}
		catch (Exception e)
		{
			AIReducer.logger.error("Failed to setup vanilla reflection in SpawnHandler!",e);
			loaded_minecraft = false;
		}
		
		if(loaded_iceandfire)
		{
			try
			{
				//Ice and Fire
				c_iceandfire_VillagerAIFearUntamed = Class.forName("com.github.alexthe666.iceandfire.entity.ai.VillagerAIFearUntamed");
				c_iceandfire_IAnimalFear = Class.forName("com.github.alexthe666.iceandfire.entity.IAnimalFear");
				m_iceandfire_shouldAnimalsFear = c_iceandfire_IAnimalFear.getDeclaredMethod("shouldAnimalsFear",Entity.class);
				c_iceandfire_IVillagerFear = Class.forName("com.github.alexthe666.iceandfire.entity.IVillagerFear");
				c_iceandfire_DragonUtils = Class.forName("com.github.alexthe666.iceandfire.entity.DragonUtils");
				m_iceandfire_isLivestock = c_iceandfire_DragonUtils.getDeclaredMethod("isLivestock",Entity.class);
				m_iceandfire_isVillager = c_iceandfire_DragonUtils.getDeclaredMethod("isVillager", Entity.class);
				
				c_iceandfire_MyrmexAIForage = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIForage");
				c_iceandfire_MyrmexAIFindHidingSpot = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIFindHidingSpot");
				c_iceandfire_MyrmexAILeaveHive = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAILeaveHive");
				c_iceandfire_MyrmexAIMoveThroughHive = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIMoveThroughHive");
				c_iceandfire_MyrmexAIReEnterHive = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIReEnterHive");
				c_iceandfire_MyrmexAIEscortEntity = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIEscortEntity");
				
				new ReflectMyrmex();
			}
			catch(Exception e)
			{
				AIReducer.logger.error("Failed to setup iceandfire reflection in SpawnHandler!",e);
				loaded_iceandfire = false;
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if(!ModConfig.enabled)
			return;
		
		if(event.getWorld().isRemote)
			return;
		
		Entity rawEntity = event.getEntity();
		if(rawEntity instanceof EntityLiving)
		{
			EntityLiving entity = (EntityLiving)rawEntity;
			ResourceLocation id = EntityList.getKey(entity.getClass());
			if(id==null)
				return;
			String domain = id.getResourceDomain();
			String path = id.getResourcePath();
			
			
			//Handle broader things first
			if(loaded_iceandfire)
			{
				try
				{
					if(ModConfig.iceandfire.replaceVillageAIFearUntamed)
					{
						if((boolean)m_iceandfire_isLivestock.invoke(null, entity))
						{
							tryReplaceIceAndFireLivestockFearTask(entity);
						}
						else if((boolean)m_iceandfire_isVillager.invoke(null, entity))
						{
							tryReplaceIceAndFireVillagerFearTask(entity);
						}
					}
				}
				catch(Exception e)
				{
					AIReducer.debugError("Failed to check isLivestock!", e);
				}
			}
			
				
			//Handle specific cases
			if(loaded_minecraft && domain.equals(ModNames.MINECRAFT))
			{
				handleVanilla(entity,path);
			}
			else if(loaded_iceandfire && domain.equals(ModNames.ICEANDFIRE))
			{
				handleIceAndFire(entity,path);
			}
		}
	}
	
	private void handleIceAndFire(EntityLiving entity, String path)
	{
		switch(path)
		{
			case "deathworm":
				handleDeathWorm(entity);break;
			case "myrmex_worker":
			case "myrmex_soldier":
			case "myrmex_sentinel":
			case "myrmex_royal":
			case "myrmex_queen":
				handleMyrmex(entity, path);break;
			
			default: break;
		}
	}
	
	private void handleDeathWorm(EntityLiving entity)
	{
		if(!ModConfig.iceandfire.deathworm)
			return;
		
		AIReducer.debugDebug("Applying deathworm delay: "+ModConfig.iceandfire.deathworm_ai_delay);
		applyTickRate(entity.tasks, ModConfig.iceandfire.deathworm_ai_delay);
		applyTickRate(entity.targetTasks, ModConfig.iceandfire.deathworm_ai_delay);
	}
	
	private void handleMyrmex(EntityLiving entity, String path)
	{
		if(!ModConfig.iceandfire.myrmex)
			return;
		
		if(ModConfig.iceandfire.myrmexAlternateNavigation && !path.equals("myrmex_royal") && !path.equals("myrmex_queen"))
		{
			AIReducer.debugDebug("Swapping out myrmex navigator");
			setNavigator(entity, new PathNavigateMyrmexAlternate(entity, entity.getEntityWorld()));
		}
		
		entity.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(ModConfig.iceandfire.myrmexFollowRange);
		
		AIReducer.debugDebug("Applying myrmex delay: "+ModConfig.iceandfire.myrmex_ai_delay);
		applyTickRate(entity.tasks, ModConfig.iceandfire.myrmex_ai_delay);
		applyTickRate(entity.targetTasks, ModConfig.iceandfire.myrmex_ai_delay);
		
		AIReducer.debugDebug("Current Tasks Count: "+entity.tasks.taskEntries.size());
		wrapTask(entity, entity.tasks, c_iceandfire_MyrmexAIEscortEntity, WrappedTaskMyrmexAIEscortEntity.class);
		wrapTask(entity, entity.tasks, c_iceandfire_MyrmexAIForage, WrappedTaskMyrmexAIForage.class);
		wrapTask(entity, entity.tasks, c_iceandfire_MyrmexAIFindHidingSpot, WrappedTaskMyrmexAIFindHidingSpot.class);
		wrapTask(entity, entity.tasks, c_iceandfire_MyrmexAILeaveHive, WrappedTaskMyrmexAILeaveHive.class);
		wrapTask(entity, entity.tasks, c_iceandfire_MyrmexAIMoveThroughHive, WrappedTaskMyrmexAIMoveThroughHive.class);
		wrapTask(entity, entity.tasks, c_iceandfire_MyrmexAIReEnterHive, WrappedTaskMyrmexAIReEnterHive.class);
		AIReducer.debugDebug("Final Tasks Count: "+entity.tasks.taskEntries.size());
	}
	/*
	private void wrapTaskMyrmex(EntityLiving entity, Class clazz)
	{
		EntityAITaskEntry oldTaskEntry = getAndRemoveTaskOfClass(entity, clazz);
		if(oldTaskEntry!=null)
		{
			entity.tasks.addTask(oldTaskEntry.priority, new WrappedTaskMyrmexAIForage(entity, oldTaskEntry.action));
			AIReducer.debugDebug("Wrapped Myrmex task with priority "+oldTaskEntry.priority+": "+oldTaskEntry.action.getClass().getName());
		}
	}
	*/
	
	private boolean removeAllTasksOfClass(EntityLiving entity, Class classToRemove)
	{
		//TODO replace completely
		
		boolean found = false;
		Iterator<EntityAITaskEntry> iterator = entity.tasks.taskEntries.iterator();
		while(iterator.hasNext())
		{
			EntityAITaskEntry task = iterator.next();
			
			if(task.action.getClass()==classToRemove)
			{
				AIReducer.debugDebug("Removing "+classToRemove.getName()+" from "+entity.getClass().getName());
				found = true;
				iterator.remove();
			}
		}
		return found;
	}
	
	private void tryAndReplaceAllTasks(EntityLiving entity, EntityAITasks tasks, Class toMatch, Function<EntityAITaskEntry,EntityAIBase> action)
	{
		int count = 0;
		LinkedList<EntityAITaskEntry> entries = new LinkedList<>();
		for(EntityAITaskEntry entry : tasks.taskEntries)
		{
			if(toMatch.isInstance(entry.action))
			{
				entries.add(tasks.new EntityAITaskEntry(entry.priority, action.apply(entry)));
				count++;
			}
			else
			{
				entries.add(entry);
			}
		}
		if(count>0)
		{
			AIReducer.debugDebug("Replacing "+count+" tasks of "+toMatch.getName());
			tasks.taskEntries.clear();
			tasks.taskEntries.addAll(entries);
		}
		//Don't do anything if there were no matches
		
		/*
		//DEBUG

		for(EntityAITaskEntry entry : tasks.taskEntries)
		{
			AIReducer.debugDebug("["+entry.priority+", "+entry.action.getClass().getName()+"]");
		}
		
		*/
	}
	
	private void wrapTask(EntityLiving entity, EntityAITasks tasks, Class clazz, Class<? extends WrappedTask> wrapperClazz)
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
				AIReducer.debugDebug("Wrapped task with priority "+oldTaskEntry.priority+": "+oldTaskEntry.action.getClass().getName()+" "+wrapper.getClass().getName());
				return wrapper;
			}
			catch(Exception e)
			{
				AIReducer.logger.error("Failed to reflect to requested WrappedTask constructor!", e);
				return oldTaskEntry.action;
			}
		});
		
		/*
		EntityAITaskEntry oldTaskEntry = getAndRemoveTaskOfClass(entity,clazz);
		if(oldTaskEntry!=null)
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
				
				entity.tasks.addTask(oldTaskEntry.priority, wrapper);
				
				AIReducer.debugDebug("Wrapped task with priority "+oldTaskEntry.priority+": "+oldTaskEntry.action.getClass().getName()+" "+wrapper.getClass().getName());
			}
			catch(Exception e)
			{
				AIReducer.logger.error("Failed to reflect to requested WrappedTask constructor!", e);
			}
		}
		*/
	}

	private void handleVanilla(EntityLiving entity, String path)
	{
		if(entity instanceof EntityCreature)
		{
			switch(path)
			{
				case "rabbit": 
					handleVanillaRabbit((EntityCreature)entity); 
					break;
				
				default: break;
			}
		}
	}
	
	private void handleVanillaRabbit(EntityCreature entity)
	{
		if(!ModConfig.vanilla.rabbit)
			return;
			
		EntityRabbit entityRabbit = (EntityRabbit)entity;
		
		applyTickRate(entityRabbit.tasks, ModConfig.vanilla.rabbit_ai_delay);
		
		
		//TODO probably broken, should fix later
		if(removeAllTasksOfClass(entityRabbit, c_rabbit_AIAvoidEntity))
		{
			//Couldn't tell how much performance consolidating these saved, I do have the code lying around to do it though, inaccurately however
			entityRabbit.tasks.addTask(4, new AIAvoidReducedRabbit(entityRabbit, EntityPlayer.class, 8.0F, 2.2D, 2.2D));
			entityRabbit.tasks.addTask(4, new AIAvoidReducedRabbit(entityRabbit, EntityWolf.class, 10.0F, 2.2D, 2.2D));
			entityRabbit.tasks.addTask(4, new AIAvoidReducedRabbit(entityRabbit, EntityMob.class, 4.0F, 2.2D, 2.2D));
		}
		
	}
	
	private void tryReplaceIceAndFireVillagerFearTask(EntityLiving entity)
	{
		//Just in case
		if(entity instanceof EntityCreature)
		{
			tryAndReplaceAllTasks(entity, entity.tasks, c_iceandfire_VillagerAIFearUntamed, task -> makeIceAndFireVillagerFearTask((EntityCreature)entity));
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
	            	return targentity != null && c_iceandfire_IVillagerFear.isInstance(targentity);         	
	            }
			},12.0f, 0.8, 0.8);
	}
	
	private void tryReplaceIceAndFireLivestockFearTask(EntityLiving entity)
	{
		//Just in case
		if(entity instanceof EntityCreature)
		{
			tryAndReplaceAllTasks(entity, entity.tasks, c_iceandfire_VillagerAIFearUntamed, task -> makeIceAndFireLivestockFearTask((EntityCreature)entity));
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
		                return targentity != null && c_iceandfire_IAnimalFear.isInstance(targentity) && (boolean)m_iceandfire_shouldAnimalsFear.invoke(targentity, entity);
	        		}
	        		catch(Exception e)
	        		{
	        			AIReducer.debugError("Failed to makeIceAndFireLivestockFearTask!", e);
	        			return false;
	        		}           	
	            }
			},12.0f,1.2,1.5);
	}
	
	/*
	private boolean removeAllTasksOfClass(EntityLiving entity, Class classToRemove)
	{
		boolean found = false;
		Iterator<EntityAITaskEntry> iterator = entity.tasks.taskEntries.iterator();
		while(iterator.hasNext())
		{
			EntityAITaskEntry task = iterator.next();
			
			if(task.action.getClass()==classToRemove)
			{
				AIReducer.debugDebug("Removing "+classToRemove.getName()+" from "+entity.getClass().getName());
				found = true;
				iterator.remove();
			}
		}
		return found;
	}
	*/
	/*
	@Nullable
	private EntityAITaskEntry getAndRemoveTaskOfClass(EntityLiving entity, Class classToRemove)
	{
		Iterator<EntityAITaskEntry> iterator = entity.tasks.taskEntries.iterator();
		
		EntityAITaskEntry taskToRemove = null;
		
		while(iterator.hasNext())
		{
			EntityAITaskEntry task = iterator.next();
			
			if(task.action.getClass()==classToRemove)
			{
				taskToRemove = task;
				break;
			}
		}
		
		if(taskToRemove!=null)
		{
			AIReducer.debugDebug("Removing "+classToRemove.getName()+" from "+entity.getClass().getName());
			entity.tasks.removeTask(taskToRemove.action);
			return taskToRemove;
		}
		
		return null;
	}
	*/
	private void setNavigator(EntityLiving entity, PathNavigate navigator)
	{
		try
		{
			f_EntityLiving_navigator.set(entity, navigator);
		}
		catch(Exception e)
		{
			//TODO
		}
	}
	
	private void applyTickRate(EntityAITasks tasks, int tickRate)
	{
		try
		{
			f_tickRate.setInt(tasks, tickRate);
		}
		catch (Exception e)
		{
			//TODO maybe make this silent or run once
			//AIReducer.logger.error("Failed to applyTickRate!", e);
		}
	}
}
