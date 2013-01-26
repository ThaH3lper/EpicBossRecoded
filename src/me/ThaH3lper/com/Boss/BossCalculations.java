package me.ThaH3lper.com.Boss;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import me.ThaH3lper.com.EpicBoss;

public class BossCalculations {
	private EpicBoss eb;
	public BossCalculations(EpicBoss boss)
	{
		eb = boss;
	}
	public boolean isBoss(Entity e)
	{
		if(eb.BossList != null)
		{
			for(Boss boss : eb.BossList)
			{
				if(boss.getLivingEntity() != null)
				{
					if(e.getEntityId() == boss.getId())
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean isBossLiv(LivingEntity e)
	{
		if(eb.BossList != null)
		{
			for(Boss boss : eb.BossList)
			{
				if(boss.getLivingEntity() != null)
				{
					if(e.getEntityId() == boss.getId())
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	public Boss getBoss(Entity e)
	{
		int id = e.getEntityId();
		if(eb.BossList != null)
		{
			for(Boss boss : eb.BossList)
			{
				if(boss.getSaved() == false)
				{
					if(id == boss.getId())
					{
						return boss;
					}
				}
			}
		}
		return null;
	}
	public Boolean BossHited(LivingEntity e)
	{
		if(e.getHealth() == e.getMaxHealth())
		{
			return true;
		}
		return false;
	}
	public Boolean BossExist(Boss b)
	{
		if(b.getLivingEntity() != null)
		{
			for(World w : Bukkit.getWorlds())
			{
				try
				{
					for(Entity e : w.getEntities())
					{
						if(b.getId() == e.getEntityId())
						{
							return true;
						}
					}
				}
				catch (Exception e) {
					return null;
				}
			}
		}
		return false;
	}

}
