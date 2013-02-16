package me.ThaH3lper.com.Skills.AllSkills;

import java.util.Random;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Api.BossSkillEvent;
import me.ThaH3lper.com.Boss.Boss;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleport {
	private EpicBoss eb;
	Random r = new Random();
	
	//- bossmsg radius:&2I_Will_Kill_You! <100 0.25
	
	public Teleport(EpicBoss boss)
	{
		eb = boss;
	}
	public void executeTeleport(String s, Boss b, int index, Player p)
	{
		String[] parts = s.split(" ");
		String[] settings = parts[1].split(":");
		float chance = Float.parseFloat(parts[3]);
		if(parts[2].contains(">"))
		{
			String exe = parts[2].replace(">", "");
			if(b.getHealth() > Integer.parseInt(exe))
			{
				doTeleport(settings[0], chance, b, p);
			}
		}
		else if(parts[2].contains("="))
		{
			String exe = parts[2].replace("=", "");
			if(b.getHealth() <= Integer.parseInt(exe))
			{
				doTeleport(settings[0], chance, b, p);
				b.setRemoveSkill(index);
			}
		}
		else if(parts[2].contains("<"))
		{
			String exe = parts[2].replace("<", "");
			if(b.getHealth() < Integer.parseInt(exe))
			{
				doTeleport(settings[0], chance, b, p);
			}
		}
		else if(parts[2].contains("/"))
		{
			String exe = parts[2].replace("/", "");
			String[] value = exe.split("-");
			if(b.getHealth() < Integer.parseInt(value[0]) && b.getHealth() > Integer.parseInt(value[1]))
			{
				doTeleport(settings[0], chance, b, p);
			}
		}
	}
	public void doTeleport(String s, float chance, Boss b, Player pla)
	{
		if(r.nextFloat() <= chance)
		{
			if(pla != null)
			{
				if(s.equalsIgnoreCase("teleport"))
				{
					b.getLivingEntity().teleport(pla.getLocation());
				}
				else
				{
					Location l = b.getLocation();
					b.getLivingEntity().teleport(pla.getLocation());
					pla.teleport(l);
				}
				eb.skillhandler.event = new BossSkillEvent(eb, b, "teleport", false);
				Bukkit.getServer().getPluginManager().callEvent(eb.skillhandler.event);
			}
		}
	}

}
