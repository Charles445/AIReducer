package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.ai.WrappedProximityTask;
import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedProximityTaskMyrmex extends WrappedProximityTask
{
	public WrappedProximityTaskMyrmex(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}

	@Override
	public double getProximity()
	{
		return ModConfig.iceandfire.myrmexProximityRequirement;
	}

	@Override
	public boolean canModify()
	{
		return ModConfig.iceandfire.myrmexProximityRequired;
	}
}
