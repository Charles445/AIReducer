package com.charles445.aireducer.goals;

import net.minecraft.entity.MobEntity;

public interface IRadiusGoal 
{
	public boolean getRadiusConfig();
	
	public default double getMaxEntityRadius(MobEntity mob)
	{
		if(this.getRadiusConfig())
			return 2.0d;
		
		return mob.level.getMaxEntityRadius();
	}
}
