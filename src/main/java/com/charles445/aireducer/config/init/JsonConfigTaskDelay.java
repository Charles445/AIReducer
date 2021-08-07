package com.charles445.aireducer.config.init;

import java.util.HashMap;
import java.util.Map;

import com.charles445.aireducer.util.ModNames;

import net.minecraftforge.fml.common.Loader;

public class JsonConfigTaskDelay
{
	public static Map<String, Integer> getDefaults()
	{
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		//Default value is 3
		
		//Vanilla
		map.put("minecraft:rabbit", 6);
		
		if(Loader.isModLoaded(ModNames.ICEANDFIRE))
		{
			//Ice and Fire Default Value
			int iafv = 10;
			
			map.put("iceandfire:deathworm",iafv);
			
			map.put("iceandfire:myrmex_worker",iafv);
			map.put("iceandfire:myrmex_soldier",iafv);
			map.put("iceandfire:myrmex_sentinel",iafv);
			map.put("iceandfire:myrmex_royal",iafv);
			map.put("iceandfire:myrmex_queen",iafv);
		}
			
		return map;
	}
}
