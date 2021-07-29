package com.charles445.aireducer.goals;

import java.util.EnumSet;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.util.WorldRadiusUtil;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;

public abstract class NearestAttackableTargetReducedGoal<T extends LivingEntity> extends TargetGoal implements IRadiusGoal
{
	protected final Class<T> targetType;
	protected final int randomInterval;
	protected LivingEntity target;
	protected EntityPredicate targetConditions;
	
	@SuppressWarnings("unchecked")
	public NearestAttackableTargetReducedGoal(NearestAttackableTargetGoal<?> oldGoal) throws IllegalArgumentException, IllegalAccessException
	{
		this
		(
			(MobEntity) ReflectorMinecraft.reflector.f_TargetGoal_mob.get((TargetGoal)oldGoal), //Safety first...
			(Class<T>) ReflectorMinecraft.reflector.f_NearestAttackableTargetGoal_targetType.get(oldGoal),
			ReflectorMinecraft.reflector.f_NearestAttackableTargetGoal_randomInterval.getInt(oldGoal),
			ReflectorMinecraft.reflector.f_TargetGoal_mustSee.getBoolean((TargetGoal)oldGoal),
			ReflectorMinecraft.reflector.f_TargetGoal_mustReach.getBoolean((TargetGoal)oldGoal),
			(EntityPredicate) ReflectorMinecraft.reflector.f_NearestAttackableTargetGoal_targetConditions.get(oldGoal)
		);
	}
	
	//Constructor differs, accepts EntityPredicate directly instead of constructing its own from a selector
	public NearestAttackableTargetReducedGoal(MobEntity mobIn, Class<T> targetTypeIn, int randomIntervalIn, boolean mustSeeIn, boolean mustReachIn, EntityPredicate targetConditionsIn)
	{
		super(mobIn, mustSeeIn, mustReachIn);
		this.targetType = targetTypeIn;
		this.randomInterval = randomIntervalIn;
		this.setFlags(EnumSet.of(Goal.Flag.TARGET));
		this.targetConditions = targetConditionsIn;
	}
	
	@Override
	public String toString()
	{
		return "NearestAttackableTargetReducedGoal";
	}
	
	//Original Class
	
	@Override
	public boolean canUse()
	{
		if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0)
		{
			return false;
		}
		else
		{
			this.findTarget();
			return this.target != null;
		}
	}
	
	protected AxisAlignedBB getTargetSearchArea(double inflateXZIn)
	{
		return this.mob.getBoundingBox().inflate(inflateXZIn, 4.0D, inflateXZIn);
	}
	
	protected void findTarget()
	{
		if (this.targetType != PlayerEntity.class && this.targetType != ServerPlayerEntity.class)
		{
			//this.target = this.mob.level.getNearestLoadedEntity(this.targetType, this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ(), this.getTargetSearchArea(this.getFollowDistance()));
			this.target = WorldRadiusUtil.worldGetNearestLoadedEntity(this.getMaxEntityRadius(this.mob), this.mob.level, this.targetType, this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ(), this.getTargetSearchArea(this.getFollowDistance()));
		}
		else
		{
			this.target = this.mob.level.getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
		}
	}
	
	@Override
	public void start()
	{
		this.mob.setTarget(this.target);
		super.start();
	}
	
	public void setTarget(@Nullable LivingEntity targetIn)
	{
		this.target = targetIn;
	}
}
