package com.charles445.aireducer.routine;

import java.util.function.Function;

import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.goals.AvoidEntityReducedGoal;
import com.charles445.aireducer.reflect.ReflectorMinecraft;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;

public class AvoidRoutine extends Routine
{
	//Avoid Routine for VANILLA
	
	@Override
	public boolean canRun()
	{
		return ModConfig.minecraft.avoidTaskReplacement.get();
	}

	@Override
	protected void run(MobEntity entity, String domain, String path)
	{
		if(entity instanceof CreatureEntity)
		{
			CreatureEntity mob = (CreatureEntity)entity;
			replaceAvoidsAll(mob);
			
			switch(path)
			{
				case "rabbit":
					replaceAvoidsRabbit(mob);
					break;
				case "wolf":
					replaceAvoidsWolf(mob);
					break;
				default:
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void replaceAvoidsAll(CreatureEntity mobIn)
	{
		//Duplicate the avoid task (same settings, just with a different class)
		tryAndReplaceAllTasks(mobIn, mobIn.goalSelector, AvoidEntityGoal.class, (Function<Goal,Goal>) oldGoal -> 
		{
			try
			{
				return new AvoidEntityReducedGoal((AvoidEntityGoal<?>)oldGoal)
				{
					@Override
					public boolean getRadiusConfig()
					{
						return ModConfig.minecraft.avoidTaskReplacement.get();
					}
				};
			}
			catch(Exception e)
			{
				return null;
			}
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void replaceAvoidsRabbit(CreatureEntity mobIn)
	{
		if(!(mobIn instanceof RabbitEntity))
			return;
		
		tryAndReplaceAllTasks(mobIn, mobIn.goalSelector, ReflectorMinecraft.reflector.c_RabbitEntity_AvoidEntityGoal, (Function<Goal,Goal>) oldGoal -> 
		{
			try
			{
				return new AvoidEntityReducedGoal((AvoidEntityGoal<?>)oldGoal)
				{
					private RabbitEntity rabbit;
					
					public Goal build() throws IllegalArgumentException, IllegalAccessException
					{
						this.rabbit = (RabbitEntity) ReflectorMinecraft.reflector.f_RabbitEntity_AvoidEntityGoal_rabbit.get(oldGoal); //Safety first...
						return this;
					}
					
					@Override
					public boolean getRadiusConfig()
					{
						return ModConfig.minecraft.avoidTaskReplacement.get();
					}
					
					@Override
					public boolean canUse()
					{
						return this.rabbit.getRabbitType() != 99 && super.canUse();
					}
				}.build();
			}
			catch(Exception e)
			{
				return null;
			}
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void replaceAvoidsWolf(CreatureEntity mobIn)
	{
		if(!(mobIn instanceof WolfEntity))
			return;
		
		tryAndReplaceAllTasks(mobIn, mobIn.goalSelector, ReflectorMinecraft.reflector.c_WolfEntity_AvoidEntityGoal, (Function<Goal,Goal>) oldGoal -> 
		{
			try
			{
				return new AvoidEntityReducedGoal((AvoidEntityGoal<?>)oldGoal)
				{
					private WolfEntity wolf;
					
					public Goal build() throws IllegalArgumentException, IllegalAccessException
					{
						this.wolf = (WolfEntity) ReflectorMinecraft.reflector.f_WolfEntity_AvoidEntityGoal_wolf.get(oldGoal); //Safety first...
						return this;
					}
					
					@Override
					public boolean getRadiusConfig()
					{
						return ModConfig.minecraft.avoidTaskReplacement.get();
					}
					
					@Override
					public boolean canUse()
					{
						if (super.canUse() && this.toAvoid instanceof LlamaEntity)
						{
							return !this.wolf.isTame() && this.avoidLlama((LlamaEntity)this.toAvoid);
						}
						else
						{
							return false;
						}
					}

					private boolean avoidLlama(LlamaEntity avoiderIn)
					{
						return avoiderIn.getStrength() >= this.wolf.getRandom().nextInt(5);
					}

					@Override
					public void start()
					{
						this.wolf.setTarget((LivingEntity)null);
						super.start();
					}
					
					@Override
					public void tick()
					{
						this.wolf.setTarget((LivingEntity)null);
						super.tick();
					}
					
				}.build();
			}
			catch(Exception e)
			{
				return null;
			}
		});
	}
}
