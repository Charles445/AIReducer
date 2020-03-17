package com.charles445.aireducer.debug;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class DebugUtil
{
	public static void messageAll(String s)
	{
		for(EntityPlayerMP player : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers())
		{
			player.sendMessage(new TextComponentString(s));
		}
	}
}
