package me.ThaH3lper.com.LoadBosses;

import java.util.ArrayList;
import java.util.List;

public class LoadBoss {
	public String Name;
	public String Type;
	public int Health;
	public int Damage;
	boolean Showhp;
	public List<String> Items;
	public List<String> Skillslist;
	
	public LoadBoss(String newName, String newType, int newHealth, int newDamage, List<String> newItems, boolean newShowhp, List<String> newskills)
	{
		Name = newName;
		Type = newType;
		Health = newHealth;
		Damage = newDamage;
		Items = new ArrayList<String>(newItems);
		Showhp = newShowhp;
		Skillslist = new ArrayList<String>(newskills);
	}
	public String getName()
	{
		return Name;
	}
	public String getType()
	{
		return Type;
	}
	public int getHealth()
	{
		return Health;
	}
	public int getDamage()
	{
		return Damage;
	}
	public List<String> getItems()
	{
		return Items;
	}
	public List<String> getSkills()
	{
		return Skillslist;
	}
	public boolean getShowhp()
	{
		return Showhp;
	}

}
