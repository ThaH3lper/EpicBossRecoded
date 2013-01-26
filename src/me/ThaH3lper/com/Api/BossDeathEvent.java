package me.ThaH3lper.com.Api;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

public class BossDeathEvent extends Event {
	
	private EpicBoss eb;
	private Player player;
	private Boss boss;
	private List<ItemStack> Drops;
	private int exp;
	private static final HandlerList handlers = new HandlerList();
	 
	public BossDeathEvent(EpicBoss neb, Player p, Boss b, List<ItemStack> nlist, int nexp)
	{
		eb = neb;
		exp = nexp;
		player = p;
		boss = b;
		Drops = nlist;
	}
	
	public String getBossName(){
		return boss.getName();
	}
		
	public Player getPlayer(){
		return player;
	}
	
	public List<ItemStack> getDrops(){
		return Drops;
	}
	
	public int getExp(){
		return exp;
	}
	public void setExp(int i){
		exp = i;
	}
	 
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
