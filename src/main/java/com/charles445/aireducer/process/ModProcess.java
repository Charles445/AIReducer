package com.charles445.aireducer.process;

import net.minecraft.entity.EntityLiving;

public abstract class ModProcess
{	
	public abstract void handle(EntityLiving entity, String domain, String path);
	
	public abstract boolean canUse();
}
