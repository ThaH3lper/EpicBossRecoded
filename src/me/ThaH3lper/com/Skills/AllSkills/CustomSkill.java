package me.ThaH3lper.com.Skills.AllSkills;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Api.BossDeathEvent;
import me.ThaH3lper.com.Api.BossSkillEvent;
import me.ThaH3lper.com.Boss.Boss;

public class CustomSkill {
	private EpicBoss eb;
	Random r = new Random();
	private BossSkillEvent event;
	
	// - throw Radious:Strenght(float) <HP chance(float)
	// - throw 20:0.2 =100 0.2
	// - throw 18:0.8 >200 1
	
	public CustomSkill(EpicBoss boss)
	{
		eb = boss;
	}
	public void executeThrow(String s, Boss b, int index)
	{
		String[] parts = s.split(" ");
		float chance = Float.parseFloat(parts[2]);
		if(parts[1].contains(">"))
		{
			String exe = parts[1].replace(">", "");
			if(b.getHealth() > Integer.parseInt(exe))
			{
				GoCustom(chance, b, parts[0]);
			}
		}
		else if(parts[1].contains("="))
		{
			String exe = parts[1].replace("=", "");
			if(b.getHealth() <= Integer.parseInt(exe))
			{
				GoCustom(chance, b, parts[0]);
				b.setRemoveSkill(index);
			}
		}
		else if(parts[1].contains("<"))
		{
			String exe = parts[1].replace("<", "");
			if(b.getHealth() < Integer.parseInt(exe))
			{
				GoCustom(chance, b, parts[0]);
			}
		}
		else if(parts[1].contains("/"))
		{
			String exe = parts[1].replace("/", "");
			String[] value = exe.split("-");
			if(b.getHealth() < Integer.parseInt(value[0]) && b.getHealth() > Integer.parseInt(value[1]))
			{
				GoCustom(chance, b, parts[0]);
			}
		}
	}
	public void GoCustom(float chance, Boss b, String name)
	{
		if(r.nextFloat() <= chance)
		{
			event = new BossSkillEvent(eb, b, name, true);
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
	}

}
