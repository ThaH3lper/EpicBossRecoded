package me.ThaH3lper.com.Api;

import org.bukkit.entity.LivingEntity;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

public class Api {
	public EpicBoss eb;
	
	public Api(EpicBoss neweb){
		eb = neweb;
	}
	
	public boolean isBoss(LivingEntity l)
	{
		if(eb.bossCalculator.isBossLiv(l))
		{
			return true;
		}
		return false;
	}
	
	public String getBossName(LivingEntity l)
	{
		Boss b = eb.bossCalculator.getBoss(l);
		return b.getName();
	}
	
	public int getMaxHealth(LivingEntity l)
	{
		Boss b = eb.bossCalculator.getBoss(l);
		return b.getMaxHealth();
	}
	
	public int getHealth(LivingEntity l)
	{
		Boss b = eb.bossCalculator.getBoss(l);
		return b.getHealth();
	}
	
	public boolean isShowingHp(LivingEntity l)
	{
		Boss b = eb.bossCalculator.getBoss(l);
		return b.getShowHp();
	}
	
	public void addNewSkill(String name)
	{
		eb.CustomSkills.add(name);
	}

}
