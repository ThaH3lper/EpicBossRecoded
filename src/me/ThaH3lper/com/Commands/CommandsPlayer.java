package me.ThaH3lper.com.Commands;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.LoadBosses.LoadBoss;
import me.ThaH3lper.com.LoadBosses.LoadConfigs;

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
		}
		if(args.length == 2)
		{
			if(args[0].equals("help") && args[1].equals("bossinfo"))
			{
				Message(1, p);
			}
			if(args[0].equals("spawn"))
			{
				String name = args[1];
				LoadBoss lb = eb.loadconfig.getLoadBoss(name);
				if(lb != null)
				{
					p.sendMessage(ChatColor.GREEN + "You spawned " + ChatColor.DARK_PURPLE + lb.getName() + ChatColor.GREEN + " and he has " + ChatColor.DARK_PURPLE + lb.getHealth() + ChatColor.GREEN + "Hp");
					eb.BossList.add(new Boss(lb.getName(), lb.getHealth(), p.getLocation(), lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems()));
					
					//Call despawner/spawner for instant spawn!
					eb.timer.despawn.DeSpawnEvent(eb);
				}
			}
			if(args[0].equals("bossegg"))
			{
				eb.bossegg.giveBossEgg(p, args[1], 1);
			}
		}
		if(args.length == 3)
		{
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
		case 0:
			p.sendMessage(s);
			p.sendMessage(ChatColor.RED + "/eb help bossinfo" + ChatColor.GRAY + ChatColor.ITALIC + " Show info about Bosses");
			p.sendMessage(ChatColor.RED + "/eb reload" + ChatColor.GRAY + ChatColor.ITALIC + " Reload chnages in Bosses.yml");
			break;
		case 1:
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
			p.sendMessage(ChatColor.RED + "/eb spawn <BossName>" + ChatColor.GRAY + ChatColor.ITALIC + " Spawn Boss at your location");
			p.sendMessage(ChatColor.RED + "/eb bossegg <BossName> (amount)" + ChatColor.GRAY + ChatColor.ITALIC + " getspawnegg");
			break;

		default:
			break;
		}
	}
}
