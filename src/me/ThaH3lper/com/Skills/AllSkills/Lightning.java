package me.ThaH3lper.com.Skills.AllSkills;

import java.util.Random;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

import org.bukkit.entity.Player;

public class Lightning {
	private EpicBoss eb;
	Random r = new Random();
	
	//- lightning radious:damage =HP Chance(float)
    //- swarm zombie:3 <500 0.2
    //- swarm pig:2 >500 0.1
    
	public Lightning(EpicBoss boss)
	{
		eb = boss;
	}
	public void executeLightning(String s, Boss b, int index)
	{
		String[] parts = s.split(" ");
		String[] settings = parts[1].split(":");
		float chance = Float.parseFloat(parts[3]);
		if(parts[2].contains(">"))
		{
			String exe = parts[2].replace(">", "");
			if(b.getHealth() > Integer.parseInt(exe))
			{
				lightPlayer(Integer.parseInt(settings[0]), Integer.parseInt(settings[1]), b, chance);
			}
		}
		else if(parts[2].contains("="))
		{
			String exe = parts[2].replace("=", "");
			if(b.getHealth() <= Integer.parseInt(exe))
			{
				lightPlayer(Integer.parseInt(settings[0]), Integer.parseInt(settings[1]), b, chance);
				b.setRemoveSkill(index);
			}
		}
		else if(parts[2].contains("<"))
		{
			String exe = parts[2].replace("<", "");
			if(b.getHealth() < Integer.parseInt(exe))
			{
				lightPlayer(Integer.parseInt(settings[0]), Integer.parseInt(settings[1]), b, chance);
			}
		}
	}
	public void lightPlayer(int radious, int damage, Boss b, float chance)
	{
		if(r.nextFloat() <= chance)
		{
			if(eb.skillhandler.getPlayersRadious(radious, b) != null)
			{
				for(Player p : eb.skillhandler.getPlayersRadious(radious, b))
				{
					p.getLocation().getWorld().strikeLightningEffect(p.getLocation());
					p.damage(damage);
				}
			}
		}
	}
}
