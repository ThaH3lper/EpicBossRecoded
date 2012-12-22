package me.ThaH3lper.com.Boss;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class Boss {
	
	String name;
	int MaxHealth;
	int Health;
	LivingEntity entity;
	int id;
	int damage;
	boolean showHp;
	Location location;
	
	
	public Boss(String newname, int newmaxhealth, LivingEntity newentity, int newdamage, boolean newshowHP)
	{
		name = newname;
		MaxHealth = newmaxhealth;
		Health = newmaxhealth;
		entity = newentity;
		id = newentity.getEntityId();
		damage = newdamage;
		showHp = newshowHP;
		location = entity.getLocation();
	}
	public int getDamage()
	{
		return damage;
	}
	public void setDamage(int i)
	{
		damage = i;
	}
	
	public boolean getShowHp()
	{
		return showHp;
	}
	public void setShowHp(boolean i)
	{
		showHp = i;
	}
	
	public int getMaxHealth()
	{
		return MaxHealth;
	}
	public void setMaxHealth(int i)
	{
		MaxHealth = i;
	}
	
	public int getHealth()
	{
		return Health;
	}
	public void sethealth(int i)
	{
		Health = i;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String i)
	{
		name = i;
	}
	
	public LivingEntity getLivingEntity()
	{
		return entity;
	}
	public void setEntity(LivingEntity i)
	{
		entity = i;
	}
	
	public int getId()
	{
		return id;
	}
	public Location getLocation()
	{
		return entity.getLocation();
	}

}
