package me.ThaH3lper.com;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Skeleton.SkeletonType;

public class Mobs {
	public Entity SpawnMob(String s, Location l)
	{
		String[] part = s.split(":");
		String mobname = part[0];
		
		if(part.length == 2)
		{
			if(mobname.equals("sheep"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.SHEEP);
				Sheep sheep = (Sheep) liver;
				sheep.setColor(DyeColor.getByData(Byte.parseByte(part[1])));
				return liver;
			}
		}
		else
		{
			if(mobname.equals("creeper"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.CREEPER);
				return liver;
			}
			if(mobname.equals("wither"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.WITHER);
				return liver;
			}
			if(mobname.equals("enderdragon"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.ENDER_DRAGON);
				return liver;
			}
			if(mobname.equals("witherskeleton"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.SKELETON);
				Skeleton ske = (Skeleton) liver;
				ske.setSkeletonType(SkeletonType.WITHER);
				return liver;
			}
			if(mobname.equals("zombiebaby"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
				Zombie zm = (Zombie) liver;
				zm.setBaby(true);
				return liver;
			}
			if(mobname.equals("zombievillager"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
				Zombie zm = (Zombie) liver;
				zm.setVillager(true);
				return liver;
			}
			
			if(mobname.equals("bat"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.BAT);
				return liver;
			}
			if(mobname.equals("witch"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.WITCH);
				return liver;
			}
			if(mobname.equals("mushroomcow"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.MUSHROOM_COW);
				return liver;
			}
			if(mobname.equals("mushroomcowbaby"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.MUSHROOM_COW);
				MushroomCow mu = (MushroomCow) liver;
				mu.setBaby();
				return liver;
			}
			
			if(mobname.equals("squid"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.SQUID);
				return liver;
			}
			if(mobname.equals("skeleton"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.SKELETON);
				return liver;
			}
			if(mobname.equals("ghast"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.GHAST);
				return liver;
			}
			if(mobname.equals("blaze"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.BLAZE);
				return liver;
			}
			if(mobname.equals("zombie"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
				return liver;
			}
			if(mobname.equals("slime"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.SLIME);
				Slime slime = (Slime) liver;
				slime.setSize(5);
				return liver;
			}
			if(mobname.equals("wolf"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.WOLF);
				return liver;
			}
			if(mobname.equals("irongolem"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.IRON_GOLEM);
				return liver;
			}
			if(mobname.equals("pig"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.PIG);
				return liver;
			}
			if(mobname.equals("villager"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.VILLAGER);
				return liver;
			}
			if(mobname.equals("ocelot"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.OCELOT);
				return liver;
			}
			if(mobname.equals("chicken"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.CHICKEN);
				return liver;
			}
			if(mobname.equals("chickenbaby"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.CHICKEN);
				Chicken ch = (Chicken) liver;
				ch.setBaby();	
				return liver;
			}
			if(mobname.equals("cow"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.COW);
				return liver;
			}
			if(mobname.equals("cowbaby"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.COW);
				
				Cow ch = (Cow) liver;
				ch.setBaby();
				return liver;
			}
			if(mobname.equals("spider"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.SPIDER);
				return liver;
			}
			if(mobname.equals("enderman"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.ENDERMAN);
				return liver;
			}
			if(mobname.equals("cavespider"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.CAVE_SPIDER);
				return liver;
			}
			if(mobname.equals("giant"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.GIANT);
				return liver;
			}
			if(mobname.equals("silverfish"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.SILVERFISH);
				return liver;
			}
			if(mobname.equals("magmacube"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.MAGMA_CUBE);
				return liver;
			}
			if(mobname.equals("pigzombie"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.PIG_ZOMBIE);
				return liver;
			}
			if(mobname.equals("sheep"))
			{
				Entity liver = l.getWorld().spawnEntity(l, EntityType.SHEEP);
				return liver;
			}
		}
		return null;
	}

}
