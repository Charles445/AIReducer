package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskMyrmexAIEscortEntity extends WrappedTaskMyrmexRunDelay
{
	public WrappedTaskMyrmexAIEscortEntity(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}

	@Override
	public boolean canModify()
	{
		return ModConfig.iceandfire.myrmexModifyEscortEntity;
	}

	@Override
	public int getRunDelay()
	{
		return ModConfig.iceandfire.myrmexRunDelayEscortEntity;
	}
	
	@Override
	public double getUpdateChance()
	{
		return ModConfig.iceandfire.myrmexUpdateChanceEscortEntity;
	}
}
