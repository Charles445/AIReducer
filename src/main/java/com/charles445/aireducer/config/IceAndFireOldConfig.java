package com.charles445.aireducer.config;

import net.minecraftforge.common.config.Config;

public class IceAndFireOldConfig
{
	@Config.Comment("Master switch for this handler")
	@Config.Name("ENABLED")
	public boolean ENABLED = true;
	
	@Config.Comment("Should death worm AI be tweaked")
	@Config.Name("Death Worm")
	public boolean deathworm = true;
	
	@Config.Comment("How close in blocks a player has to be for death worms to have AI, helps fix chunk loading issues")
	@Config.Name("DeathWormProximityRequirement")
	@Config.RangeDouble(min=10d)
	public double deathwormProximityRequirement = 1000d;
	
	@Config.Comment("Whether to enable Death Worm Proximity Requirements")
	@Config.Name("DeathWormProximityRequired")
	public boolean deathwormProximityRequired = true;
	
	/*
	@Config.Comment("Delay for AI getting a new task - Vanilla is 3")
	@Config.Name("DeathWormAIDelay")
	@Config.RangeInt(min=3, max=40)
	public int deathworm_ai_delay = 10;
	*/
	
	@Config.Comment("Should myrmex AI be tweaked")
	@Config.Name("Myrmex")
	public boolean myrmex = true;
	
	/*
	@Config.Comment("Delay for AI getting a new task - Vanilla is 3")
	@Config.Name("MyrmexAIDelay")
	@Config.RangeInt(min=3,max=40)
	public int myrmex_ai_delay = 10;
	*/
	
	@Config.Comment("Range with which Myrmex can see and pathfind - Vanilla 1.7.1 is 128, 1.8.4 is 64")
	@Config.Name("MyrmexFollowRange")
	@Config.RangeDouble(min=16.0d, max=128.0d)
	public double myrmexFollowRange = 50.0d;
	
	@Config.Comment("Update chance for EscortEntity - Vanilla is 1.0")
	@Config.Name("MyrmexUpdateChance EscortEntity")
	@Config.RangeDouble(min=0.01d, max=1.0d)
	public double myrmexUpdateChanceEscortEntity = 0.1d;
	
	@Config.Comment("Update chance for FindHidingSpot - Vanilla is 1.0")
	@Config.Name("MyrmexUpdateChance FindHidingSpot")
	@Config.RangeDouble(min=0.01d, max=1.0d)
	public double myrmexUpdateChanceFindHidingSpot = 0.1d;
	
	@Config.Comment("Update chance for Forage - Vanilla is 1.0")
	@Config.Name("MyrmexUpdateChance Forage")
	@Config.RangeDouble(min=0.01d, max=1.0d)
	public double myrmexUpdateChanceForage = 0.1d;
	
	@Config.Comment("Use alternate path navigation - for 1.7.1")
	@Config.Name("MyrmexAlternateNavigation")
	public boolean myrmexAlternateNavigation = true;
	
	@Config.Comment("Modify LeaveHive - for 1.7.1")
	@Config.Name("MyrmexModify LeaveHive")
	public boolean myrmexModifyLeaveHive = true;
	
	@Config.Comment("Modify MoveThroughHive - for 1.7.1")
	@Config.Name("MyrmexModify MoveThroughHive")
	public boolean myrmexMoveThroughHive = true;
	
	@Config.Comment("Update chance for ReEnterHive - Vanilla is 1.0")
	@Config.Name("MyrmexUpdateChance ReEnterHive")
	@Config.RangeDouble(min=0.01d, max=1.0d)
	public double myrmexUpdateChanceReEnterHive = 0.1d;
	
	@Config.Comment("Replaces Ice and Fire dragon avoiding AI to use reduced AI settings instead")
	@Config.Name("ReplaceVillageAIFearUntamed")
	public boolean replaceVillageAIFearUntamed = true;
	
	@Config.Comment("How close in blocks a player has to be for myrmex to have AI, helps fix chunk loading issues")
	@Config.Name("MyrmexProximityRequirement")
	@Config.RangeDouble(min=10d)
	public double myrmexProximityRequirement = 1000d;
	
	@Config.Comment("Whether to enable Myrmex Proximity Requirements")
	@Config.Name("MyrmexProximityRequired")
	public boolean myrmexProximityRequired = true;
	
}
