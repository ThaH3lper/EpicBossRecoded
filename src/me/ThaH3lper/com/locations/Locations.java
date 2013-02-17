package me.ThaH3lper.com.locations;

import org.bukkit.Location;

public class Locations {
	private Location location;
	private String name;
	private String World;
	
	public Locations(Location loc, String newname, String worldname)
	{
		location = loc;
		name = newname;
		World = worldname;
	}
	
	public Location getLocation()
	{
		return location;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getWorldName()
	{
		return World;
	}
}
