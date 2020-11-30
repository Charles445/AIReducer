package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskMyrmexAIFindHidingSpot extends WrappedTaskMyrmexRunDelay
{
	public WrappedTaskMyrmexAIFindHidingSpot(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}

	@Override
	public boolean canModify()
	{
		return ModConfig.iceandfire.myrmexModifyFindHidingSpot;
	}

	@Override
	public int getRunDelay()
	{
		return ModConfig.iceandfire.myrmexRunDelayFindHidingSpot;
	}
	
	@Override
	public double getUpdateChance()
	{
		return ModConfig.iceandfire.myrmexUpdateChanceFindHidingSpot;
	}
}
