package com.charles445.aireducer.util;

import com.charles445.aireducer.AIReducer;
import com.charles445.aireducer.config.ModConfig;

public class ErrorUtil
{
	public static void debugError(String s, Exception e)
	{
		if(ModConfig.debug)
			AIReducer.logger.error(s,e);
	}
	
	public static void debugError(String s)
	{
		if(ModConfig.debug)
			AIReducer.logger.error(s);
	}
	
	public static void debugDebug(String s)
	{
		if(ModConfig.debug)
			AIReducer.logger.debug(s);
	}
}
