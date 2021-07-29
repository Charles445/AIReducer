package com.charles445.aireducer.util;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.AbstractChunkProvider;
import net.minecraft.world.chunk.Chunk;

public class WorldRadiusUtil
{
	//Utility to provide custom entity radius options
	
	//No reflection needed this time around...
	
	/**getNearestLoadedEntity(Class<? extends T>, EntityPredicate, LivingEntity, double, double, double, AxisAlignedBB)*/
	@Nullable
	public static <T extends LivingEntity> T worldGetNearestLoadedEntity(double entityRadius, World world, Class<? extends T> chosenClazz, EntityPredicate targetingPredicate, @Nullable LivingEntity self, double posX, double posY, double posZ, AxisAlignedBB boundingBox)
	{
		return world.getNearestEntity(worldGetLoadedEntitiesOfClass(entityRadius, world, chosenClazz, boundingBox, (Predicate<? super T>)null), targetingPredicate, self, posX, posY, posZ);
	}
	
	/**getEntitiesOfClass(Class<? extends T>, AxisAlignedBB)**/
	public static <T extends Entity> List<T> worldGetEntitiesOfClass(double entityRadius, World world, Class<? extends T> p_217357_1_, AxisAlignedBB p_217357_2_)
	{
		return worldGetEntitiesOfClass(entityRadius, world, p_217357_1_, p_217357_2_, EntityPredicates.NO_SPECTATORS);
	}
	
	/**getLoadedEntitiesOfClass(Class<? extends T>, AxisAlignedBB)**/
	public static <T extends Entity> List<T> worldGetLoadedEntitiesOfClass(double entityRadius, World world, Class<? extends T> chosenClazz, AxisAlignedBB boundingBox)
	{
		return worldGetLoadedEntitiesOfClass(entityRadius, world, chosenClazz, boundingBox, EntityPredicates.NO_SPECTATORS);
	}
	
	/**getEntities(Entity, AxisAlignedBB, List<Entity>, Predicate<? super Entity>)**/
	public static void chunkGetEntities(double entityRadius, Chunk chunk, @Nullable Entity excludedEntity, AxisAlignedBB boundingBox, List<Entity> returnList, @Nullable Predicate<? super Entity> testPredicate)
	{
		//TODO consider getting every time
		final ClassInheritanceMultiMap<Entity>[] entitySections = chunk.getEntitySections();
		
		int i = MathHelper.floor((boundingBox.minY - entityRadius) / 16.0D);
		int j = MathHelper.floor((boundingBox.maxY + entityRadius) / 16.0D);
		i = MathHelper.clamp(i, 0, entitySections.length - 1);
		j = MathHelper.clamp(j, 0, entitySections.length - 1);
		
		for(int k = i; k <= j; ++k)
		{
			ClassInheritanceMultiMap<Entity> classinheritancemultimap = entitySections[k];
			List<Entity> list = classinheritancemultimap.getAllInstances();
			int l = list.size();
			
			for(int i1 = 0; i1 < l; ++i1)
			{
				Entity entity = list.get(i1);
				if (entity.getBoundingBox().intersects(boundingBox) && entity != excludedEntity)
				{
					if (testPredicate == null || testPredicate.test(entity))
					{
						returnList.add(entity);
					}
					
					if (entity.isMultipartEntity())
					{
						for(net.minecraftforge.entity.PartEntity<?> enderdragonpartentity : entity.getParts()) 
						{
							if (enderdragonpartentity != excludedEntity && enderdragonpartentity.getBoundingBox().intersects(boundingBox) && (testPredicate == null || testPredicate.test(enderdragonpartentity)))
							{
								returnList.add(enderdragonpartentity);
							}
						}
					}
				}
			}
		}
	}
	
	/**getEntities(Entity, AxisAlignedBB, Predicate<? super Entity>)**/
	public static List<Entity> getEntities(double entityRadius, World world, @Nullable Entity excludedEntity, AxisAlignedBB boundingBox, @Nullable Predicate<? super Entity> testPredicate)
	{
		world.getProfiler().incrementCounter("getEntities");
		List<Entity> list = Lists.newArrayList();
		int i = MathHelper.floor((boundingBox.minX - entityRadius) / 16.0D);
		int j = MathHelper.floor((boundingBox.maxX + entityRadius) / 16.0D);
		int k = MathHelper.floor((boundingBox.minZ - entityRadius) / 16.0D);
		int l = MathHelper.floor((boundingBox.maxZ + entityRadius) / 16.0D);
		AbstractChunkProvider abstractchunkprovider = world.getChunkSource();
		
		for(int i1 = i; i1 <= j; ++i1)
		{
			for(int j1 = k; j1 <= l; ++j1)
			{
				Chunk chunk = abstractchunkprovider.getChunk(i1, j1, false);
				if (chunk != null)
				{
					chunkGetEntities(entityRadius, chunk, excludedEntity, boundingBox, list, testPredicate);
				}
			}
		}
		
		return list;
	}
	
	/**getEntities(EntityType<?>, AxisAlignedBB, List<? super T>, Predicate<? super T>)**/
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void chunkGetEntities(double entityRadius, Chunk chunk, @Nullable EntityType<?> entityType, AxisAlignedBB boundingBox, List<? super T> returnList, Predicate<? super T> testPredicate)
	{
		//TODO consider getting every time
		final ClassInheritanceMultiMap<Entity>[] entitySections = chunk.getEntitySections();
		
		int i = MathHelper.floor((boundingBox.minY - entityRadius) / 16.0D);
		int j = MathHelper.floor((boundingBox.maxY + entityRadius) / 16.0D);
		i = MathHelper.clamp(i, 0, entitySections.length - 1);
		j = MathHelper.clamp(j, 0, entitySections.length - 1);
		
		for(int k = i; k <= j; ++k)
		{
			for(Entity entity : entitySections[k].find(Entity.class))
			{
				if ((entityType == null || entity.getType() == entityType) && entity.getBoundingBox().intersects(boundingBox) && testPredicate.test((T)entity))
				{
					returnList.add((T)entity);
				}
			}
		}
	}
	
	/**getEntities(EntityType<T>, AxisAlignedBB, Predicate<? super T>)**/
	public static <T extends Entity> List<T> worldGetEntities(double entityRadius, World world, @Nullable EntityType<T> entityType, AxisAlignedBB boundingBox, Predicate<? super T> testPredicate)
	{
		world.getProfiler().incrementCounter("getEntities");
		int i = MathHelper.floor((boundingBox.minX - entityRadius) / 16.0D);
		int j = MathHelper.ceil((boundingBox.maxX + entityRadius) / 16.0D);
		int k = MathHelper.floor((boundingBox.minZ - entityRadius) / 16.0D);
		int l = MathHelper.ceil((boundingBox.maxZ + entityRadius) / 16.0D);
		List<T> list = Lists.newArrayList();
		
		for(int i1 = i; i1 < j; ++i1)
		{
			for(int j1 = k; j1 < l; ++j1)
			{
				Chunk chunk = world.getChunkSource().getChunk(i1, j1, false);
				if (chunk != null)
				{
					chunkGetEntities(entityRadius, chunk, entityType, boundingBox, list, testPredicate);
				}
			}
		}
		
		return list;
	}
	
	/** getEntitiesOfClass(Class<? extends T>, AxisAlignedBB, List<T>, Predicate<? super T>) **/
	public static <T extends Entity> void chunkGetEntitiesOfClass(double entityRadius, Chunk chunk, Class<? extends T> chosenClazz, AxisAlignedBB boundingBox, List<T> returnList, @Nullable Predicate<? super T> testPredicate)
	{
		//TODO consider getting every time
		final ClassInheritanceMultiMap<Entity>[] entitySections = chunk.getEntitySections();
		
		int i = MathHelper.floor((boundingBox.minY - entityRadius) / 16.0D);
		int j = MathHelper.floor((boundingBox.maxY + entityRadius) / 16.0D);
		i = MathHelper.clamp(i, 0, entitySections.length - 1);
		j = MathHelper.clamp(j, 0, entitySections.length - 1);
	
		for(int k = i; k <= j; ++k)
		{
			for(T t : entitySections[k].find(chosenClazz))
			{
				if (t.getBoundingBox().intersects(boundingBox) && (testPredicate == null || testPredicate.test(t)))
				{
					returnList.add(t);
				}
			}
		}
	}
	
	/** getEntitiesOfClass(Class<? extends T>, AxisAlignedBB, Predicate<? super T>) **/
	public static <T extends Entity> List<T> worldGetEntitiesOfClass(double entityRadius, World world, Class<? extends T> chosenClazz, AxisAlignedBB boundingBox, @Nullable Predicate<? super T> testPredicate)
	{
		world.getProfiler().incrementCounter("getEntities");
		int i = MathHelper.floor((boundingBox.minX - entityRadius) / 16.0D);
		int j = MathHelper.ceil((boundingBox.maxX + entityRadius) / 16.0D);
		int k = MathHelper.floor((boundingBox.minZ - entityRadius) / 16.0D);
		int l = MathHelper.ceil((boundingBox.maxZ + entityRadius) / 16.0D);
		List<T> list = Lists.newArrayList();
		AbstractChunkProvider abstractchunkprovider = world.getChunkSource();
		
		for(int i1 = i; i1 < j; ++i1)
		{
			for(int j1 = k; j1 < l; ++j1)
			{
				Chunk chunk = abstractchunkprovider.getChunk(i1, j1, false);
				if (chunk != null)
				{
					chunkGetEntitiesOfClass(entityRadius, chunk, chosenClazz, boundingBox, list, testPredicate);
				}
			}
		}
		
		return list;
	}
	
	/** getLoadedEntitiesOfClass(Class<? extends T>, AxisAlignedBB, Predicate<? super T>) **/
	public static <T extends Entity> List<T> worldGetLoadedEntitiesOfClass(double entityRadius, World world, Class<? extends T> chosenClazz, AxisAlignedBB boundingBox, @Nullable Predicate<? super T> testPredicate)
	{
		world.getProfiler().incrementCounter("getLoadedEntities");
		int i = MathHelper.floor((boundingBox.minX - entityRadius) / 16.0D);
		int j = MathHelper.ceil((boundingBox.maxX + entityRadius) / 16.0D);
		int k = MathHelper.floor((boundingBox.minZ - entityRadius) / 16.0D);
		int l = MathHelper.ceil((boundingBox.maxZ + entityRadius) / 16.0D);
		List<T> list = Lists.newArrayList();
		AbstractChunkProvider abstractchunkprovider = world.getChunkSource();
		
		for(int i1 = i; i1 < j; ++i1)
		{
			for(int j1 = k; j1 < l; ++j1)
			{
				Chunk chunk = abstractchunkprovider.getChunkNow(i1, j1);
				if (chunk != null)
				{
					chunkGetEntitiesOfClass(entityRadius, chunk, chosenClazz, boundingBox, list, testPredicate);
				}
			}
		}
		
		return list;
	}
}
