package com.charles445.aireducer.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.charles445.aireducer.AIReducer;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public class CollisionUtil
{
	public static final CollisionUtil instance = new CollisionUtil();
	
	public boolean isLessCollisionsEnabled = false;
	
	private final Map<String, Double> stringReferenceServer = new ConcurrentHashMap<String, Double>();
	private final Map<String, Double> stringReferenceClient = new ConcurrentHashMap<String, Double>();
	
	private final Map<Class<?>, Double> collisionMapServer = new ConcurrentHashMap<>();
	private final Map<Class<?>, Double> collisionMapClient = new ConcurrentHashMap<>();
	
	//(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
	public static Stream<VoxelShape> getEntityCollisions(World world, @Nullable Entity excludedEntity, AxisAlignedBB boundingBox, Predicate<Entity> testPredicate)
	{
		if(instance.isLessCollisionsEnabled && excludedEntity != null)
		{
			return WorldRadiusUtil.worldGetEntityCollisions(instance.getRadiusForEntity(excludedEntity), world, excludedEntity, boundingBox, testPredicate);
		}
		else
		{
			//Fall back to default
			return world.getEntityCollisions(excludedEntity, boundingBox, testPredicate);
		}
	}
	
	//(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/function/Predicate;)Ljava/util/List;
	public static List<Entity> getEntities(World world, @Nullable Entity pushingEntity, AxisAlignedBB boundingBox, @Nullable Predicate<? super Entity> testPredicate)
	{
		if(instance.isLessCollisionsEnabled && pushingEntity != null)
		{
			return WorldRadiusUtil.worldGetEntities(instance.getRadiusForEntity(pushingEntity), world, pushingEntity, boundingBox, testPredicate);
		}
		else
		{
			//Fall back to default
			return world.getEntities(pushingEntity, boundingBox, testPredicate);
		}
	}
	
	public static void addToStringReference(Map<String, Double> sent)
	{
		instance.stringReferenceServer.putAll(sent);
		instance.stringReferenceClient.putAll(sent);
	}
	
	public double getRadiusForEntity(@Nonnull Entity entity)
	{
		if(entity.level.isClientSide)
		{
			//Client
			Double dub = collisionMapClient.get(entity.getClass());
			if(dub!=null)
				return dub;
			
			dub = stringReferenceClient.get(entity.getClass().getName());
			
			if(dub==null)
			{
				dub = new Double(entity.level.getMaxEntityRadius());
			}
			
			AIReducer.logger.debug("Adding "+entity.getClass().getName()+" with radius "+dub+" to client");
			
			collisionMapClient.put(entity.getClass(), dub);
			
			return dub.doubleValue();
		}
		else
		{
			//Server
			Double dub = collisionMapServer.get(entity.getClass());
			if(dub!=null)
				return dub;
			
			dub = stringReferenceServer.get(entity.getClass().getName());
			
			if(dub==null)
			{
				dub = new Double(entity.level.getMaxEntityRadius());
			}
			
			AIReducer.logger.debug("Adding "+entity.getClass().getName()+" with radius "+dub+" to server");
			
			collisionMapServer.put(entity.getClass(), dub);
			
			return dub.doubleValue();
		}
	}
	
	
}
