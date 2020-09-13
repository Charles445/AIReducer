package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.ai.WrappedTask;
import com.charles445.aireducer.reflect.ReflectorIAF;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskMyrmex extends WrappedTask
{
	ReflectorIAF reflector;
	
	public WrappedTaskMyrmex(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
		reflector = ReflectorIAF.reflector;
	}
}
