package com.charles445.aireducer.config;

import net.minecraftforge.common.config.Config;

public class IceAndFireConfig
{
	@Config.Comment("Should myrmex AI be tweaked")
	@Config.Name("Myrmex")
	public boolean myrmex = true;
	
	@Config.Comment("Delay for AI getting a new task - Vanilla is 3")
	@Config.Name("MyrmexAIDelay")
	@Config.RangeInt(min=3,max=20)
	public int myrmex_ai_delay = 3;
	
	@Config.Comment("Update chance for EscortEntity - Vanilla is 1.0")
	@Config.Name("MyrmexUpdateChance EscortEntity")
	@Config.RangeDouble(min=0.025d, max=1.0d)
	public double myrmexUpdateChanceEscortEntity = 0.1d;
	
	@Config.Comment("Update chance for FindHidingSpot - Vanilla is 1.0")
	@Config.Name("MyrmexUpdateChance FindHidingSpot")
	@Config.RangeDouble(min=0.025d, max=1.0d)
	public double myrmexUpdateChanceFindHidingSpot = 0.1d;
	
	@Config.Comment("Update chance for Forage - Vanilla is 1.0")
	@Config.Name("MyrmexUpdateChance Forage")
	@Config.RangeDouble(min=0.025d, max=1.0d)
	public double myrmexUpdateChanceForage = 0.1d;
	
	@Config.Comment("Update chance for ReEnterHive - Vanilla is 1.0")
	@Config.Name("MyrmexUpdateChance ReEnterHive")
	@Config.RangeDouble(min=0.025d, max=1.0d)
	public double myrmexUpdateChanceReEnterHive = 0.1d;
	
	@Config.Comment("Replaces Ice and Fire dragon avoiding AI to use reduced AI settings instead")
	@Config.Name("ReplaceVillageAIFearUntamed")
	public boolean replaceVillageAIFearUntamed = true;
	
}
