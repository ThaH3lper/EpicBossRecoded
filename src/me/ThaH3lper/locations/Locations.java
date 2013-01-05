package me.ThaH3lper.locations;

import org.bukkit.Location;

public class Locations {
	private Location location;
	private String name;
	
	public Locations(Location loc, String newname)
	{
		location = loc;
		name = newname;
	}
	
	public Location getLocation()
	{
		return location;
	}
	
	public String getName()
	{
		return name;
	}
}
