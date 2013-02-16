package me.ThaH3lper.com.Api;


import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

public class BossSkillEvent extends Event {
	
	private EpicBoss eb;
	private String name;
	private Boolean custom;
	private Boss boss;
	private static final HandlerList handlers = new HandlerList();
	 
	public BossSkillEvent(EpicBoss neb, Boss b, String nname, Boolean ncustom)
	{
		eb = neb;
		boss = b;
		name = nname;
		custom = ncustom;
	}
	
	public String getBossName(){
		return boss.getName();
	}
		
	public String getSkillName(){
		return name;
	}
	
	public boolean isCustomSkill(){
		return custom;
	}
	
	public LivingEntity getBoss(){
		return boss.getLivingEntity();
	}
	 
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
