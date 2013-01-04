package me.ThaH3lper.com.Boss;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class Boss {
	
	private String name, entityspawnname;
	private int MaxHealth;
	private int Health;
	private LivingEntity entity;
	private int damage;
	private boolean showHp;
	private Location spawnlocation, savedlocation;
	private boolean saved = true;
	private List<String> Items, Skills;
	
	
	public Boss(String newname, int newmaxhealth, Location newspawnlocation, String newentityspawnname, int newdamage, boolean newshowHP, List<String> newItems, List<String> newSkills)
	{
		name = newname;
		MaxHealth = newmaxhealth;
		Health = newmaxhealth;
		damage = newdamage;
		showHp = newshowHP;
		spawnlocation = newspawnlocation;
		savedlocation = newspawnlocation;
		entityspawnname = newentityspawnname;
		Items = new ArrayList<String>(newItems);
		Skills = new ArrayList<String>(newSkills);
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
	
	public Location getWorkingLocation()
	{
		if(saved)
			return savedlocation;
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
	public List<String> getItems()
	{
		return Items;
	}
	public List<String> getSkill()
	{
		return Skills;
	}
	public void setRemoveSkill(int i)
	{
		Skills.set(i, "null");
	}
	public String getEntitySpawnName()
	{
		return entityspawnname;
	}

}
