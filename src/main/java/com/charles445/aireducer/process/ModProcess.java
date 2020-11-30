package com.charles445.aireducer.process;

import net.minecraft.entity.EntityLiving;

public abstract class ModProcess
{	
	public abstract boolean canUse();
	
	public abstract void handle(EntityLiving entity, String domain, String path);
	
}
