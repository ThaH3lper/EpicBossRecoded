package me.ThaH3lper.com.LoadBosses;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;

public class LoadConfigs {
	public EpicBoss eb;
	
	private String Name, Type;
	private int Health, Damage;
	private List<String> Items;
	private boolean showhp;
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
				Name = name;
				Type = eb.Bosses.getCustomConfig().getString("Bosses." + name + ".Type");
				Health = eb.Bosses.getCustomConfig().getInt("Bosses." + name + ".Health");
				Damage = eb.Bosses.getCustomConfig().getInt("Bosses." + name + ".Damage");
				if(eb.Bosses.getCustomConfig().contains("Bosses." + name + ".Drops") == true)
				{
					Items = eb.Bosses.getCustomConfig().getStringList("Bosses." + name + ".Drops");
				}
				showhp = eb.Bosses.getCustomConfig().getBoolean("Bosses." + name + ".Showhp");
				
				eb.BossLoadList.add(new LoadBoss(Name, Type, Health, Damage, Items, showhp));
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
