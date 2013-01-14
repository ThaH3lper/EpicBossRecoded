package me.ThaH3lper.com.Api;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

public class BossDeathEvent extends Event {
	
	private EpicBoss eb;
	private Player player;
	private Boss boss;
	private static final HandlerList handlers = new HandlerList();
	 
	public BossDeathEvent(EpicBoss neb, Player p, Boss b)
	{
		eb = neb;
		player = p;
		boss = b;
	}
	
	public String getBossName(){
		return boss.getName();
	}
		
	public Player getPlayer(){
		return player;
	}
	 
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
