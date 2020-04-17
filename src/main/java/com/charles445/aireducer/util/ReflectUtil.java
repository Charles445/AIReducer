package com.charles445.aireducer.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtil
{
	public static Method findMethod(Class clazz, String name) throws Exception
	{
		//Desc is not specified
		for(Method m : clazz.getDeclaredMethods())
		{
			if(m.getName().equals(name))
			{
				m.setAccessible(true);
				return m;
			}
		}
		
		throw new NoSuchMethodException(name);
	}
	
	public static Method findMethod(Class clazz, String name, Class... params) throws Exception
	{
		Method m = clazz.getDeclaredMethod(name, params);
		m.setAccessible(true);
		return m;
	}
	
	public static Field findField(Class clazz, String name) throws Exception
	{
		Field f = clazz.getDeclaredField(name);
		f.setAccessible(true);
		return f;
	}
}
