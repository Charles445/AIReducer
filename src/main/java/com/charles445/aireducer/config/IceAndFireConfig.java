package com.charles445.aireducer.config;

import net.minecraftforge.common.config.Config;

public class IceAndFireConfig
{
	@Config.Comment("Master switch for this handler")
	@Config.Name("ENABLED")
	public boolean ENABLED = true;
	
	@Config.Comment("Should death worm AI be tweaked")
	@Config.Name("Death Worm")
	public boolean deathworm = true;
	
	/*
	@Config.Comment("Delay for AI getting a new task - Vanilla is 3")
	@Config.Name("DeathWormAIDelay")
	@Config.RangeInt(min=3, max=40)
	public int deathworm_ai_delay = 10;
	*/
	
	@Config.Comment("Should myrmex AI be tweaked")
	@Config.Name("Myrmex")
	public boolean myrmex = true;
	
	@Config.Comment("Range with which Myrmex can see and pathfind - Vanilla is 64")
	@Config.Name("MyrmexFollowRange")
	@Config.RangeDouble(min=16.0d, max=128.0d)
	public double myrmexFollowRange = 64.0d;
	
	/*
	@Config.Comment("Delay for AI getting a new task - Vanilla is 3")
	@Config.Name("MyrmexAIDelay")
	@Config.RangeInt(min=3,max=40)
	public int myrmex_ai_delay = 10;
	*/
	
	@Config.Comment("Modify EscortEntity")
	@Config.Name("MyrmexModify EscortEntity")
	public boolean myrmexModifyEscortEntity = true;
	
	@Config.Comment("Delay in ticks between new EscortEntity tasks - Vanilla is 0")
	@Config.Name("MyrmexRunDelay EscortEntity")
	@Config.RangeInt(min=0)
	public int myrmexRunDelayEscortEntity = 20;
	
	@Config.Comment("Update chance for EscortEntity task updates - Vanilla is 1")
	@Config.Name("myrmexUpdateChance EscortEntity")
	@Config.RangeDouble(min=0.01d, max=1.0d)
	public double myrmexUpdateChanceEscortEntity = 1.0d;
	
	@Config.Comment("Modify FindHidingSpot")
	@Config.Name("MyrmexModify FindHidingSpot")
	public boolean myrmexModifyFindHidingSpot = true;
	
	@Config.Comment("Delay in ticks between new FindHidingSpot tasks - Vanilla is 0")
	@Config.Name("MyrmexRunDelay FindHidingSpot")
	@Config.RangeInt(min=0)
	public int myrmexRunDelayFindHidingSpot = 60;
	
	@Config.Comment("Update chance for FindHidingSpot task updates - Vanilla is 1")
	@Config.Name("myrmexUpdateChance FindHidingSpot")
	@Config.RangeDouble(min=0.01d, max=1.0d)
	public double myrmexUpdateChanceFindHidingSpot = 0.1d;
	
	@Config.Comment("Modify Forage")
	@Config.Name("MyrmexModify Forage")
	public boolean myrmexModifyForage = true;
	
	@Config.Comment("Delay in ticks between new Forage tasks - Vanilla is 0")
	@Config.Name("MyrmexRunDelay Forage")
	@Config.RangeInt(min=0)
	public int myrmexRunDelayForage = 60;
	
	@Config.Comment("Update chance for Forage task updates - Vanilla is 1")
	@Config.Name("myrmexUpdateChance Forage")
	@Config.RangeDouble(min=0.01d, max=1.0d)
	public double myrmexUpdateChanceForage = 0.5d;
	
	@Config.Comment("Modify LeaveHive")
	@Config.Name("MyrmexModify LeaveHive")
	public boolean myrmexModifyLeaveHive = true;
	
	@Config.Comment("Delay in ticks between new LeaveHive tasks - Vanilla is 0")
	@Config.Name("MyrmexRunDelay LeaveHive")
	@Config.RangeInt(min=0)
	public int myrmexRunDelayLeaveHive = 60;
	
	@Config.Comment("Update chance for LeaveHive task updates - Vanilla is 1")
	@Config.Name("myrmexUpdateChance LeaveHive")
	@Config.RangeDouble(min=0.01d, max=1.0d)
	public double myrmexUpdateChanceLeaveHive = 0.1d;
	
	@Config.Comment("Modify MoveThroughHive")
	@Config.Name("MyrmexModify MoveThroughHive")
	public boolean myrmexModifyMoveThroughHive = true;
	
	@Config.Comment("Delay in ticks between new MoveThroughHive tasks - Vanilla is 0")
	@Config.Name("MyrmexRunDelay MoveThroughHive")
	@Config.RangeInt(min=0)
	public int myrmexRunDelayWanderHiveCenter = 60;
	
	@Config.Comment("Update chance for MoveThroughHive task updates - Vanilla is 1")
	@Config.Name("myrmexUpdateChance MoveThroughHive")
	@Config.RangeDouble(min=0.01d, max=1.0d)
	public double myrmexUpdateChanceMoveThroughHive = 0.5d;
	
	@Config.Comment("Modify WanderHiveCenter")
	@Config.Name("MyrmexModify WanderHiveCenter")
	public boolean myrmexModifyWanderHiveCenter = true;
	
	@Config.Comment("Delay in ticks between new WanderHiveCenter tasks - Vanilla is 0")
	@Config.Name("MyrmexRunDelay WanderHiveCenter")
	@Config.RangeInt(min=0)
	public int myrmexRunDelayMoveThroughHive = 60;
	
	@Config.Comment("Update chance for WanderHiveCenter task updates - Vanilla is 1")
	@Config.Name("myrmexUpdateChance WanderHiveCenter")
	@Config.RangeDouble(min=0.01d, max=1.0d)
	public double myrmexUpdateChanceWanderHiveCenter = 0.7d;

	@Config.Comment("How close in blocks a player has to be for myrmex to have AI, helps fix chunk loading issues")
	@Config.Name("MyrmexProximityRequirement")
	@Config.RangeDouble(min=10d)
	public double myrmexProximityRequirement = 1000d;
	
	@Config.Comment("Whether to enable Myrmex Proximity Requirements")
	@Config.Name("MyrmexProximityRequired")
	public boolean myrmexProximityRequired = true;
}
