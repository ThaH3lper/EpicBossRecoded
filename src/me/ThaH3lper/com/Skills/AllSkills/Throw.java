package me.ThaH3lper.com.Skills.AllSkills;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

public class Throw {
	private EpicBoss eb;
	Random r = new Random();
	
	public Throw(EpicBoss boss)
	{
		eb = boss;
	}
	public void executeThrow(String s, Boss b, int index)
	{
		String[] parts = s.split(" ");
		String[] settings = parts[1].split(":");
		float chance = Float.parseFloat(parts[3]);
		if(parts[2].contains(">"))
		{
			String exe = parts[2].replace(">", "");
			if(b.getHealth() > Integer.parseInt(exe))
			{
				throwPlayer(Integer.parseInt(settings[0]), Float.parseFloat(settings[1]), chance, b);
			}
		}
		else if(parts[2].contains("="))
		{
			String exe = parts[2].replace("=", "");
			if(b.getHealth() <= Integer.parseInt(exe))
			{
				throwPlayer(Integer.parseInt(settings[0]), Float.parseFloat(settings[1]), chance, b);
				b.setRemoveSkill(index);
			}
		}
		else if(parts[2].contains("<"))
		{
			String exe = parts[2].replace("<", "");
			if(b.getHealth() < Integer.parseInt(exe))
			{
				throwPlayer(Integer.parseInt(settings[0]), Float.parseFloat(settings[1]), chance, b);
			}
		}
	}
	public void throwPlayer(int radious, float strength, float chance, Boss b)
	{
		if(r.nextFloat() <= chance)
		{
			if(eb.skillhandler.getPlayersRadious(radious, b) != null)
			{
				for(Player p : eb.skillhandler.getPlayersRadious(radious, b))
				{
					p.setVelocity(new Vector(0, strength, 0));
				}
			}
		}
	}

}
