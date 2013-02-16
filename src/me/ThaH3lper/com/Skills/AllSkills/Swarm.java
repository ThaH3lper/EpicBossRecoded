package me.ThaH3lper.com.Skills.AllSkills;

import java.util.Random;

import org.bukkit.Bukkit;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Api.BossSkillEvent;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.LoadBosses.LoadBoss;

public class Swarm {
	private EpicBoss eb;
	Random r = new Random();
	
	//- swarm Type:Amount =HP Chance(float)
    //- swarm zombie:3 <500 0.2
    //- swarm pig:2 >500 0.1
    
	public Swarm(EpicBoss boss)
	{
		eb = boss;
	}
	public void executeSwarm(String s, Boss b, int index)
	{
		String[] parts = s.split(" ");
		String[] settings = parts[1].split(":");
		int amount = Integer.parseInt(settings[1]);
		float chance = Float.parseFloat(parts[3]);
		if(parts[2].contains(">"))
		{
			String exe = parts[2].replace(">", "");
			if(b.getHealth() > Integer.parseInt(exe))
			{
				spawn(settings[0], amount, b, chance);
			}
		}
		else if(parts[2].contains("="))
		{
			String exe = parts[2].replace("=", "");
			if(b.getHealth() <= Integer.parseInt(exe))
			{
				spawn(settings[0], amount, b, chance);
				b.setRemoveSkill(index);
			}
		}
		else if(parts[2].contains("<"))
		{
			String exe = parts[2].replace("<", "");
			if(b.getHealth() < Integer.parseInt(exe))
			{
				spawn(settings[0], amount, b, chance);
			}
		}
		else if(parts[2].contains("/"))
		{
			String exe = parts[2].replace("/", "");
			String[] value = exe.split("-");
			if(b.getHealth() < Integer.parseInt(value[0]) && b.getHealth() > Integer.parseInt(value[1]))
			{
				spawn(settings[0], amount, b, chance);
			}
		}
	}
	public void spawn(String mob, int amount, Boss b, float Chance)
	{

		if(r.nextFloat() <= Chance)
		{
			eb.skillhandler.event = new BossSkillEvent(eb, b, "swarm", false);
			Bukkit.getServer().getPluginManager().callEvent(eb.skillhandler.event);
			if(mob.contains("$"))
			{
				String bossname = mob.replace("$", "");
				LoadBoss lb = eb.loadconfig.getLoadBoss(bossname);
				if(lb != null)
				{
					int i = 1;
					while( i <= amount)
					{
						i++;
						eb.BossList.add(new Boss(lb.getName(), lb.getHealth(), b.getLocation(), lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems(), lb.getSkills()));
					}
				}
			}
			else
			{
				int i = 1;
				while( i <= amount)
				{
					i++;
					eb.mobs.SpawnMob(mob, b.getLocation());
				}
			}
			eb.timer.despawn.DeSpawnEvent(eb);
		}
	}
}
