package com.charles445.aireducer.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

public abstract class WrappedProximityTask extends WrappedTask
{
	public double proximitySq;
	
	public WrappedProximityTask(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
		this.proximitySq = getProximity() * getProximity();
	}
	
	public abstract boolean canModify();
	
	public abstract double getProximity();
	
	@Override
	public boolean shouldExecute()
	{
		if(canModify() && !isPlayerClose(entity))
			return false;
		
		return task.shouldExecute();
	}
	
	private boolean isPlayerClose(EntityLiving entity)
	{
		double eX = entity.posX;
		double eZ = entity.posZ;
		for(EntityPlayer player : entity.world.playerEntities)
		{
			if(player.isSpectator())
				continue;
				
			if(getDistanceSqXZ(eX, eZ, player.posX, player.posZ) <= proximitySq)
				return true;
		}
		return false;
	}
	
	private double getDistanceSqXZ(double ax, double az, double bx, double bz)
	{
		double xx = ax - bx;
		double zz = az - bz;
		return xx * xx + zz * zz;
	}
}
