package me.ThaH3lper.com.Skills.AllSkills;

import java.util.Random;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Api.BossSkillEvent;
import me.ThaH3lper.com.Boss.Boss;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Command {
	private EpicBoss eb;
	Random r = new Random();
	
	//- command &2I_Will_Kill_You! <500 0.3
	
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
		else if(parts[2].contains("/"))
		{
			String exe = parts[2].replace("/", "");
			String[] value = exe.split("-");
			if(b.getHealth() < Integer.parseInt(value[0]) && b.getHealth() > Integer.parseInt(value[1]))
			{
				sendCommand(parts[1], chance, p, b);
			}
		}
	}
	public void sendCommand(String s, float chance, Player p, Boss b)
	{
		if(r.nextFloat() <= chance)
		{
			eb.skillhandler.event = new BossSkillEvent(eb, b, "command", false);
			Bukkit.getServer().getPluginManager().callEvent(eb.skillhandler.event);
			
			s = s.replace("_", " ");
			if(p != null)
				s = s.replace("$player", p.getName());
			s = s.replace("$boss", b.getName());
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), s);
		}
	}

}
