package com.charles445.aireducer.ai.myrmex;

import com.charles445.aireducer.ai.WrappedTask;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskMyrmex extends WrappedTask
{
	ReflectMyrmex reflector;
	
	public WrappedTaskMyrmex(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
		reflector = ReflectMyrmex.reflector;
	}
}
