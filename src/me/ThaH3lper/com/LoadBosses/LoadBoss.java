package me.ThaH3lper.com.LoadBosses;

public class LoadBoss {
	public String Name;
	public String Type;
	public int Health;
	public int Damage;
	public LoadBoss(String newName, String newType, int newHealth, int newDamage)
	{
		Name = newName;
		Type = newType;
		Health = newHealth;
		Damage = newDamage;
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

}
