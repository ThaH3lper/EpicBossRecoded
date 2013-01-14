package me.ThaH3lper.com.Timer;

import me.ThaH3lper.com.EpicBoss;

public class Timer {
	private int time;
	private int maxtime;
	private String name, location, boss, spawnmsg;
private EpicBoss eb;
	public Timer(String newname, String newboss, String newlocation, int newtime, EpicBoss neweb)
	{
		maxtime = newtime;
		boss = newboss;
		time = 0;
		spawnmsg = "";
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
	public void setText(String newmsg)
	{
		spawnmsg = newmsg;
	}
	public String getText()
	{
		return spawnmsg;
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
