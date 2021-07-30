package com.charles445.aireducer.config.json;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

public class JsonTypeToken
{
	public static Type get(JsonFileName jcfn)
	{
		switch(jcfn)
		{
			case lessCollisions: return new TypeToken<Map<String, Double>>(){}.getType();
			
			default:
				return null;
		}
	}
}
