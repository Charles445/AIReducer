package com.charles445.aireducer.reflect;

import javax.annotation.Nullable;

public class ReflectorIAF
{
	@Nullable
	public static ReflectorIAF reflector;

	public final Class c_iceandfire_MyrmexAIEscortEntity;
	public final Class c_iceandfire_MyrmexAIFindHidingSpot;
	public final Class c_iceandfire_MyrmexAIForage;
	public final Class c_iceandfire_MyrmexAILeaveHive;
	public final Class c_iceandfire_MyrmexAIMoveThroughHive;
	public final Class c_iceandfire_MyrmexAIWanderHiveCenter;
	
	public ReflectorIAF() throws Exception
	{
		c_iceandfire_MyrmexAIEscortEntity = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIEscortEntity");
		c_iceandfire_MyrmexAIFindHidingSpot = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIFindHidingSpot");
		c_iceandfire_MyrmexAIForage = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIForage");
		c_iceandfire_MyrmexAILeaveHive = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAILeaveHive");
		c_iceandfire_MyrmexAIMoveThroughHive = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIMoveThroughHive");
		c_iceandfire_MyrmexAIWanderHiveCenter = Class.forName("com.github.alexthe666.iceandfire.entity.ai.MyrmexAIWanderHiveCenter");
		
		reflector = this;
	}
}
