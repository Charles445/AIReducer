package com.charles445.aireducer.compat.iceandfire;

import com.charles445.aireducer.ai.AIAvoidReduced;
import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class VillagerAIFearUntamedReduced<T extends Entity> extends AIAvoidReduced
{
	//This runs A LOT for some reason, like, an insane amount
	//Why?
	
	public VillagerAIFearUntamedReduced(final EntityCreature entityIn, final Class<T> classToAvoidIn, final float avoidDistanceIn, final double farSpeedIn, final double nearSpeedIn)
	{
		super(entityIn, classToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
	}
	
	public VillagerAIFearUntamedReduced(final EntityCreature entityIn, final Class<T> classToAvoidIn, final Predicate<? super T> avoidTargetSelectorIn, final float avoidDistanceIn, final double farSpeedIn, final double nearSpeedIn)
	{
		super(entityIn, classToAvoidIn, avoidTargetSelectorIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
	}
	
	@Override
	public boolean shouldExecute()
	{
		return super.shouldExecute() && (this.closestLivingEntity == null || !(this.closestLivingEntity instanceof EntityTameable) || !((EntityTameable)this.closestLivingEntity).isTamed());
	}
	
	@Override
	//Later IAF versions remove the need to see the target
	public boolean mustSee()
	{
		return false;
	}
}
