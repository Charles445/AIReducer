package com.charles445.aireducer.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.charles445.aireducer.AIReducer;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.process.ModProcess;
import com.charles445.aireducer.process.VanillaProcess;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.util.ModNames;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpawnHandler
{
	public Map<String, ModProcess> modProcessMap;
	//public AnyProcess anyProcess;
	
	public SpawnHandler()
	{
		//SETUP REFLECTORS
		try
		{
			new ReflectorMinecraft();
		}
		catch(Exception e)
		{
			AIReducer.logger.error("Failed to setup ReflectorMinecraft!",e);
		}
		
		modProcessMap = new ConcurrentHashMap<String, ModProcess>();
		
		//ADD PROCESSES HERE
		
		//Any
		//anyProcess = new AnyProcess();
		
		//Vanilla
		modProcessMap.put(ModNames.MINECRAFT, new VanillaProcess());
		
	}
	
	//Low priority to run after mods like AI Improvements
	//As it would be bad to wrap / replace a task that AI Improvements was trying to remove, etc.
	@SubscribeEvent(priority = EventPriority.LOW)
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if(event.getWorld().isClientSide)
			return;

		if(!ModConfig.general.enabled.get())
			return;
		
		if(ReflectorMinecraft.reflector == null)
			return;
		
		Entity rawEntity = event.getEntity();
		
		if(rawEntity instanceof MobEntity)
		{
			MobEntity entity = (MobEntity)rawEntity;
			
			ResourceLocation id = EntityType.getKey(entity.getType());
			if(id==null)
				return;
			
			String domain = id.getNamespace();
			String path = id.getPath();
			
			//anyProcess.handle(entity, domain, path);
			
			ModProcess modProcess = modProcessMap.get(domain);
			if(modProcess!=null)
			{
				if(modProcess.canUse())
					modProcess.handle(entity, domain, path);
			}
		}
	}
}
