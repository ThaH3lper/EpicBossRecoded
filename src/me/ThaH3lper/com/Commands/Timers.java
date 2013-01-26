package me.ThaH3lper.com.Commands;


import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Timer.Timer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Timers {
	EpicBoss eb;
	String s = ChatColor.DARK_RED + "-------------------" + ChatColor.GRAY + "[ " + ChatColor.RED + ChatColor.BOLD + "EpicBoss" + ChatColor.GRAY +" ]" + ChatColor.DARK_RED + "-------------------";
	public Timers(EpicBoss neweb)
	{
		eb = neweb;
	}
	public void Command (Player p, Command cmd, String commandlabel, String[] args)
	{
		if(args.length == 1)
		{
			p.sendMessage(s);
			p.sendMessage(ChatColor.RED + "/eb timers <name> <boss> <location> <h:m:s>" + ChatColor.GRAY + ChatColor.ITALIC + " Create a new timer");
			p.sendMessage(ChatColor.RED + "/eb timers remove <name>" + ChatColor.GRAY + ChatColor.ITALIC + " remove timer");
			p.sendMessage(ChatColor.RED + "/eb timers list" + ChatColor.GRAY + ChatColor.ITALIC + " list all timers");
			p.sendMessage(ChatColor.RED + "/eb timers info <name>" + ChatColor.GRAY + ChatColor.ITALIC + " get info about timer");
		}
		if(args.length == 2)
		{
			if(args[1].equals("list"))
			{
				p.sendMessage(s);
				String string = "";
				if(eb.TimersList != null)
				{
					for(Timer time: eb.TimersList)
					{
						string += ChatColor.DARK_RED + time.getName() + ChatColor.GRAY + ", ";
					}
				}
				if(string.equals(""))
				{
					string = ChatColor.RED + "There is no Timers :o";
				}
				p.sendMessage(string);
			}
		}
		if(args.length == 3)
		{
			if(args[1].equals("remove"))
			{
				if(eb.timerstuff.getTimer(args[2]) != null)
				{
					eb.timerstuff.removeTimer(args[2]);
					p.sendMessage(ChatColor.GREEN + "Timer " + ChatColor.DARK_PURPLE + args[2] + ChatColor.GREEN + " removed!");
				}
				else
				{
					p.sendMessage(ChatColor.RED + "That Timer does not exict");
				}
			}
			if(args[1].equals("info"))
			{
				if(eb.timerstuff.getTimer(args[2]) != null)
				{	
					Timer time = eb.timerstuff.getTimer(args[2]);
					p.sendMessage(ChatColor.GREEN + "Name: " + ChatColor.DARK_PURPLE + time.getName());
					p.sendMessage(ChatColor.GREEN + "Boss type: " + ChatColor.DARK_PURPLE + time.getBossName());
					p.sendMessage(ChatColor.GREEN + "Location: " + ChatColor.DARK_PURPLE + time.getLocationStr());			
					p.sendMessage(ChatColor.GREEN + "Respawn time: " + ChatColor.DARK_PURPLE + getTime(time.getMaxTime()));
					p.sendMessage(ChatColor.GREEN + "Respawn in: " + ChatColor.DARK_PURPLE + getTime(time.getTime()));
					p.sendMessage(ChatColor.GREEN + "(If Respawn in equals -1sec then the boss has spawned)");
				}
				else
				{
					p.sendMessage(ChatColor.RED + "That Timer does not exict");
				}
			}
		}
		if(args.length == 5)
		{
			if(eb.timerstuff.getTimer(args[1]) == null)
			{
				if(eb.loadconfig.getLoadBoss(args[2]) != null)
				{
					if(eb.locationstuff.locationExict(args[3]))
					{
						String[] Splits = args[4].split(":");
						int time = (Integer.parseInt(Splits[0]) * 60 *60) + (Integer.parseInt(Splits[1]) * 60) + (Integer.parseInt(Splits[2]));
						eb.timerstuff.addTimer(args[1], args[2], args[3], time);
						p.sendMessage(ChatColor.GREEN + "Timer " + ChatColor.DARK_PURPLE + args[1] + ChatColor.GREEN + " added!");
					}
					else
					{
						p.sendMessage(ChatColor.RED + "That location does not exict!");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "That boss does not exict!");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "Timer already exict with that name!");
			}
		}
	}
	public String getTime(int i)
	{
		int m = 0;
		int h = 0;
		int s = 0;
		if(i>=60)
		{
			s = i%60;
			i -= s;
			i = i/60;
		}
		else
		{
			s = i;
			return h + "hour(s), " + m + "minut(s), " + s + "second(s)";
		}
		if(i>=60)
		{
			m = i%60;
			i -= m;
			h = i/60;	
		}
		else
		{
			m = i;
			return h + "hour(s), " + m + "minut(s), " + s + "second(s)";
		}
		return h + "hour(s), " + m + "minut(s), " + s + "second(s)";
	}
}
