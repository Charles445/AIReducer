package com.charles445.aireducer.ai;

import java.util.List;

import javax.annotation.Nullable;

import com.charles445.aireducer.config.ModConfig;
import com.charles445.aireducer.debug.DebugUtil;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.Vec3d;

public class AIAvoidReduced<T extends Entity> extends EntityAIBase
{
	//NOTE - Extending classes should override getExecuteDelay with their corresponding static config value!
	
	private final Predicate<Entity> canBeSeenSelector;
    protected EntityCreature entity;
    private final double farSpeed;
    private final double nearSpeed;
    protected T closestLivingEntity;
    private final float avoidDistance;
    private Path path;
    private final PathNavigate navigation;
    private final Class<T> classToAvoid;
    private final Predicate <? super T > avoidTargetSelector;

    private int shouldExecuteDelay = 0;
    
    //Override me!
    public int getShouldExecuteDelay()
	{
		//Default to instant
		return 1;
	}
    
    public AIAvoidReduced(EntityCreature entityIn, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
    {
        this(entityIn, classToAvoidIn, Predicates.alwaysTrue(), avoidDistanceIn, farSpeedIn, nearSpeedIn);
    }

    public AIAvoidReduced(EntityCreature entityIn, Class<T> classToAvoidIn, Predicate <? super T> avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
    {
        this.canBeSeenSelector = new Predicate<Entity>()
        {
            public boolean apply(@Nullable Entity toApply)
            {
            	//Let's do some logging
            	/*
            	boolean bool = false;
            	long nano_1 = System.nanoTime();
            	bool = !EntityAIAvoidEntityReduced.this.entity.isOnSameTeam(toApply);
            	long nano_2 = System.nanoTime();
            	bool = EntityAIAvoidEntityReduced.this.entity.getEntitySenses().canSee(toApply);
            	long nano_3 = System.nanoTime();
            	
            	DebugUtil.messageAll("isOnSameTeam: "+(nano_2-nano_1));
            	DebugUtil.messageAll("canSee: "+(nano_3-nano_2));
            			*/
            	//Switched around predicate
                return toApply.isEntityAlive() && !AIAvoidReduced.this.entity.isOnSameTeam(toApply) && AIAvoidReduced.this.entity.getEntitySenses().canSee(toApply);
            }
        };
        this.entity = entityIn;
        this.classToAvoid = classToAvoidIn;
        this.avoidTargetSelector = avoidTargetSelectorIn;
        this.avoidDistance = avoidDistanceIn;
        this.farSpeed = farSpeedIn;
        this.nearSpeed = nearSpeedIn;
        this.navigation = entityIn.getNavigator();
        this.setMutexBits(1);
    }
    
    public boolean shouldExecute()
    {
    	//Insert
    	
    	shouldExecuteDelay--;
		if(shouldExecuteDelay > 0)
		{
			return false;
		}
		shouldExecuteDelay = getShouldExecuteDelay();
		
		//Back to vanilla
    	//Sort of
        //List<T> list = this.entity.world.<T>getEntitiesWithinAABB(this.classToAvoid, this.entity.getEntityBoundingBox().grow((double)this.avoidDistance, 3.0D, (double)this.avoidDistance), Predicates.and(EntitySelectors.CAN_AI_TARGET, this.canBeSeenSelector, this.avoidTargetSelector));
		//Rearranged, AGAIN
		//This is a huge boost in the event that the avoidTargetSelector predicate actually gets used, which it isn't in vanilla
		List<T> list = this.entity.world.<T>getEntitiesWithinAABB(this.classToAvoid, this.entity.getEntityBoundingBox().grow((double)this.avoidDistance, 3.0D, (double)this.avoidDistance), Predicates.and(EntitySelectors.CAN_AI_TARGET, this.avoidTargetSelector, this.canBeSeenSelector));

		
        if (list.isEmpty())
        {
            return false;
        }
        else
        {
            this.closestLivingEntity = list.get(0);
            Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

            if (vec3d == null)
            {
                return false;
            }
            else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq(this.entity))
            {
                return false;
            }
            else
            {
                this.path = this.navigation.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
                return this.path != null;
            }
        }
    }
    
    public boolean shouldContinueExecuting()
    {
        return !this.navigation.noPath();
    }
    
    public void startExecuting()
    {
        this.navigation.setPath(this.path, this.farSpeed);
    }
    
    public void resetTask()
    {
        this.closestLivingEntity = null;
    }

    public void updateTask()
    {
        if (this.entity.getDistanceSq(this.closestLivingEntity) < 49.0D)
        {
            this.entity.getNavigator().setSpeed(this.nearSpeed);
        }
        else
        {
            this.entity.getNavigator().setSpeed(this.farSpeed);
        }
    }
}
