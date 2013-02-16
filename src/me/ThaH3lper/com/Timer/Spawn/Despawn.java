package me.ThaH3lper.com.Timer.Spawn;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Despawn {
	
	public void DeSpawnEvent(EpicBoss eb)
	{
		if(eb.BossList != null)
		{
			try
			{
			for(Boss boss : eb.BossList)
			{
				if(boss.getSaved() == false)
				{
					if(boss.getLocation() != null)
					{
						if(PlayersInside(boss.getLocation()) == false)
						{
							if(!boss.getEntitySpawnName().equals("enderdragon"))
							{
								boss.setSaved(true);
								boss.setSavedLocation(boss.getLocation());
								boss.getLivingEntity().remove();
							}
						}
					}
				}
				else if(boss.getSaved() == true)
				{
					if(PlayersInside(boss.getSavedLocation()) == true)
					{
						boss.setSaved(false);
						LivingEntity l = (LivingEntity) eb.mobs.SpawnMob(boss.getEntitySpawnName(), boss.getSavedLocation());
						l.setHealth(l.getMaxHealth() - 1);
						boss.setEntity(l);
						eb.loadbossequip.SetEqupiment(boss);
						eb.skillhandler.skills(boss, null);
						if(eb.SpoutEnabled && boss.getSkinUrl() != null)
						{
							Spout.getServer().setEntitySkin(l, boss.getSkinUrl(), EntitySkinType.DEFAULT);
						}
						if(eb.SpoutEnabled && boss.isTitleShowed())
						{
							Spout.getServer().setTitle(l, boss.getEntitySpawnName());
						}
					}
				}
				//This is used so if it gets removed it will be spawned again!
				if(boss.getSaved() == false)
				{
					if(PlayersInside(boss.getSavedLocation()) == true)
					{
						if(eb.bossCalculator.BossExist(boss) != null)
						{
							if(eb.bossCalculator.BossExist(boss) == false)
							{
								boss.setSaved(true);
								boss.setSavedLocation(boss.getLocation());
							}
						}
					}
				}
			}
			}
			catch (Exception e) {
			}
		}
	}
	//Player inside 40 blocks returns true
	public boolean PlayersInside(Location l)
	{
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			if(l.getWorld() == p.getWorld())
			{
				if(l.distance(p.getLocation()) < 40)
				{
					return true;
				}
			}
		}
		return false;
	}
}
