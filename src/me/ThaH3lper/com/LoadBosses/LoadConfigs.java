package me.ThaH3lper.com.LoadBosses;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

public class LoadConfigs {
	public EpicBoss eb;
	public LoadConfigs(EpicBoss boss)
	{
		eb = boss;
		LoadBosses();
	}
	public void LoadBosses()
	{
		eb.BossLoadList.clear();
		eb.Bosses.reloadCustomConfig();
		if(eb.Bosses.getCustomConfig().contains("Bosses") == true)
		{
			for(String name : eb.Bosses.getCustomConfig().getConfigurationSection("Bosses").getKeys(false))
			{
				String Name, Type, Skin = null;
				int Health, Damage;
				List<String> Items = new ArrayList<String>(), Skills = new ArrayList<String>();
				boolean showhp;
				boolean showtitle = false;
				
				Name = name;
				Type = eb.Bosses.getCustomConfig().getString("Bosses." + name + ".Type");
				Health = eb.Bosses.getCustomConfig().getInt("Bosses." + name + ".Health");
				Damage = eb.Bosses.getCustomConfig().getInt("Bosses." + name + ".Damage");
				if(eb.Bosses.getCustomConfig().contains("Bosses." + name + ".Drops") == true)
				{
					Items = eb.Bosses.getCustomConfig().getStringList("Bosses." + name + ".Drops");
				}
				if(eb.Bosses.getCustomConfig().contains("Bosses." + name + ".Skills") == true)
				{
					Skills = eb.Bosses.getCustomConfig().getStringList("Bosses." + name + ".Skills");
				}
				if(eb.Bosses.getCustomConfig().contains("Bosses." + name + ".Skin") == true)
				{
					Skin = eb.Bosses.getCustomConfig().getString("Bosses." + name + ".Skin");
				}
				if(eb.Bosses.getCustomConfig().contains("Bosses." + name + ".Showtitle") == true)
				{
					showtitle = eb.Bosses.getCustomConfig().getBoolean("Bosses." + name + ".Showtitle");
				}
				showhp = eb.Bosses.getCustomConfig().getBoolean("Bosses." + name + ".Showhp");
				
				eb.BossLoadList.add(new LoadBoss(Name, Type, Health, Damage, Items, showhp, Skills, Skin, showtitle));
			}
		}
	}
	public LoadBoss getLoadBoss(String s)
	{
		if(eb.BossLoadList != null)
		{
			for(LoadBoss lb : eb.BossLoadList)
			{
				if(lb.getName().equals(s))
				{
					return lb;
				}
			}
		}
		return null;
	}
	public void SaveAllBosses()
	{
		if(eb.BossList != null)
		{
			List<String> saved = new ArrayList<String>();
			for(Boss boss : eb.BossList)
			{
				if(boss.getTimer().equals("null"))
				{
					String save = boss.getName() + ":" + boss.getHealth() + ":" + boss.getWorkingLocation().getWorld().getName() + ":" + boss.getWorkingLocation().getBlockX() + ":" + boss.getWorkingLocation().getBlockY() + ":" + boss.getWorkingLocation().getBlockZ() + ":" + boss.getTimer();
					saved.add(save);
				}
				if(boss.getSaved() == false)
				{
					boss.getLivingEntity().remove();
				}
			}
			eb.SavedData.reloadCustomConfig();
			eb.SavedData.getCustomConfig().set("Bosses", saved);
			eb.SavedData.saveCustomConfig();
		}
		
	}
	public void LoadAllBosses()
	{
		if(eb.SavedData.getCustomConfig().contains("Bosses"))
		{
			if(eb.SavedData.getCustomConfig().getStringList("Bosses") != null)
			{
				for(String s : eb.SavedData.getCustomConfig().getStringList("Bosses"))
				{
					String[] Splits = s.split(":");
					if(getLoadBoss(Splits[0]) != null)
					{
						LoadBoss lb = getLoadBoss(Splits[0]);
						Location l = new Location(Bukkit.getWorld(Splits[2]), (double )Integer.parseInt(Splits[3]), (double )Integer.parseInt(Splits[4]), (double )Integer.parseInt(Splits[5]));
						
						Boss bs = new Boss(lb.getName(), lb.getHealth(), l, lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems(), lb.getSkills(), lb.getShowtitle(), lb.getSkin());
						bs.sethealth(Integer.parseInt(Splits[1]));
						if(!Splits[6].equals("null"));
						{
							bs.setTimer(Splits[6]);
						}
						eb.BossList.add(bs);
					}
				}
			}
		}
	}
}
