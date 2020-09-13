package com.charles445.aireducer.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.charles445.aireducer.AIReducer;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.process.AnyProcess;
import com.charles445.aireducer.process.IAFProcess;
import com.charles445.aireducer.process.ModProcess;
import com.charles445.aireducer.process.VanillaProcess;
import com.charles445.aireducer.reflect.ReflectorIAF;
import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.util.ModNames;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpawnHandler
{
	public Map<String, ModProcess> modProcessMap;
	public ModProcess anyProcess;
	
	
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
		
		try
		{
			if(Loader.isModLoaded(ModNames.ICEANDFIRE))
				new ReflectorIAF();
		}
		catch(Exception e)
		{
			AIReducer.logger.error("Failed to setup ReflectorIAF!",e);
		}
		
		modProcessMap = new ConcurrentHashMap<String, ModProcess>();
		
		//ADD PROCESSES HERE
		anyProcess = new AnyProcess();
		modProcessMap.put(ModNames.MINECRAFT, new VanillaProcess());
		modProcessMap.put(ModNames.ICEANDFIRE, new IAFProcess());
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
			
			anyProcess.handle(entity, domain, path);
			
			ModProcess modProcess = modProcessMap.get(domain);
			if(modProcess!=null)
			{
				modProcess.handle(entity, domain, path);
			}
		}
		
	}
}
