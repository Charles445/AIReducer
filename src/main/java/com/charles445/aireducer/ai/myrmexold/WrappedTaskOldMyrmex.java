package com.charles445.aireducer.ai.myrmexold;

import com.charles445.aireducer.ai.WrappedTask;
import com.charles445.aireducer.reflect.ReflectorIAFOld;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class WrappedTaskOldMyrmex extends WrappedTask
{
	ReflectorIAFOld reflector;
	
	public WrappedTaskOldMyrmex(EntityLiving entity, EntityAIBase task)
	{
		super(entity, task);
		reflector = ReflectorIAFOld.reflector;
	}
}
