package me.ThaH3lper.com.Commands;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CommandsPlayer{
	private EpicBoss eb;
	public CommandsPlayer(EpicBoss boss)
	{
		eb = boss;
	}
	public void Command (CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		Player p = (Player) sender;
		if(args[0].equals("Win"))
		{
		p.sendMessage("you are a player");
		Entity e = p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
		LivingEntity el = (LivingEntity) e;
		eb.BossList.add(new Boss("Olle", 1000, el, 100, true));
		}
		if(args[0].equals("test"))
		{
			Boss boss = eb.BossList.get(0);
			p.teleport(boss.getLivingEntity().getLocation());
		}
	}
}
