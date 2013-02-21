package me.ThaH3lper.com.Commands;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.LoadBosses.LoadBoss;
import me.ThaH3lper.com.Timer.Timer;
import me.ThaH3lper.com.locations.Locations;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandsConsole {
	EpicBoss eb;
	public CommandsConsole(EpicBoss neweb)
	{
		eb = neweb;
	}
	public void Command (CommandSender sender, Command cmd, String commandlabel, String[] args)
	{

		if(args.length == 0)
		{
			sender.sendMessage(ChatColor.RED + "/eb boss spawn <BossName> <location>" + ChatColor.GRAY + ChatColor.ITALIC + " Spawn a Boss");
			sender.sendMessage(ChatColor.RED + "/eb boss spawn <BossName> <Player>" + ChatColor.GRAY + ChatColor.ITALIC + " Spawn a Boss at Player");
			sender.sendMessage(ChatColor.RED + "/eb boss killall " + ChatColor.GRAY + ChatColor.ITALIC + " kills all bosses");
			sender.sendMessage(ChatColor.RED + "/eb boss killtype <BossName " + ChatColor.GRAY + ChatColor.ITALIC + " kills all bosses with that name");
			sender.sendMessage(ChatColor.RED + "/eb location list" + ChatColor.GRAY + ChatColor.ITALIC + " list all locations");
			sender.sendMessage(ChatColor.RED + "/eb timers <name> <boss> <location> <h:m:s>" + ChatColor.GRAY + ChatColor.ITALIC + " Create a new timer");
			sender.sendMessage(ChatColor.RED + "/eb timers remove <name>" + ChatColor.GRAY + ChatColor.ITALIC + " remove timer");
			sender.sendMessage(ChatColor.RED + "/eb timers list" + ChatColor.GRAY + ChatColor.ITALIC + " list all timers");
			sender.sendMessage(ChatColor.RED + "/eb timers info <name>" + ChatColor.GRAY + ChatColor.ITALIC + " get info about timer");
			sender.sendMessage(ChatColor.RED + "/eb reload" + ChatColor.GRAY + ChatColor.ITALIC + " Reload changes in Bosses.yml");
		}
		if(args.length == 1)
		{
			if(args[0].equals("reload"))
			{
				eb.loadconfig.LoadBosses();
				sender.sendMessage(ChatColor.GREEN + "EpicBoss reloded!");
			}
			
		}
		if(args.length == 2)
		{
			if(args[1].equals("killall") && args[0].equals("boss"))
			{
				if(eb.BossList != null)
				{
					List<Boss> remove = new ArrayList<Boss>();
					int i = 0;
					while(i < eb.BossList.size())
					{
						if(eb.BossList.get(i).getTimer().equals("null"))
						{
							remove.add(eb.BossList.get(i));
						}					
						i++;
					}
					for(Boss b: remove)
					{
						if(b.getLivingEntity() != null)
							b.getLivingEntity().remove();
					}
					eb.BossList.removeAll(remove);
					sender.sendMessage(ChatColor.GREEN + "All Bosses removed!");
				}
			}
			if(args[1].equals("list") && args[0].equals("location"))
			{
				String string = "";
				if(eb.LocationList != null)
				{
					for(Locations loc: eb.LocationList)
					{
						string += ChatColor.DARK_RED + loc.getName() + ChatColor.GRAY + ", ";
					}
				}
				if(string.equals(""))
				{
					string = ChatColor.RED + "There is no Locations :o";
				}
				sender.sendMessage(string);
			}
			if(args[1].equals("list") && args[0].equals("timers"))
			{
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
				sender.sendMessage(string);
			}
		}
		if(args.length == 3)
		{
			if(args[1].equals("killtype") && args[0].equals("boss"))
			{
				if(eb.BossList != null)
				{
					List<Boss> remove = new ArrayList<Boss>();
					int i = 0;
					if(eb.loadconfig.getLoadBoss(args[2]) != null)
					{
					while(i < eb.BossList.size())
					{
						if(eb.BossList.get(i).getTimer().equals("null"))
						{
							if(eb.BossList.get(i).getName().equals(args[2]))
								remove.add(eb.BossList.get(i));
						}					
						i++;
					}
					for(Boss b: remove)
					{
						if(b.getLivingEntity() != null)
							b.getLivingEntity().remove();
					}
					eb.BossList.removeAll(remove);
					sender.sendMessage(ChatColor.GREEN + "All " + ChatColor.DARK_PURPLE + args[2] + ChatColor.GREEN + " removed!");
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "There is no boss called " + args[2]);
					}
				}
			}
			if(args[1].equals("remove") && args[0].equals("timers"))
			{
				if(eb.timerstuff.getTimer(args[2]) != null)
				{
					eb.timerstuff.removeTimer(args[2]);
					sender.sendMessage(ChatColor.GREEN + "Timer " + ChatColor.DARK_PURPLE + args[2] + ChatColor.GREEN + " removed!");
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "That Timer does not exict");
				}
			}
			if(args[1].equals("info") && args[0].equals("timers"))
			{
				if(eb.timerstuff.getTimer(args[2]) != null)
				{	
					Timer time = eb.timerstuff.getTimer(args[2]);
					sender.sendMessage(ChatColor.GREEN + "Name: " + ChatColor.DARK_PURPLE + time.getName());
					sender.sendMessage(ChatColor.GREEN + "Boss type: " + ChatColor.DARK_PURPLE + time.getBossName());
					sender.sendMessage(ChatColor.GREEN + "Location: " + ChatColor.DARK_PURPLE + time.getLocationStr());			
					sender.sendMessage(ChatColor.GREEN + "Respawn time: " + ChatColor.DARK_PURPLE + getTime(time.getMaxTime()));
					sender.sendMessage(ChatColor.GREEN + "Respawn in: " + ChatColor.DARK_PURPLE + getTime(time.getTime()));
					sender.sendMessage(ChatColor.GREEN + "(If Respawn in equals -1sec then the boss has spawned)");
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "That Timer does not exict");
				}
			}
		}
		if(args.length == 4)
		{
			if(args[1].equals("spawn") && args[0].equals("boss"))
			{
				String name = args[2];
				LoadBoss lb = eb.loadconfig.getLoadBoss(name);
				if(lb != null)
				{
					if(eb.locationstuff.locationExict(args[3]))
					{
						Locations loc = eb.locationstuff.getLocations(args[3]);
						sender.sendMessage(ChatColor.GREEN + "You spawned " + ChatColor.DARK_PURPLE + lb.getName() + ChatColor.GREEN + " and he has " + ChatColor.DARK_PURPLE + lb.getHealth() + ChatColor.GREEN + " Hp at " + ChatColor.DARK_PURPLE + args[3]);
						eb.BossList.add(new Boss(lb.getName(), lb.getHealth(), loc.getLocation(), lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems(), lb.getSkills()));
						
						eb.timer.despawn.DeSpawnEvent(eb);
					}
					else if(eb.locationstuff.getPlayer(args[3]) != null)
					{
						Location loc = eb.locationstuff.getPlayer(args[3]).getLocation();
						sender.sendMessage(ChatColor.GREEN + "You spawned " + ChatColor.DARK_PURPLE + lb.getName() + ChatColor.GREEN + " and he has " + ChatColor.DARK_PURPLE + lb.getHealth() + ChatColor.GREEN + " Hp at player " + ChatColor.DARK_PURPLE + args[3]);
						eb.BossList.add(new Boss(lb.getName(), lb.getHealth(), loc, lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems(), lb.getSkills()));
						
						eb.timer.despawn.DeSpawnEvent(eb);
					}
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
						sender.sendMessage(ChatColor.GREEN + "Timer " + ChatColor.DARK_PURPLE + args[1] + ChatColor.GREEN + " added!");
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "That location does not exict!");
					}
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "That boss does not exict!");
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "Timer already exict with that name!");
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
