package me.ThaH3lper.com.Timer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.LoadBosses.LoadBoss;
import me.ThaH3lper.com.locations.Locations;

public class TimerStuff {
	EpicBoss eb;
	public TimerStuff(EpicBoss neweb)
	{
		eb = neweb;
		LoadAllTimers();
	}
	
	public void addTimer(String name, String bossname, String location, int time)
	{
		eb.TimersList.add(new Timer(name, bossname, location, time, eb));
		saveAllTimers();
	}
	
	public void removeTimer(String name)
	{
		if(eb.TimersList != null)
		{
			int i = 0;
			while(i < eb.TimersList.size())
			{
				if(eb.TimersList.get(i).getName().equals(name))
				{
					eb.TimersList.remove(i);
					saveAllTimers();
				}
				i++;
			}
		}
	}
	
	public void saveAllTimers()
	{
		if(eb.TimersList != null)
		{
			List<String> saved = new ArrayList<String>();
			for(Timer time: eb.TimersList)
			{
				String save = time.getName() + ":" + time.getBossName() + ":" + time.getLocationStr() + ":" + time.getMaxTime();
				saved.add(save);
			}
			eb.SavedData.reloadCustomConfig();
			eb.SavedData.getCustomConfig().set("Timers", saved);
			eb.SavedData.saveCustomConfig();
		}
	}
	public void LoadAllTimers()
	{
		if(eb.SavedData.getCustomConfig().contains("Timers"))
		{
			if(eb.SavedData.getCustomConfig().getStringList("Timers") != null)
			{
				for(String s : eb.SavedData.getCustomConfig().getStringList("Timers"))
				{
					String[] Splits = s.split(":");
					if(eb.loadconfig.getLoadBoss(Splits[1]) != null)
					{
						if(eb.locationstuff.locationExict(Splits[2]))
						{
							Timer time = new Timer(Splits[0], Splits[1], Splits[2], Integer.parseInt(Splits[3]), eb);
							eb.TimersList.add(time);
							if(Splits.length == 5)
							{
								String str = Splits[4].replace("_", " ");
								str = ChatColor.translateAlternateColorCodes('&', str);
								time.setText(str);
							}
							saveAllTimers();
						}
						else
						{
							eb.logger.warning("Timer: " + Splits[0] + " could not be loaded since location " + Splits[2] + " dose not exict!");
						}
					}
					else
					{
						eb.logger.warning("Timer: " + Splits[0] + " could not be loaded since boss " + Splits[1] + " dose not exict!");
					}
				}
			}
		}
	}
	public void lower()
	{
		if(eb.TimersList != null)
		{
			for(Timer time: eb.TimersList)
			{
				time.lower();
			}
		}
	}
	public void Death(Boss b)
	{
		if(getTimer(b.getTimer()) != null)
		{
			Timer time = getTimer(b.getTimer());
			time.setTime(time.getMaxTime());
		}
	}
	public void spawndeath(Timer time)
	{
		LoadBoss lb = eb.loadconfig.getLoadBoss(time.getBossName());
		Locations loc = eb.locationstuff.getLocations(time.getLocationStr());
		if(lb != null && loc != null)
		{
			Boss b = new Boss(lb.getName(), lb.getHealth(), loc.getLocation(), lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems(), lb.getSkills());
			b.setTimer(time.getName());
			eb.BossList.add(b);
			
			eb.timer.despawn.DeSpawnEvent(eb);
			if(!time.getText().equals(""))
			{
				Bukkit.broadcastMessage(time.getText());
			}
		}
	}
	public Timer getTimer(String name)
	{
		if(eb.TimersList != null)
		{
			for(Timer time : eb.TimersList)
			{
				if(time.getName().equals(name))
				{
					return time;
				}
			}
		}
		return null;
	}

}
