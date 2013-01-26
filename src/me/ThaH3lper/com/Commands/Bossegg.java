package me.ThaH3lper.com.Commands;

import me.ThaH3lper.com.EpicBoss;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Bossegg {
	EpicBoss eb;
	String s = ChatColor.DARK_RED + "-------------------" + ChatColor.GRAY + "[ " + ChatColor.RED + ChatColor.BOLD + "EpicBoss" + ChatColor.GRAY +" ]" + ChatColor.DARK_RED + "-------------------";
	public Bossegg(EpicBoss neweb)
	{
		eb = neweb;
	}
	public void Command (Player p, Command cmd, String commandlabel, String[] args)
	{
		if(args.length == 1)
		{
			p.sendMessage(s);
			p.sendMessage(ChatColor.RED + "/eb bossegg <BossName> (amount)" + ChatColor.GRAY + ChatColor.ITALIC + " get Bossegg");
		}
		if(args.length == 2)
		{
			eb.bossegg.giveBossEgg(p, args[1], 1);
		}
		if(args.length == 3)
		{
			eb.bossegg.giveBossEgg(p, args[1], Integer.parseInt(args[2]));
		}
	}
}
