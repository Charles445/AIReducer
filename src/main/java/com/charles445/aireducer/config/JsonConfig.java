package com.charles445.aireducer.config;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.apache.commons.io.FileUtils;

import com.charles445.aireducer.AIReducer;
import com.charles445.aireducer.config.init.JsonConfigTaskDelay;
import com.charles445.aireducer.config.json.JsonFileName;
import com.charles445.aireducer.config.json.JsonTypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConfig
{
	public static List<String> jsonErrors = new ArrayList<String>();
	
	public static Map<String, Integer> taskDelay = new HashMap<>();
	
	public static void init()
	{
		//Task Delay
		taskDelay.putAll(JsonConfigTaskDelay.getDefaults());
		
		Map<String, Integer> taskDelayJson = processJson(JsonFileName.taskDelay, taskDelay, true);
		if(taskDelayJson!=null)
		{
			try
			{
				taskDelay.putAll(taskDelayJson);
				manuallyWriteToJson(JsonFileName.taskDelay, taskDelay);
			}
			catch(Exception e)
			{
				AIReducer.logger.error("Failed to merge write taskDelay!");
			}
		}
		
		if(taskDelay == null)
			taskDelay = new HashMap<>();
		
		///////////////////
	}
	
	/** Nullable when forMerging is true */
	@Nullable
	public static <T> T processJson(JsonFileName jfn, final T container, boolean forMerging)
	{
		try
		{
			return processUncaughtJson(jfn, container, forMerging);
		}
		catch(Exception e)
		{
			AIReducer.logger.error("Error managing JSON File: "+jfn.get(), e);
			jsonErrors.add("config/aireducer/"+jfn.get()+" failed to load!");
			if(forMerging)
			{
				return null;
			}
			else
			{
				return container;
			}
		}
	}
	
	@Nullable
	public static <T> T processUncaughtJson(JsonFileName jfn, final T container, boolean forMerging) throws Exception
	{
		String jsonFileName = jfn.get();
		Type type = JsonTypeToken.get(jfn);
		
		File jsonFile = new File(AIReducer.jsonDirectory,jsonFileName);
		if(jsonFile.exists())
		{
			Gson gson = buildNewGson();
			//Read
			return (T) gson.fromJson(new FileReader(jsonFile), type);
		}
		else
		{
			Gson gson = buildNewGson();
			//Write
			
			FileUtils.write(jsonFile,gson.toJson(container, type),(String)null);
			if(forMerging)
			{
				return null;
			}
			else
			{
				return container;
			}
		}
	}
	
	private static <T> void manuallyWriteToJson(JsonFileName jfn, final T container) throws Exception
	{
		String jsonFileName = jfn.get();
		Type type = JsonTypeToken.get(jfn);
		
		Gson gson = buildNewGson();
		File jsonFile = new File(AIReducer.jsonDirectory,jsonFileName);
		FileUtils.write(jsonFile, gson.toJson(container, type),(String)null);
	}
	
	private static Gson buildNewGson()
	{
		//Pretty printing, and private modifiers are not serialized
		return new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.PRIVATE, Modifier.STATIC).create();
	}
}
