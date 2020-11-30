package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.config.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskMyrmexAILeaveHive extends WrappedTaskMyrmexRunDelay
{
	public WrappedTaskMyrmexAILeaveHive(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
	}

	@Override
	public boolean canModify()
	{
		return ModConfig.iceandfire.myrmexModifyLeaveHive;
	}

	@Override
	public int getRunDelay()
	{
		return ModConfig.iceandfire.myrmexRunDelayLeaveHive;
	}
	
	@Override
	public double getUpdateChance()
	{
		return ModConfig.iceandfire.myrmexUpdateChanceLeaveHive;
	}
}
