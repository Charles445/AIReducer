package com.charles445.aireducer.ai.myrmex;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.charles445.aireducer.AIReducer;
import com.charles445.aireducer.util.ReflectUtil;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;

public class ReflectMyrmex
{
	public static ReflectMyrmex reflector;
	
	public final Class c_EntityMyrmexBase;
	public final Method m_EntityMyrmexBase_canMove;
	public final Method m_EntityMyrmexBase_shouldLeaveHive;
	public final Method m_EntityMyrmexBase_shouldEnterHive;
	
	public final Class c_EntityMyrmexQueen;
	public final Class c_EntityMyrmexSentinel;
	
	public final Class c_MyrmexAILeaveHive;
	public final Field f_MyrmexAILeaveHive_nextEntrance;
	public final Field f_MyrmexAILeaveHive_path;
	
	public ReflectMyrmex() throws Exception
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
			AIReducer.debugError("Invocation error in getMyrmexAILeaveHive_path",e);
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
			AIReducer.debugError("Invocation error in getMyrmexAILeaveHive_nextEntrance");
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
			AIReducer.debugError("Invocation error in canMove");
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
			AIReducer.debugError("Invocation error in shouldLeaveHive");
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
			AIReducer.debugError("Invocation error in shouldEnterHive");
			return false;
		}
	}
}
