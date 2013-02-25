package me.ThaH3lper.com.Skills.AllSkills;

import java.util.Random;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Api.BossSkillEvent;
import me.ThaH3lper.com.Boss.Boss;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Bossmsg {
	private EpicBoss eb;
	Random r = new Random();
	
	//- bossmsg radius:&2I_Will_Kill_You! <100 0.25
	
	public Bossmsg(EpicBoss boss)
	{
		eb = boss;
	}
	public void executeMsg(String s, Boss b, int index, Player p)
	{
		String[] parts = s.split(" ");
		String[] settings = parts[1].split(":");
		float chance = Float.parseFloat(parts[3]);
		if(parts[2].contains(">"))
		{
			String exe = parts[2].replace(">", "");
			if(b.getHealth() > Integer.parseInt(exe))
			{
				sendMsg(settings[1], chance, Integer.parseInt(settings[0]), b , p);
			}
		}
		else if(parts[2].contains("="))
		{
			String exe = parts[2].replace("=", "");
			if(b.getHealth() <= Integer.parseInt(exe))
			{
				sendMsg(settings[1], chance, Integer.parseInt(settings[0]), b, p);
				b.setRemoveSkill(index);
			}
		}
		else if(parts[2].contains("<"))
		{
			String exe = parts[2].replace("<", "");
			if(b.getHealth() < Integer.parseInt(exe))
			{
				sendMsg(settings[1], chance, Integer.parseInt(settings[0]), b, p);
			}
		}
		else if(parts[2].contains("/"))
		{
			String exe = parts[2].replace("/", "");
			String[] value = exe.split("-");
			if(b.getHealth() < Integer.parseInt(value[0]) && b.getHealth() > Integer.parseInt(value[1]))
			{
				sendMsg(settings[1], chance, Integer.parseInt(settings[0]), b, p);
			}
		}
	}
	public void sendMsg(String s, float chance, int radious, Boss b, Player pla)
	{
		if(r.nextFloat() <= chance)
		{
			if(eb.skillhandler.getPlayersRadious(radious, b) != null)
			{
				for(Player p : eb.skillhandler.getPlayersRadious(radious, b))
				{
					eb.skillhandler.event = new BossSkillEvent(eb, b, "bossmsg", false);
					Bukkit.getServer().getPluginManager().callEvent(eb.skillhandler.event);
					s = s.replace("$boss", b.getName());
					s = s.replace("_", " ");
					s = s.replace("@", ":");
					if(p != null && pla != null)
						s = s.replace("$player", pla.getName());
					s = ChatColor.translateAlternateColorCodes('&', s);
					p.sendMessage(s);
				}
			}
		}
	}

}
