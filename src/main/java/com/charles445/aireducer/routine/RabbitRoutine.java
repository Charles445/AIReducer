package com.charles445.aireducer.routine;

import com.charles445.aireducer.ai.AIAvoidReducedRabbit;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.reflect.ReflectorMinecraft;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;

public class RabbitRoutine extends Routine
{

	@Override
	public boolean canRun()
	{
		return ModConfig.vanilla.rabbit && ReflectorMinecraft.reflector != null;
	}

	@Override
	protected void run(EntityLiving entity, String domain, String path)
	{
		if(entity instanceof EntityRabbit)
		{
			EntityRabbit entityRabbit = (EntityRabbit)entity;
			
			//applyTickRate(entityRabbit.tasks, ModConfig.vanilla.rabbit_ai_delay);
			
			//TODO probably broken, should fix later
			if(removeAllTasksOfClass(entityRabbit, ReflectorMinecraft.reflector.c_rabbit_AIAvoidEntity))
			{
				//Couldn't tell how much performance consolidating these saved, I do have the code lying around to do it though, inaccurately however
				entityRabbit.tasks.addTask(4, new AIAvoidReducedRabbit(entityRabbit, EntityPlayer.class, 8.0F, 2.2D, 2.2D));
				entityRabbit.tasks.addTask(4, new AIAvoidReducedRabbit(entityRabbit, EntityWolf.class, 10.0F, 2.2D, 2.2D));
				entityRabbit.tasks.addTask(4, new AIAvoidReducedRabbit(entityRabbit, EntityMob.class, 4.0F, 2.2D, 2.2D));
			}
		}
	}
	
}
