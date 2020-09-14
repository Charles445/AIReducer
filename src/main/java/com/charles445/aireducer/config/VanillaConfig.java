package com.charles445.aireducer.config;

import net.minecraftforge.common.config.Config;

public class VanillaConfig
{
	@Config.Comment("Master switch for this handler")
	@Config.Name("ENABLED")
	public boolean ENABLED = true;
	
	@Config.Comment("Replace vanilla entity avoid tasks for better performance")
	@Config.Name("Avoid Task Replacement")
	public boolean avoidTaskReplacement = true;
	
	@Config.Comment("Should vanilla rabbit AI be tweaked")
	@Config.Name("Rabbit")
	public boolean rabbit = true;
	
	@Config.Comment("Delay for AI getting a new task - Vanilla is 3")
	@Config.Name("RabbitAIDelay")
	@Config.RangeInt(min=3,max=20)
	public int rabbit_ai_delay = 6;
	
	//TODO remove, rely on avoidTaskReplacement instead
	@Config.Comment("Delay for rabbits checking if they are scared - Vanilla is 1")
	@Config.Name("RabbitAvoidDelay")
	@Config.RangeInt(min=1,max=10)
	public int rabbit_should_avoid = 2;
}
