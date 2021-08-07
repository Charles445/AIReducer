package com.charles445.aireducer.ai.myrmexold;

import com.charles445.aireducer.ai.WrappedProximityTask;
import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedProximityTaskOldMyrmex extends WrappedProximityTask
{
	public WrappedProximityTaskOldMyrmex(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}

	@Override
	public double getProximity()
	{
		return ModConfig.iceandfireold.myrmexProximityRequirement;
	}

	@Override
	public boolean canModify()
	{
		return ModConfig.iceandfireold.myrmexProximityRequired;
	}
}
