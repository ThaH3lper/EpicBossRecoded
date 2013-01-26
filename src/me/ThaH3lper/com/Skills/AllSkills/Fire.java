package me.ThaH3lper.com.Skills.AllSkills;

import java.util.Random;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Api.BossSkillEvent;
import me.ThaH3lper.com.Boss.Boss;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Fire {
	private EpicBoss eb;
	Random r = new Random();
	
	//- fire radious:duraction =HP Chance
    //- swarm zombie:3 <500 0.2
    //- swarm pig:2 >500 0.1
    
	public Fire(EpicBoss boss)
	{
		eb = boss;
	}
	public void executeFire(String s, Boss b, int index)
	{
		String[] parts = s.split(" ");
		String[] settings = parts[1].split(":");
		float chance = Float.parseFloat(parts[3]);
		if(parts[2].contains(">"))
		{
			String exe = parts[2].replace(">", "");
			if(b.getHealth() > Integer.parseInt(exe))
			{
				firePlayer(Integer.parseInt(settings[0]), Integer.parseInt(settings[1]), b, chance);
			}
		}
		else if(parts[2].contains("="))
		{
			String exe = parts[2].replace("=", "");
			if(b.getHealth() <= Integer.parseInt(exe))
			{
				firePlayer(Integer.parseInt(settings[0]), Integer.parseInt(settings[1]), b, chance);
				b.setRemoveSkill(index);
			}
		}
		else if(parts[2].contains("<"))
		{
			String exe = parts[2].replace("<", "");
			if(b.getHealth() < Integer.parseInt(exe))
			{
				firePlayer(Integer.parseInt(settings[0]), Integer.parseInt(settings[1]), b, chance);
			}
		}
		else if(parts[2].contains("/"))
		{
			String exe = parts[2].replace("/", "");
			String[] value = exe.split("-");
			if(b.getHealth() < Integer.parseInt(value[0]) && b.getHealth() > Integer.parseInt(value[1]))
			{
				firePlayer(Integer.parseInt(settings[0]), Integer.parseInt(settings[1]), b, chance);
			}
		}
	}
	public void firePlayer(int radious, int duraction, Boss b, float chance)
	{
		if(r.nextFloat() <= chance)
		{
			eb.skillhandler.event = new BossSkillEvent(eb, b, "fire", false);
			Bukkit.getServer().getPluginManager().callEvent(eb.skillhandler.event);
			if(eb.skillhandler.getPlayersRadious(radious, b) != null)
			{
				for(Player p : eb.skillhandler.getPlayersRadious(radious, b))
				{
					p.setFireTicks(duraction * 20);
				}
			}
		}
	}
}
