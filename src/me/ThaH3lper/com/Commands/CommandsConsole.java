package me.ThaH3lper.com.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandsConsole {
	public void Command (CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		sender.sendMessage("You are in Console");
	}
}
