package me.ThaH3lper.com.LoadBosses;

import me.ThaH3lper.com.EpicBoss;

public class LoadConfigs {
	public EpicBoss eb;
	
	private String Name, Type;
	private int Health, Damage;
	public LoadConfigs(EpicBoss boss)
	{
		eb = boss;
		if(eb.Bosses.getCustomConfig().contains("Bosses") == true)
		{
			for(String name : eb.Bosses.getCustomConfig().getConfigurationSection("Bosses").getKeys(false))
			{
				Name = name;
				Type = eb.Bosses.getCustomConfig().getString("Bosses." + name + ".Type");
				Health = eb.Bosses.getCustomConfig().getInt("Bosses." + name + ".Health");
				Damage = eb.Bosses.getCustomConfig().getInt("Bosses." + name + ".Damage");
				eb.BossLoadList.add(new LoadBoss(Name, Type, Health, Damage));
			}
		}
	}
	public LoadBoss getLoadBoss(String s)
	{
		if(eb.BossLoadList != null)
		{
			for(LoadBoss lb : eb.BossLoadList)
			{
				if(lb.getName() == s)
				{
					return lb;
				}
			}
		}
		return null;
	}
}
