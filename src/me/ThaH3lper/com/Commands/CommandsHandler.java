package me.ThaH3lper.com.Commands;

import me.ThaH3lper.com.EpicBoss;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsHandler implements CommandExecutor{
	private CommandsConsole CC;
	private CommandsPlayer CP;
	private EpicBoss eb;
	
	public CommandsHandler(EpicBoss boss)
	{
		eb = boss;
		CP = new CommandsPlayer(eb);
		CC = new CommandsConsole();
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		if(sender instanceof Player)
		{
			CP.Command(sender, cmd, commandlabel, args);
		}
		else
		{
			CC.Command(sender, cmd, commandlabel, args);
		}
		return false;
		
	}

}
