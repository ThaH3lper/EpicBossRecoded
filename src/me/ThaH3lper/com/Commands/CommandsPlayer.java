package me.ThaH3lper.com.Commands;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.LoadBosses.LoadBoss;
import me.ThaH3lper.locations.Locations;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsPlayer{
	private EpicBoss eb;
	String s = ChatColor.DARK_RED + "-------------------" + ChatColor.GRAY + "[ " + ChatColor.RED + ChatColor.BOLD + "EpicBoss" + ChatColor.GRAY +" ]" + ChatColor.DARK_RED + "-------------------";
	public CommandsPlayer(EpicBoss boss)
	{
		eb = boss;
	}
	public void Command (CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		Player p = (Player) sender;
		if(args.length == 0)
		{
			Message(0, p);
		}
		if(args.length == 1)
		{
			if(args[0].equals("help"))
			{
				Message(0, p);
			}
			if(args[0].equals("reload"))
			{
				eb.loadconfig.LoadBosses();
				p.sendMessage(ChatColor.GREEN + "EpicBoss reloded!");
			}
			if(args[0].equals("bosses"))
			{
				Message(1, p);
			}
			if(args[0].equals("bossegg"))
			{
				Message(2, p);
			}
			if(args[0].equals("timers"))
			{
				Message(3, p);
			}
			if(args[0].equals("location"))
			{
				Message(4, p);
			}
		}
		if(args.length == 2)
		{
			if(args[0].equals("spawn"))
			{
				String name = args[1];
				LoadBoss lb = eb.loadconfig.getLoadBoss(name);
				if(lb != null)
				{
					p.sendMessage(ChatColor.GREEN + "You spawned " + ChatColor.DARK_PURPLE + lb.getName() + ChatColor.GREEN + " and he has " + ChatColor.DARK_PURPLE + lb.getHealth() + ChatColor.GREEN + " Hp");
					eb.BossList.add(new Boss(lb.getName(), lb.getHealth(), p.getLocation(), lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems(), lb.getSkills()));
					
					eb.timer.despawn.DeSpawnEvent(eb);
				}
			}
			if(args[0].equals("bossegg"))
			{
				eb.bossegg.giveBossEgg(p, args[1], 1);
			}
			if(args[0].equals("location") && args[1].equals("list"))
			{
				p.sendMessage(s);
				String string = "";
				if(eb.LocationList != null)
				{
					for(Locations loc: eb.LocationList)
					{
						string += ChatColor.DARK_RED + loc.getName() + ChatColor.GRAY + ", ";
					}
				}
				else
				{
					string = ChatColor.RED + "There is no Locations :o";
				}
				p.sendMessage(string);
			}
		}
		if(args.length == 3)
		{
			if(args[0].equals("spawn"))
			{
				String name = args[1];
				LoadBoss lb = eb.loadconfig.getLoadBoss(name);
				if(lb != null)
				{
					if(eb.locationstuff.locationExict(args[2]))
					{
						Locations loc = eb.locationstuff.getLocations(args[2]);
						p.sendMessage(ChatColor.GREEN + "You spawned " + ChatColor.DARK_PURPLE + lb.getName() + ChatColor.GREEN + " and he has " + ChatColor.DARK_PURPLE + lb.getHealth() + ChatColor.GREEN + " Hp at " + ChatColor.DARK_PURPLE + args[2]);
						eb.BossList.add(new Boss(lb.getName(), lb.getHealth(), loc.getLocation(), lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems(), lb.getSkills()));
						
						eb.timer.despawn.DeSpawnEvent(eb);
					}
				}
			}
			if(args[0].equals("location") && args[1].equals("add"))
			{
				if(!eb.locationstuff.locationExict(args[2]))
				{
				eb.locationstuff.addLocation(args[2], p.getLocation());
				p.sendMessage(ChatColor.GREEN + "Location " + ChatColor.DARK_PURPLE + args[2] + ChatColor.GREEN + " set!");
				}
				else
				{
					p.sendMessage(ChatColor.RED + "A location with that name allready exicts");
				}
			}
			if(args[0].equals("location") && args[1].equals("remove"))
			{
				if(eb.locationstuff.locationExict(args[2]))
				{
				eb.locationstuff.removeLocation(args[2]);
				p.sendMessage(ChatColor.GREEN + "Location " + ChatColor.DARK_PURPLE + args[2] + ChatColor.GREEN + " removed!");
				}
				else
				{
					p.sendMessage(ChatColor.RED + "That location do not exict!");
				}
			}
			if(args[0].equals("location") && args[1].equals("teleport"))
			{
				if(eb.locationstuff.locationExict(args[2]))
				{
					Locations loc = eb.locationstuff.getLocations(args[2]);
					p.teleport(loc.getLocation());
					p.sendMessage(ChatColor.GREEN + "Teleported to " + ChatColor.DARK_PURPLE + args[2]);
				}
				else
				{
					p.sendMessage(ChatColor.RED + "That location do not exict!");
				}
			}
			if(args[0].equals("bossegg"))
			{
				eb.bossegg.giveBossEgg(p, args[1], Integer.parseInt(args[2]));
			}
		}
	}
	
	
	
	
	
	
	private void Message(int i, Player p)
	{
		switch (i) 
		{
		case 0: // /eb
			p.sendMessage(s);
			p.sendMessage(ChatColor.RED + "/eb bosses" + ChatColor.GRAY + ChatColor.ITALIC + "   Commands/info about Bosses");
			p.sendMessage(ChatColor.RED + "/eb location" + ChatColor.GRAY + ChatColor.ITALIC + "  Commands/info about Locations");
			p.sendMessage(ChatColor.RED + "/eb timers" + ChatColor.GRAY + ChatColor.ITALIC + "  Commands/info about Timers");
			p.sendMessage(ChatColor.RED + "/eb bossegg" + ChatColor.GRAY + ChatColor.ITALIC + "  Commands/info about bossegg");
			p.sendMessage(ChatColor.RED + "/eb reload" + ChatColor.GRAY + ChatColor.ITALIC + "    Reload changes in Bosses.yml");
			break;
		case 1: // /eb bosses
			p.sendMessage(s);
			p.sendMessage(ChatColor.RED + "Bosses loaded:");
			if(eb.BossLoadList.size() != 0)
			{
				String bosses = "";
				for(LoadBoss lb : eb.BossLoadList)
				{
					bosses += ChatColor.DARK_RED + lb.getName() + ChatColor.GRAY + ", ";
				}
				p.sendMessage(bosses);
			}
			else
			{
				p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "There is no bosses in Bosses.yml");
			}
			p.sendMessage(ChatColor.RED + "Current bosses: " + eb.BossList.size());
			p.sendMessage(ChatColor.RED + "/eb spawn <BossName>" + ChatColor.GRAY + ChatColor.ITALIC + " Spawn a Boss");
			break;
		case 2: // /eb bossegg
			p.sendMessage(s);
			p.sendMessage(ChatColor.RED + "/eb bossegg <BossName> (amount)" + ChatColor.GRAY + ChatColor.ITALIC + " get Bossegg");
			break;
		case 3: // /eb timers
			p.sendMessage(s);
			p.sendMessage(ChatColor.RED + "/eb timer <name> <boss> <location> <h:m:s>" + ChatColor.GRAY + ChatColor.ITALIC + " Create a new timer");
			p.sendMessage(ChatColor.RED + "/eb timer remove <name>" + ChatColor.GRAY + ChatColor.ITALIC + " remove timer");
			p.sendMessage(ChatColor.RED + "/eb timer list" + ChatColor.GRAY + ChatColor.ITALIC + " list all timers");
			p.sendMessage(ChatColor.RED + "/eb timer info <name>" + ChatColor.GRAY + ChatColor.ITALIC + " get info about timer");
			p.sendMessage(ChatColor.RED + "/eb timer reset <name>" + ChatColor.GRAY + ChatColor.ITALIC + " reset the timer");
			break;
		case 4: // /eb locations
			p.sendMessage(s);
			p.sendMessage(ChatColor.RED + "/eb location add <name>" + ChatColor.GRAY + ChatColor.ITALIC + " Create a new timer");
			p.sendMessage(ChatColor.RED + "/eb location remove <name>" + ChatColor.GRAY + ChatColor.ITALIC + " remove timer");
			p.sendMessage(ChatColor.RED + "/eb location list" + ChatColor.GRAY + ChatColor.ITALIC + " list all timers");
			p.sendMessage(ChatColor.RED + "/eb location teleport <name>" + ChatColor.GRAY + ChatColor.ITALIC + " get info about timer");
			break;

		default:
			break;
		}
	}
}
