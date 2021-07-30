package com.charles445.aireducer.config.init;

import java.util.HashMap;
import java.util.Map;

public class JsonConfigLessCollisions
{
	public static Map<String, Double> getDefaults()
	{
		//No combat allies or offensive tools
		//Exercise caution with mountables
		
		Map<String,Double> map = new HashMap<String,Double>();
		
		//Default value is 2.0d
		double dfv = 2.0d;
		
		//IceAndFire
		//TODO 1.16.5 exclusives
		map.put("com.github.alexthe666.iceandfire.entity.EntityCyclops", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityCyclopsEye", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityDeathWorm", dfv); //Mountable?
		map.put("com.github.alexthe666.iceandfire.entity.EntityDeathWormEgg", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityDragonEgg", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityDragonSkull", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityGorgon", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityHippocampus", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityHippogryphEgg", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityMultipartPart", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityMyrmexEgg", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityMyrmexQueen", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityMyrmexRoyal", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityMyrmexSentinel", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityMyrmexSoldier", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityMyrmexSwarmer", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityMyrmexWorker", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityPixie", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntitySeaSerpent", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntitySeaSerpentBubbles", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntitySiren", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntitySnowVillager", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityStymphalianBird", dfv);
		map.put("com.github.alexthe666.iceandfire.entity.EntityTroll", dfv);
		
		//LycanitesMobs
		map.put("com.lycanitesmobs.core.entity.CustomItemEntity", dfv);
		
		//Minecraft
		//TODO 1.16.5 exclusives
		map.put("net.minecraft.entity.item.ArmorStandEntity", dfv);
		map.put("net.minecraft.entity.item.ExperienceOrbEntity", dfv);
		map.put("net.minecraft.entity.item.ItemEntity", dfv);
		map.put("net.minecraft.entity.item.ItemFrameEntity", dfv);
		map.put("net.minecraft.entity.item.PaintingEntity", dfv);
		map.put("net.minecraft.entity.item.minecart.ChestMinecartEntity", dfv);
		map.put("net.minecraft.entity.item.minecart.FurnaceMinecartEntity", dfv);
		map.put("net.minecraft.entity.item.minecart.HopperMinecartEntity", dfv);
		map.put("net.minecraft.entity.item.minecart.SpawnerMinecartEntity", dfv);
		
		map.put("net.minecraft.entity.merchant.villager.VillagerEntity", dfv);
		map.put("net.minecraft.entity.merchant.villager.WanderingTraderEntity", dfv);
		
		map.put("net.minecraft.entity.monster.BlazeEntity", dfv);
		map.put("net.minecraft.entity.monster.CaveSpiderEntity", dfv);
		map.put("net.minecraft.entity.monster.CreeperEntity", dfv);
		map.put("net.minecraft.entity.monster.DrownedEntity", dfv);
		map.put("net.minecraft.entity.monster.ElderGuardianEntity", dfv);
		map.put("net.minecraft.entity.monster.EndermanEntity", dfv);
		map.put("net.minecraft.entity.monster.EndermiteEntity", dfv);
		map.put("net.minecraft.entity.monster.EvokerEntity", dfv);
		map.put("net.minecraft.entity.monster.GhastEntity", dfv);
		map.put("net.minecraft.entity.monster.GuardianEntity", dfv);
		map.put("net.minecraft.entity.monster.HoglinEntity", dfv); //Mountable (is this an issue?)
		map.put("net.minecraft.entity.monster.HuskEntity", dfv);
		map.put("net.minecraft.entity.monster.IllusionerEntity", dfv);
		map.put("net.minecraft.entity.monster.MagmaCubeEntity", dfv);
		map.put("net.minecraft.entity.monster.PhantomEntity", dfv);
		map.put("net.minecraft.entity.monster.PillagerEntity", dfv);
		map.put("net.minecraft.entity.monster.RavagerEntity", dfv);
		map.put("net.minecraft.entity.monster.ShulkerEntity", dfv);
		map.put("net.minecraft.entity.monster.SilverfishEntity", dfv);
		map.put("net.minecraft.entity.monster.SkeletonEntity", dfv);
		map.put("net.minecraft.entity.monster.SlimeEntity", dfv);
		map.put("net.minecraft.entity.monster.SpellcastingIllagerEntity", dfv);
		map.put("net.minecraft.entity.monster.SpiderEntity", dfv);
		map.put("net.minecraft.entity.monster.StrayEntity", dfv);
		map.put("net.minecraft.entity.monster.VexEntity", dfv);
		map.put("net.minecraft.entity.monster.VindicatorEntity", dfv);
		map.put("net.minecraft.entity.monster.WitchEntity", dfv);
		map.put("net.minecraft.entity.monster.WitherSkeletonEntity", dfv);
		map.put("net.minecraft.entity.monster.ZoglinEntity", dfv);
		map.put("net.minecraft.entity.monster.ZombieEntity", dfv);
		map.put("net.minecraft.entity.monster.ZombieVillagerEntity", dfv);
		map.put("net.minecraft.entity.monster.ZombifiedPiglinEntity", dfv);

		map.put("net.minecraft.entity.passive.BatEntity", dfv);
		map.put("net.minecraft.entity.passive.BeeEntity", dfv);
		map.put("net.minecraft.entity.passive.CatEntity", dfv);
		map.put("net.minecraft.entity.passive.ChickenEntity", dfv);
		map.put("net.minecraft.entity.passive.CowEntity", dfv);
		map.put("net.minecraft.entity.passive.DolphinEntity", dfv); //Mountable (is this an issue?)
		map.put("net.minecraft.entity.passive.FoxEntity", dfv);
		map.put("net.minecraft.entity.passive.MooshroomEntity", dfv);
		map.put("net.minecraft.entity.passive.OcelotEntity", dfv);
		map.put("net.minecraft.entity.passive.PandaEntity", dfv);
		map.put("net.minecraft.entity.passive.ParrotEntity", dfv);
		map.put("net.minecraft.entity.passive.PigEntity", dfv); //Take a pig ride through asmodeus
		map.put("net.minecraft.entity.passive.PolarBearEntity", dfv);
		map.put("net.minecraft.entity.passive.RabbitEntity", dfv);
		map.put("net.minecraft.entity.passive.SheepEntity", dfv);
		map.put("net.minecraft.entity.passive.SquidEntity", dfv);
		map.put("net.minecraft.entity.passive.StriderEntity", dfv); //Mountable (is this an issue?)
		map.put("net.minecraft.entity.passive.TurtleEntity", dfv);
		map.put("net.minecraft.entity.passive.WolfEntity", dfv); //Ally, but people hoard these
		map.put("net.minecraft.entity.passive.fish.CodEntity", dfv);
		map.put("net.minecraft.entity.passive.fish.PufferfishEntity", dfv);
		map.put("net.minecraft.entity.passive.fish.SalmonEntity", dfv);
		map.put("net.minecraft.entity.passive.fish.TropicalFishEntity", dfv);
		
		return map;
	}
}
