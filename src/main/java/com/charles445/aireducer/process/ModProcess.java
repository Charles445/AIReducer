package com.charles445.aireducer.process;

import net.minecraft.entity.MobEntity;

public abstract class ModProcess
{	
	public abstract boolean canUse();
	
	public abstract void handle(MobEntity entity, String domain, String path);
	
}