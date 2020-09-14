package com.charles445.aireducer.ai;

import com.charles445.aireducer.config.ModConfig;
import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityRabbit;

public class AIAvoidReducedRabbit<T extends Entity> extends AIAvoidReduced<T>
{
	private final EntityRabbit rabbit;
	
	public AIAvoidReducedRabbit(EntityRabbit rabbit, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
	{
		super(rabbit, classToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
		this.rabbit = rabbit;
	}
	
	public AIAvoidReducedRabbit(EntityRabbit rabbit, Class<T> classToAvoidIn, Predicate <? super T> avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
	{
		super(rabbit, classToAvoidIn, avoidTargetSelectorIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
		this.rabbit = rabbit;
	}
	
	@Override
	public boolean getRadiusConfig()
	{
		//While this isn't the determining factor for whether this task gets run, this is the most relevant setting to it
		//TODO remove rabbit_should_avoid entirely
		return ModConfig.vanilla.avoidTaskReplacement;
	}
	
	@Override
	public boolean shouldExecute()
 	{
		return this.rabbit.getRabbitType() != 99 && super.shouldExecute();
	}
	
	@Override
	public int getShouldExecuteDelay()
	{
		return ModConfig.vanilla.rabbit_should_avoid;
	}
}
