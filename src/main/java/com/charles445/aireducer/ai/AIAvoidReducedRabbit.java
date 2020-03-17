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
	public boolean shouldExecute()
 	{
		return this.rabbit.getRabbitType() != 99 && super.shouldExecute();
	}
	
	@Override
	public int getShouldExecuteDelay()
	{
		//Default to instant
		return ModConfig.vanilla.rabbit_should_avoid;
	}
}
