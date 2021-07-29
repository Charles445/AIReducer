package com.charles445.aireducer.reflect;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Set;

import com.charles445.aireducer.AIReducer;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.NonTamedTargetGoal;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ReflectorMinecraft
{
	/**SpawnHandler does not run if this is null, so Processes and Routines assume nonnull**/
	public static ReflectorMinecraft reflector;
	
	public final Class<?> c_RabbitEntity_AvoidEntityGoal;
	public final Field f_RabbitEntity_AvoidEntityGoal_rabbit;
	
	public final Class<?> c_WolfEntity_AvoidEntityGoal;
	public final Field f_WolfEntity_AvoidEntityGoal_wolf;
	
	public final Field f_GoalSelector_availableGoals;
	
	public final Field f_AvoidEntityGoal_mob;
	public final Field f_AvoidEntityGoal_avoidClass;
	public final Field f_AvoidEntityGoal_avoidPredicate;
	public final Field f_AvoidEntityGoal_maxDist;
	public final Field f_AvoidEntityGoal_walkSpeedModifier;
	public final Field f_AvoidEntityGoal_sprintSpeedModifier;
	public final Field f_AvoidEntityGoal_predicateOnAvoidEntity;
	
	public final Field f_NearestAttackableTargetGoal_targetType;
	public final Field f_NearestAttackableTargetGoal_randomInterval;
	public final Field f_NearestAttackableTargetGoal_target;
	public final Field f_NearestAttackableTargetGoal_targetConditions;
	
	public final Field f_NonTamedTargetGoal_tamableMob;
	
	public final Field f_TargetGoal_mob;
	public final Field f_TargetGoal_mustSee;
	public final Field f_TargetGoal_mustReach;
	
	public ReflectorMinecraft() throws Exception
	{
		c_RabbitEntity_AvoidEntityGoal = findClass("net.minecraft.entity.passive.RabbitEntity$AvoidEntityGoal");
		f_RabbitEntity_AvoidEntityGoal_rabbit = findField(c_RabbitEntity_AvoidEntityGoal, "field_179511_d");
		
		c_WolfEntity_AvoidEntityGoal = findClass("net.minecraft.entity.passive.WolfEntity$AvoidEntityGoal");
		f_WolfEntity_AvoidEntityGoal_wolf = findField(c_WolfEntity_AvoidEntityGoal, "field_190856_d");
		
		f_GoalSelector_availableGoals = ObfuscationReflectionHelper.findField(GoalSelector.class, "field_220892_d");
		
		f_AvoidEntityGoal_mob = findField(AvoidEntityGoal.class, "field_75380_a");
		f_AvoidEntityGoal_avoidClass = findField(AvoidEntityGoal.class, "field_181064_i");
		f_AvoidEntityGoal_avoidPredicate = findField(AvoidEntityGoal.class, "field_179510_i");
		f_AvoidEntityGoal_maxDist = findField(AvoidEntityGoal.class, "field_179508_f");
		f_AvoidEntityGoal_walkSpeedModifier = findField(AvoidEntityGoal.class, "field_75378_b");
		f_AvoidEntityGoal_sprintSpeedModifier = findField(AvoidEntityGoal.class, "field_75379_c");
		f_AvoidEntityGoal_predicateOnAvoidEntity = findField(AvoidEntityGoal.class, "field_203784_k");
		
		f_NearestAttackableTargetGoal_targetType = findField(NearestAttackableTargetGoal.class, "field_75307_b");
		f_NearestAttackableTargetGoal_randomInterval = findField(NearestAttackableTargetGoal.class, "field_75308_c");
		f_NearestAttackableTargetGoal_target = findField(NearestAttackableTargetGoal.class, "field_75309_a");
		f_NearestAttackableTargetGoal_targetConditions = findField(NearestAttackableTargetGoal.class, "field_220779_d");
		
		f_NonTamedTargetGoal_tamableMob = findField(NonTamedTargetGoal.class, "field_75310_g");
		
		f_TargetGoal_mob = findField(TargetGoal.class, "field_75299_d");
		f_TargetGoal_mustSee = findField(TargetGoal.class, "field_75297_f");
		f_TargetGoal_mustReach = findField(TargetGoal.class, "field_75303_a");
		
		reflector = this;
		AIReducer.logger.info("ReflectorMinecraft setup was successful");
	}
	
	@SuppressWarnings("unchecked")
	public Set<PrioritizedGoal> getAvailableGoals(GoalSelector goalSelector)
	{
		try
		{
			return (Set<PrioritizedGoal>) f_GoalSelector_availableGoals.get(goalSelector);
		}
		catch (IllegalArgumentException | IllegalAccessException e)
		{
			AIReducer.logger.error("Failed getAvailableGoals", e);
			return Collections.emptySet();
		}
			
	}
	
	private Class<?> findClass(String clazzName) throws Exception
	{
		try
		{
			return Class.forName(clazzName);
		}
		catch (Exception e)
		{
			AIReducer.logger.error("findClass failed: "+clazzName);
			throw e;
		}
	}
	
	//Parameterization is necessary here or the compiler gets cranky
	private <E> Field findField(Class<E> clazz, String fieldName) throws Exception
	{
		try
		{
			return ObfuscationReflectionHelper.findField(clazz, fieldName);
		}
		catch(Exception e)
		{
			AIReducer.logger.error("findField failed: "+clazz.getName()+" "+fieldName);
			throw e;
		}
	}
}
