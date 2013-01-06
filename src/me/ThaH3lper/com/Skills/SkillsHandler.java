package me.ThaH3lper.com.Skills;


import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.Skills.AllSkills.Bossmsg;
import me.ThaH3lper.com.Skills.AllSkills.Command;
import me.ThaH3lper.com.Skills.AllSkills.Lightning;
import me.ThaH3lper.com.Skills.AllSkills.PotionBoss;
import me.ThaH3lper.com.Skills.AllSkills.Potions;
import me.ThaH3lper.com.Skills.AllSkills.Swarm;
import me.ThaH3lper.com.Skills.AllSkills.Throw;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SkillsHandler {
	public EpicBoss eb;
	public Swarm swarm;
	public Throw throwplayer;
	public Command command;
	public Bossmsg bossmsg;
	public Potions potions;
	public PotionBoss potionboss;
	public Lightning lightning;
	
	//Constructors
	
	public SkillsHandler(EpicBoss boss)
	{
		eb = boss;
		swarm = new Swarm(eb);
		throwplayer = new Throw(eb);
		command = new Command(eb);
		bossmsg = new Bossmsg(eb);
		potions = new Potions(eb);
		potionboss = new PotionBoss(eb);
		lightning = new Lightning(eb);
		
	}
	public void skills(Boss b, Player p)
	{
		List<String> skills = b.getSkill();
		if(skills != null)
		{
			int index = 0;
			for(String s : skills)
			{
				String[] parts = s.split(" ");
				if(parts[0].equals("swarm"))
				{
					swarm.executeSwarm(s, b, index);
				}
				if(parts[0].equals("throw"))
				{
					throwplayer.executeThrow(s, b, index);
				}
				if(parts[0].equals("command"))
				{
					command.executeCommand(s, b, index, p);
				}
				if(parts[0].equals("bossmsg"))
				{
					bossmsg.executeMsg(s, b, index, p);
				}
				if(parts[0].equals("potion"))
				{
					potions.executePotions(s, b, index);
				}
				if(parts[0].equals("potionboss"))
				{
					potionboss.executeBossPotions(s, b, index);
				}
				if(parts[0].equals("lightning"))
				{
					lightning.executeLightning(s, b, index);
				}
				index++;
			}
		}
	}
	public List<Player> getPlayersRadious(int radious, Boss b)
	{
		List<Player> playerlist = new ArrayList<Player>();
		for(Player p: Bukkit.getOnlinePlayers())
		{
			if(b.getLocation().getWorld() == p.getLocation().getWorld())
			{
				if(b.getLocation().distance(p.getLocation()) < radious)
				{
					playerlist.add(p);
				}
			}
		}
		return playerlist;
		
	}
}
