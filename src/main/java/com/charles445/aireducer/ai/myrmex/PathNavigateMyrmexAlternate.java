package com.charles445.aireducer.ai.myrmex;

import java.lang.reflect.Field;
import java.util.Set;

import javax.annotation.Nullable;

import com.charles445.aireducer.debug.DebugUtil;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;

public class PathNavigateMyrmexAlternate extends PathNavigateGround
{
	//Essentially a port of the newer myrmex navigation in order to mitigate the performance loss from poor pathfinding
	
	//Most noticeable with sentinels at home failing to leave and causing tons of chunk loading and lag
	
	public PathNavigateMyrmexAlternate(EntityLiving entitylivingIn, World worldIn)
	{
		super(entitylivingIn, worldIn);
	}
	
	//Vanilla but they can swim
	protected PathFinder getPathFinder()
	{
		this.nodeProcessor = new WalkNodeProcessor();
		this.nodeProcessor.setCanEnterDoors(true);
		this.nodeProcessor.setCanSwim(true);
		return new PathFinder(this.nodeProcessor);
	}
	
	@Override
	protected void pathFollow()
	{
		Vec3d vec3d = this.getEntityPosition();
		int i = this.currentPath.getCurrentPathLength();

		for (int j = this.currentPath.getCurrentPathIndex(); j < this.currentPath.getCurrentPathLength(); ++j)
		{
			if ((double)this.currentPath.getPathPointFromIndex(j).y != Math.floor(vec3d.y))
			{
				i = j;
				break;
			}
		}
		
		//Max distance to waypoint is just their width
		this.maxDistanceToWaypoint = this.entity.width;
		
		Vec3d vec3d1 = this.currentPath.getCurrentPos();
		
		//Mostly the same, but the Y maximum distance is bumped from 1.0D to 1.1D
		//I want them to be able to go up 2 blocks though
		//TODO that.
		
		if (MathHelper.abs((float)(this.entity.posX - (vec3d1.x + 0.5D))) < this.maxDistanceToWaypoint && MathHelper.abs((float)(this.entity.posZ - (vec3d1.z + 0.5D))) < this.maxDistanceToWaypoint && (float)Math.abs(this.entity.posY - vec3d1.y) <= 1.1F)
		{
			this.currentPath.setCurrentPathIndex(this.currentPath.getCurrentPathIndex() + 1);
		}
		
		int k = MathHelper.ceil(this.entity.width);
		int l = MathHelper.ceil(this.entity.height);
		int i1 = k;
		
		for (int j1 = i - 1; j1 >= this.currentPath.getCurrentPathIndex(); --j1)
		{
			if (this.isDirectPathBetweenPoints(vec3d, this.currentPath.getVectorFromIndex(this.entity, j1), k, l, i1))
			{
				this.currentPath.setCurrentPathIndex(j1);
				break;
			}
		}
		
		this.checkForStuck(vec3d);
	}
}
