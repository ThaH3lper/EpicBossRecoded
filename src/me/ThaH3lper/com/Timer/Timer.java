package me.ThaH3lper.com.Timer;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.LoadBosses.LoadBoss;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Timer {
	private int time;
	private int maxtime;
	private String name, location, boss;
private EpicBoss eb;
	public Timer(String newname, String newboss, String newlocation, int newtime, EpicBoss neweb)
	{
		maxtime = newtime;
		boss = newboss;
		time = 0;
		name = newname;
		location = newlocation;
		eb = neweb;
	}
	public String getName()
	{
		return name;
	}
	public int getMaxTime()
	{
		return maxtime;
	}
	public int getTime()
	{
		return time;
	}
	public void setTime(int ntime)
	{
		time = ntime;
	}
	public String getBossName()
	{
		return boss;
	}
	public String getLocationStr()
	{
		return location;
	}
	public void lower()
	{
		if(time == 0)
		{
			eb.timerstuff.spawndeath(this);
			time = -1;
		}
		else if(time != -1)
		{
			time--;
		}
	}
}
