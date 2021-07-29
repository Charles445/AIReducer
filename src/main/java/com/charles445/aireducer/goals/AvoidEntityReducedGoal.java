package com.charles445.aireducer.goals;

import java.util.EnumSet;
import java.util.function.Predicate;

import com.charles445.aireducer.reflect.ReflectorMinecraft;
import com.charles445.aireducer.util.WorldRadiusUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.vector.Vector3d;

public abstract class AvoidEntityReducedGoal<T extends LivingEntity> extends Goal implements IRadiusGoal
{
	protected final CreatureEntity mob;
	private final double walkSpeedModifier;
	private final double sprintSpeedModifier;
	protected T toAvoid;
	protected final float maxDist;
	protected Path path;
	protected final PathNavigator pathNav;
	protected final Class<T> avoidClass;
	protected final Predicate<LivingEntity> avoidPredicate;
	protected final Predicate<LivingEntity> predicateOnAvoidEntity;
	private final EntityPredicate avoidEntityTargeting;
	
	@SuppressWarnings("unchecked")
	public AvoidEntityReducedGoal(AvoidEntityGoal<?> oldGoal) throws IllegalArgumentException, IllegalAccessException
	{
		this
		(
			(CreatureEntity) ReflectorMinecraft.reflector.f_AvoidEntityGoal_mob.get(oldGoal), //Safety first...
			(Class<T>) ReflectorMinecraft.reflector.f_AvoidEntityGoal_avoidClass.get(oldGoal),
			(Predicate<LivingEntity>) ReflectorMinecraft.reflector.f_AvoidEntityGoal_avoidPredicate.get(oldGoal),
			ReflectorMinecraft.reflector.f_AvoidEntityGoal_maxDist.getFloat(oldGoal),
			ReflectorMinecraft.reflector.f_AvoidEntityGoal_walkSpeedModifier.getDouble(oldGoal),
			ReflectorMinecraft.reflector.f_AvoidEntityGoal_sprintSpeedModifier.getDouble(oldGoal),
			(Predicate<LivingEntity>) ReflectorMinecraft.reflector.f_AvoidEntityGoal_predicateOnAvoidEntity.get(oldGoal)
		);
	}
	
	public AvoidEntityReducedGoal(CreatureEntity mobIn, Class<T> avoidClassIn, Predicate<LivingEntity> avoidPredicateIn, float maxDistIn, double walkSpeedModifierIn, double sprintSpeedModifierIn, Predicate<LivingEntity> predicateOnAvoidEntityIn)
	{
		this.mob = mobIn;
		this.avoidClass = avoidClassIn;
		this.avoidPredicate = avoidPredicateIn;
		this.maxDist = maxDistIn;
		this.walkSpeedModifier = walkSpeedModifierIn;
		this.sprintSpeedModifier = sprintSpeedModifierIn;
		this.predicateOnAvoidEntity = predicateOnAvoidEntityIn;
		this.pathNav = mobIn.getNavigation();
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		this.avoidEntityTargeting = (new EntityPredicate()).range((double)maxDistIn).selector(predicateOnAvoidEntityIn.and(avoidPredicateIn));
	}
	
	@Override
	public String toString()
	{
		return "AvoidEntityReducedGoal";
	}
	
	//Original Class

	@Override
	public boolean canUse()
	{
		//this.toAvoid = this.mob.level.getNearestLoadedEntity(this.avoidClass, this.avoidEntityTargeting, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ(), this.mob.getBoundingBox().inflate((double)this.maxDist, 3.0D, (double)this.maxDist));	
		this.toAvoid = WorldRadiusUtil.worldGetNearestLoadedEntity(this.getMaxEntityRadius(this.mob), this.mob.level, this.avoidClass, this.avoidEntityTargeting, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ(), this.mob.getBoundingBox().inflate((double)this.maxDist, 3.0D, (double)this.maxDist));
		
		if (this.toAvoid == null)
		{
			return false;
		}
		else
		{
			Vector3d vector3d = RandomPositionGenerator.getPosAvoid(this.mob, 16, 7, this.toAvoid.position());
			if (vector3d == null)
			{
				return false;
			} 
			else if (this.toAvoid.distanceToSqr(vector3d.x, vector3d.y, vector3d.z) < this.toAvoid.distanceToSqr(this.mob))
			{
				return false;
			} 
			else
			{
				this.path = this.pathNav.createPath(vector3d.x, vector3d.y, vector3d.z, 0);
				return this.path != null;
			}
		}
	}
	
	@Override
	public boolean canContinueToUse()
	{
		return !this.pathNav.isDone();
	}
	
	@Override
	public void start()
	{
		this.pathNav.moveTo(this.path, this.walkSpeedModifier);
	}
	
	@Override
	public void stop()
	{
		this.toAvoid = null;
	}
	
	@Override
	public void tick()
	{
		if (this.mob.distanceToSqr(this.toAvoid) < 49.0D)
		{
			this.mob.getNavigation().setSpeedModifier(this.sprintSpeedModifier);
		}
		else
		{
			this.mob.getNavigation().setSpeedModifier(this.walkSpeedModifier);
		}
	}
	
	
}
