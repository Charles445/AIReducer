package com.charles445.aireducer.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Nullable;

import com.charles445.aireducer.util.ErrorUtil;
import com.charles445.aireducer.util.ReflectUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;

public class ReflectorIAF
{
	@Nullable
	public static ReflectorIAF reflector;
	
	public final Class c_EntityMyrmexBase;
	public final Method m_EntityMyrmexBase_canMove;
	public final Method m_EntityMyrmexBase_shouldLeaveHive;
	public final Method m_EntityMyrmexBase_shouldEnterHive;
	
	public final Class c_EntityMyrmexQueen;
	public final Class c_EntityMyrmexSentinel;
	
	public final Class c_MyrmexAILeaveHive;
	public final Field f_MyrmexAILeaveHive_nextEntrance;
	public final Field f_MyrmexAILeaveHive_path;
	
	public final Class c_iceandfire_VillagerAIFearUntamed;
	public final Class c_iceandfire_IAnimalFear;
	public final Method m_iceandfire_shouldAnimalsFear;
	public final Class c_iceandfire_IVillagerFear;
	public final Class c_iceandfire_DragonUtils;
	public final Method m_iceandfire_isLivestock;
	public final Method m_iceandfire_isVillager;
	
	public final Class c_iceandfire_MyrmexAIForage;
	public final Class c_iceandfire_MyrmexAIFindHidingSpot;
	public final Class c_iceandfire_MyrmexAILeaveHive;
	public final Class c_iceandfire_MyrmexAIMoveThroughHive;
	public final Class c_iceandfire_MyrmexAIReEnterHive;
	public final Class c_iceandfire_MyrmexAIEscortEntity;
	
	public ReflectorIAF() throws Exception
	{
		c_EntityMyrmexBase = Class.forName("com.github.alexthe666.iceandfire.entity.EntityMyrmexBase");
		m_EntityMyrmexBase_canMove = ReflectUtil.findMethod(c_EntityMyrmexBase, "canMove");
		m_EntityMyrmexBase_shouldLeaveHive = ReflectUtil.findMethod(c_EntityMyrmexBase, "shouldLeaveHive");
		m_EntityMyrmexBase_shouldEnterHive = ReflectUtil.findMethod(c_EntityMyrmexBase, "shouldEnterHive");

		c_EntityMyrmexQueen = Class.forName("com.github.alexthe666.iceandfire.entity.EntityMyrmexQueen");
		c_EntityMyrmexSentinel = Class.forName("com.github.alexthe666.iceandfire.entity.EntityMyrmexSentinel");
		
		c_MyrmexAILeaveHive = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAILeaveHive");
		f_MyrmexAILeaveHive_nextEntrance = ReflectUtil.findField(c_MyrmexAILeaveHive, "nextEntrance");
		f_MyrmexAILeaveHive_path = ReflectUtil.findField(c_MyrmexAILeaveHive, "path");
		
		c_iceandfire_VillagerAIFearUntamed = Class.forName("com.github.alexthe666.iceandfire.entity.ai.VillagerAIFearUntamed");
		c_iceandfire_IAnimalFear = Class.forName("com.github.alexthe666.iceandfire.entity.IAnimalFear");
		m_iceandfire_shouldAnimalsFear = c_iceandfire_IAnimalFear.getDeclaredMethod("shouldAnimalsFear",Entity.class);
		c_iceandfire_IVillagerFear = Class.forName("com.github.alexthe666.iceandfire.entity.IVillagerFear");
		c_iceandfire_DragonUtils = Class.forName("com.github.alexthe666.iceandfire.entity.DragonUtils");
		m_iceandfire_isLivestock = c_iceandfire_DragonUtils.getDeclaredMethod("isLivestock",Entity.class);
		m_iceandfire_isVillager = c_iceandfire_DragonUtils.getDeclaredMethod("isVillager", Entity.class);
		
		c_iceandfire_MyrmexAIForage = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIForage");
		c_iceandfire_MyrmexAIFindHidingSpot = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIFindHidingSpot");
		c_iceandfire_MyrmexAILeaveHive = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAILeaveHive");
		c_iceandfire_MyrmexAIMoveThroughHive = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIMoveThroughHive");
		c_iceandfire_MyrmexAIReEnterHive = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIReEnterHive");
		c_iceandfire_MyrmexAIEscortEntity = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIEscortEntity");
		
		reflector = this;
	}
	
	public Path getMyrmexAILeaveHive_path(Object taskMyrmexAILeaveHive)
	{
		try
		{
			return (Path) f_MyrmexAILeaveHive_path.get(taskMyrmexAILeaveHive);
		}
		catch (IllegalArgumentException | IllegalAccessException e)
		{
			ErrorUtil.debugError("Invocation error in getMyrmexAILeaveHive_path",e);
			return null;
		}
	}
	
	public BlockPos getMyrmexAILeaveHive_nextEntrance(Object taskMyrmexAILeaveHive)
	{
		try
		{
			return (BlockPos) f_MyrmexAILeaveHive_nextEntrance.get(taskMyrmexAILeaveHive);
		}
		catch (IllegalArgumentException | IllegalAccessException e)
		{
			ErrorUtil.debugError("Invocation error in getMyrmexAILeaveHive_nextEntrance");
			return BlockPos.ORIGIN;
		}
	}
	
	public boolean canMove(EntityLivingBase entityMyrmexBase)
	{
		try
		{
			return (boolean) m_EntityMyrmexBase_canMove.invoke(entityMyrmexBase);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			ErrorUtil.debugError("Invocation error in canMove");
			return false;
		}
	}
	
	public boolean shouldLeaveHive(EntityLivingBase entityMyrmexBase)
	{
		try
		{
			return (boolean) m_EntityMyrmexBase_shouldLeaveHive.invoke(entityMyrmexBase);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			ErrorUtil.debugError("Invocation error in shouldLeaveHive");
			return false;
		}
	}
	
	public boolean shouldEnterHive(EntityLivingBase entityMyrmexBase)
	{
		try
		{
			return (boolean) m_EntityMyrmexBase_shouldEnterHive.invoke(entityMyrmexBase);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			ErrorUtil.debugError("Invocation error in shouldEnterHive");
			return false;
		}
	}
}
