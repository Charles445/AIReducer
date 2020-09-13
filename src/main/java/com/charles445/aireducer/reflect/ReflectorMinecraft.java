package com.charles445.aireducer.reflect;

import java.lang.reflect.Field;

import com.charles445.aireducer.util.ReflectUtil;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAITasks;

public class ReflectorMinecraft
{
	public static ReflectorMinecraft reflector;
	
	public final Class c_rabbit_AIAvoidEntity;
	
	public final Field f_EntityLiving_navigator;
	public final Field f_EntityAITasks_tickRate;
	
	
	public final Field f_EntityAIAvoidEntity_entity;
	public final Field f_EntityAIAvoidEntity_classToAvoid;
	public final Field f_EntityAIAvoidEntity_avoidTargetSelector;
	public final Field f_EntityAIAvoidEntity_avoidDistance;
	public final Field f_EntityAIAvoidEntity_farSpeed;
	public final Field f_EntityAIAvoidEntity_nearSpeed;
	
	public ReflectorMinecraft() throws Exception
	{
		c_rabbit_AIAvoidEntity = Class.forName("net.minecraft.entity.passive.EntityRabbit$AIAvoidEntity");
		
		f_EntityLiving_navigator = ReflectUtil.findFieldAny(EntityLiving.class, "field_70699_by", "navigator");
		f_EntityAITasks_tickRate = ReflectUtil.findFieldAny(EntityAITasks.class, "field_75779_e", "tickRate");
		
		f_EntityAIAvoidEntity_entity = ReflectUtil.findFieldAny(EntityAIAvoidEntity.class, "field_75380_a", "entity");
		f_EntityAIAvoidEntity_classToAvoid = ReflectUtil.findFieldAny(EntityAIAvoidEntity.class, "field_181064_i", "classToAvoid");
		f_EntityAIAvoidEntity_avoidTargetSelector = ReflectUtil.findFieldAny(EntityAIAvoidEntity.class, "field_179510_i", "avoidTargetSelector");
		f_EntityAIAvoidEntity_avoidDistance = ReflectUtil.findFieldAny(EntityAIAvoidEntity.class, "field_179508_f", "avoidDistance");
		f_EntityAIAvoidEntity_farSpeed = ReflectUtil.findFieldAny(EntityAIAvoidEntity.class, "field_75378_b", "farSpeed");
		f_EntityAIAvoidEntity_nearSpeed = ReflectUtil.findFieldAny(EntityAIAvoidEntity.class, "field_75379_c", "nearSpeed");
		
		reflector = this;
	}
}
