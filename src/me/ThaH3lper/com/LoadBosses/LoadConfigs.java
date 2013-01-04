package me.ThaH3lper.com.LoadBosses;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.EpicBoss;

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
				String Name, Type;
				int Health, Damage;
				List<String> Items = new ArrayList<String>(), Skills = new ArrayList<String>();
				boolean showhp;
				
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
				showhp = eb.Bosses.getCustomConfig().getBoolean("Bosses." + name + ".Showhp");
				
				eb.BossLoadList.add(new LoadBoss(Name, Type, Health, Damage, Items, showhp, Skills));
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
}
