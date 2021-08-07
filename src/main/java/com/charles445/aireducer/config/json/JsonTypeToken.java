package com.charles445.aireducer.config.json;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

public class JsonTypeToken
{
	public static Type get(JsonFileName jcfn)
	{
		switch(jcfn)
		{
			case taskDelay: return new TypeToken<Map<String, Integer>>(){}.getType();
		
			default:
				return null;
		}
	}
}
