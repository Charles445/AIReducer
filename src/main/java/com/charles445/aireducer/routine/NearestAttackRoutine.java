package com.charles445.aireducer.routine;

import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.charles445.aireducer.AIReducer;
import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.goals.NearestAttackableTargetReducedGoal;
import com.charles445.aireducer.reflect.ReflectorMinecraft;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.NonTamedTargetGoal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;

public class NearestAttackRoutine extends Routine
{
	//Nearest Attack Routine for VANILLA
	
	@Override
	public boolean canRun()
	{
		return ModConfig.minecraft.nearestAttackTaskReplacement.get();
	}

	@Override
	protected void run(MobEntity entity, String domain, String path)
	{
		replaceAttackableAll(entity);
		
		switch(path)
		{
			case "cat":
				replaceTamed(entity);
				break;
			case "wolf":
				replaceTamed(entity);
				break;
			default:
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void replaceAttackableAll(MobEntity entity)
	{
		tryAndReplaceAllTasks(entity, entity.targetSelector, NearestAttackableTargetGoal.class, (Function<Goal,Goal>) oldGoal -> 
		{
			try
			{
				return new NearestAttackableTargetReducedGoal((NearestAttackableTargetGoal<?>) oldGoal)
				{
					@Override
					public boolean getRadiusConfig()
					{
						return ModConfig.minecraft.nearestAttackTaskReplacement.get();
					}
				};
			}
			catch(Exception e)
			{
				return null;
			}
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void replaceTamed(MobEntity entity)
	{
		if(!(entity instanceof TameableEntity))
			return;
		
		tryAndReplaceAllTasks(entity, entity.targetSelector, NonTamedTargetGoal.class, (Function<Goal,Goal>) oldGoal -> 
		{
			try
			{
				return new NearestAttackableTargetReducedGoal((NearestAttackableTargetGoal<?>) oldGoal)
				{
					private TameableEntity tamableMob;
					
					public Goal build() throws IllegalArgumentException, IllegalAccessException
					{
						this.tamableMob = (TameableEntity) ReflectorMinecraft.reflector.f_NonTamedTargetGoal_tamableMob.get(oldGoal); //Safety first...
						return this;
					}
					
					@Override
					public boolean getRadiusConfig()
					{
						return ModConfig.minecraft.nearestAttackTaskReplacement.get();
					}
					
					@Override
					public boolean canUse()
					{
						return !this.tamableMob.isTame() && super.canUse();
					}
					
					@Override
					public boolean canContinueToUse()
					{
						return this.targetConditions != null ? this.targetConditions.test(this.mob, this.target) : super.canContinueToUse();
					}
				}.build();
			}
			catch(Exception e)
			{
				return null;
			}
		});
	}
}
