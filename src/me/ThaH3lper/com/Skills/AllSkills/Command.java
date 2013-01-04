package me.ThaH3lper.com.Skills.AllSkills;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

public class Command {
	private EpicBoss eb;
	Random r = new Random();
	
	//- command &2I_Will_Kill_You! <500 0.2
	
	public Command(EpicBoss boss)
	{
		eb = boss;
	}
	public void executeCommand(String s, Boss b, int index, Player p)
	{
		String[] parts = s.split(" ");
		float chance = Float.parseFloat(parts[3]);
		if(parts[2].contains(">"))
		{
			String exe = parts[2].replace(">", "");
			if(b.getHealth() > Integer.parseInt(exe))
			{
				sendCommand(parts[1], chance, p, b);
			}
		}
		else if(parts[2].contains("="))
		{
			String exe = parts[2].replace("=", "");
			if(b.getHealth() <= Integer.parseInt(exe))
			{
				sendCommand(parts[1], chance, p, b);
				b.setRemoveSkill(index);
			}
		}
		else if(parts[2].contains("<"))
		{
			String exe = parts[2].replace("<", "");
			if(b.getHealth() < Integer.parseInt(exe))
			{
				sendCommand(parts[1], chance, p, b);
			}
		}
	}
	public void sendCommand(String s, float chance, Player p, Boss b)
	{
		if(r.nextFloat() <= chance)
		{
			
			s = s.replace("_", " ");
			s = s.replace("$player", p.getName());
			s = s.replace("$boss", b.getName());
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), s);
		}
	}

}
