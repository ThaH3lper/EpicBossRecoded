package me.ThaH3lper.com.Commands;

import me.ThaH3lper.com.EpicBoss;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsPlayer{
	private EpicBoss eb;
	String s = ChatColor.DARK_RED + "-------------------" + ChatColor.GRAY + "[ " + ChatColor.RED + ChatColor.BOLD + "EpicBoss" + ChatColor.GRAY +" ]" + ChatColor.DARK_RED + "-------------------";
	
	private Bosses bosses;
	private Timers timer;
	private Location location;
	private Bossegg bossegg;
	
	public CommandsPlayer(EpicBoss boss)
	{
		eb = boss;
		bosses = new Bosses(eb);
		timer = new Timers(eb);
		location = new Location(eb);
		bossegg = new Bossegg(eb);
	}
	public void Command (CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		Player p = (Player) sender;
		if(args.length == 0)
		{
			p.sendMessage(s);
			p.sendMessage(ChatColor.RED + "/eb boss" + ChatColor.GRAY + ChatColor.ITALIC + "   Commands/info about Bosses");
			p.sendMessage(ChatColor.RED + "/eb location" + ChatColor.GRAY + ChatColor.ITALIC + "  Commands/info about Locations");
			p.sendMessage(ChatColor.RED + "/eb timers" + ChatColor.GRAY + ChatColor.ITALIC + "  Commands/info about Timers");
			p.sendMessage(ChatColor.RED + "/eb bossegg" + ChatColor.GRAY + ChatColor.ITALIC + "  Commands/info about bossegg");
			p.sendMessage(ChatColor.RED + "/eb reload" + ChatColor.GRAY + ChatColor.ITALIC + "    Reload changes in Bosses.yml");
			p.sendMessage(ChatColor.DARK_GRAY + "" +  ChatColor.ITALIC + "(Version 1.3.0, Coded by ThaH3lper)");
		}
		if(args.length >= 1)
		{
			if(args[0].equals("reload"))
			{
				if(p.hasPermission("epicboss.reload"))
				{
					eb.loadconfig.LoadBosses();
					p.sendMessage(ChatColor.GREEN + "EpicBoss reloded!");
				}
			}
			else if(args[0].equals("boss"))
			{
				if(p.hasPermission("epicboss.boss"))
				{
					bosses.Command(p, cmd, commandlabel, args);
				}
			}
			else if(args[0].equals("bossegg"))
			{
				if(p.hasPermission("epicboss.bossegg"))
				{
					bossegg.Command(p, cmd, commandlabel, args);
				}
			}
			else if(args[0].equals("timers"))
			{
				if(p.hasPermission("epicboss.timers"))
				{
					timer.Command(p, cmd, commandlabel, args);
				}
			}
			else if(args[0].equals("location"))
			{
				if(p.hasPermission("epicboss.location"))
				{
					location.Command(p, cmd, commandlabel, args);
				}
			}
			else
			{
				p.sendMessage(s);
				p.sendMessage(ChatColor.RED + "/eb boss" + ChatColor.GRAY + ChatColor.ITALIC + "   Commands/info about Bosses");
				p.sendMessage(ChatColor.RED + "/eb location" + ChatColor.GRAY + ChatColor.ITALIC + "  Commands/info about Locations");
				p.sendMessage(ChatColor.RED + "/eb timers" + ChatColor.GRAY + ChatColor.ITALIC + "  Commands/info about Timers");
				p.sendMessage(ChatColor.RED + "/eb bossegg" + ChatColor.GRAY + ChatColor.ITALIC + "  Commands/info about bossegg");
				p.sendMessage(ChatColor.RED + "/eb reload" + ChatColor.GRAY + ChatColor.ITALIC + "    Reload changes in Bosses.yml");
			}
		}
	}
}
