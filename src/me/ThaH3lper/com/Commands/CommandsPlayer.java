package me.ThaH3lper.com.Commands;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.LoadBosses.LoadBoss;

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
		if(args.length == 2)
		{
			if(args[0].equals("help") && args[1].equals("bossinfo"))
			{
				Message(1, p);
			}
			if(args[0].equals("spawn"))
			{
				String name = args[1];
				eb.BossList.add(new Boss("Olle", 1000, p.getLocation(), name, 100, true));
			}
		}
	}
	private void Message(int i, Player p)
	{
		switch (i) 
		{
		case 0:
			p.sendMessage(s);
			p.sendMessage(ChatColor.RED + "/eb help bossinfo" + ChatColor.GRAY + ChatColor.ITALIC + " Show info about bosses");
			break;
		case 1:
			p.sendMessage(s);
			p.sendMessage(ChatColor.RED + "Bosses loaded:");
			if(eb.BossLoadList.size() != 0)
			{
				String bosses = "";
				for(LoadBoss lb : eb.BossLoadList)
				{
					bosses += ChatColor.RED + lb.getName() + ChatColor.GRAY + ", ";
				}
				p.sendMessage(bosses);
			}
			else
			{
				p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "There is no bosses in Bosses.yml");
			}
			p.sendMessage(ChatColor.RED + "Current bosses: " + eb.BossList.size());
			break;

		default:
			break;
		}
	}
}
