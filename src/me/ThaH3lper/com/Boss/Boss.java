package me.ThaH3lper.com.Boss;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class Boss {
	
	String name, entityspawnname;
	int MaxHealth;
	int Health;
	LivingEntity entity;
	int damage;
	boolean showHp;
	Location spawnlocation, savedlocation;
	boolean saved = true;
	
	
	public Boss(String newname, int newmaxhealth, Location newspawnlocation, String newentityspawnname, int newdamage, boolean newshowHP)
	{
		name = newname;
		MaxHealth = newmaxhealth;
		Health = newmaxhealth;
		damage = newdamage;
		showHp = newshowHP;
		spawnlocation = newspawnlocation;
		savedlocation = newspawnlocation;
		entityspawnname = newentityspawnname;
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
	
	//Needs Entity
	public LivingEntity getLivingEntity()
	{
		return entity;
	}
	public void setEntity(LivingEntity i)
	{
		entity = i;
	}
	
	//Needs Entity
	public int getId()
	{
		return entity.getEntityId();
	}
	public Location getLocation()
	{
		return entity.getLocation();
	}
	
	public Location getSpawnLocation()
	{
		return spawnlocation;
	}
	
	public void setSavedLocation(Location l)
	{
		savedlocation = l;
	}
	public Location getSavedLocation()
	{
		return savedlocation;
	}
	public void setSaved(boolean i)
	{
		saved = i;
	}
	public boolean getSaved()
	{
		return saved;
	}
	public String getEntitySpawnName()
	{
		return entityspawnname;
	}

}
