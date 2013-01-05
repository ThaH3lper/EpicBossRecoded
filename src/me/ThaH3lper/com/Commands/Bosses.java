package me.ThaH3lper.com.Commands;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.LoadBosses.LoadBoss;
import me.ThaH3lper.locations.Locations;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Bosses {
	EpicBoss eb;
	String s = ChatColor.DARK_RED + "-------------------" + ChatColor.GRAY + "[ " + ChatColor.RED + ChatColor.BOLD + "EpicBoss" + ChatColor.GRAY +" ]" + ChatColor.DARK_RED + "-------------------";
	public Bosses(EpicBoss neweb)
	{
		eb = neweb;
	}
	public void Command (Player p, Command cmd, String commandlabel, String[] args)
	{
		if(args.length == 1)
		{
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
			p.sendMessage(ChatColor.RED + "/eb boss spawn <BossName> (location)" + ChatColor.GRAY + ChatColor.ITALIC + " Spawn a Boss");
		}
		if(args.length == 3)
		{
			if(args[1].equals("spawn"))
			{
				String name = args[2];
				LoadBoss lb = eb.loadconfig.getLoadBoss(name);
				if(lb != null)
				{
					p.sendMessage(ChatColor.GREEN + "You spawned " + ChatColor.DARK_PURPLE + lb.getName() + ChatColor.GREEN + " and he has " + ChatColor.DARK_PURPLE + lb.getHealth() + ChatColor.GREEN + " Hp");
					eb.BossList.add(new Boss(lb.getName(), lb.getHealth(), p.getLocation(), lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems(), lb.getSkills()));
					
					eb.timer.despawn.DeSpawnEvent(eb);
				}
			}
		}
		if(args.length == 4)
		{
			if(args[1].equals("spawn"))
			{
				String name = args[2];
				LoadBoss lb = eb.loadconfig.getLoadBoss(name);
				if(lb != null)
				{
					if(eb.locationstuff.locationExict(args[3]))
					{
						Locations loc = eb.locationstuff.getLocations(args[3]);
						p.sendMessage(ChatColor.GREEN + "You spawned " + ChatColor.DARK_PURPLE + lb.getName() + ChatColor.GREEN + " and he has " + ChatColor.DARK_PURPLE + lb.getHealth() + ChatColor.GREEN + " Hp at " + ChatColor.DARK_PURPLE + args[3]);
						eb.BossList.add(new Boss(lb.getName(), lb.getHealth(), loc.getLocation(), lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems(), lb.getSkills()));
						
						eb.timer.despawn.DeSpawnEvent(eb);
					}
				}
			}
		}
	}
}
