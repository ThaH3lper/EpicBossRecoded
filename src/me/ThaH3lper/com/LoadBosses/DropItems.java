package me.ThaH3lper.com.LoadBosses;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class DropItems {
	Random r = new Random();
	public void dropItems(ItemStack stack, float f, Location l)
	{
		if(r.nextFloat()<=f)
			l.getWorld().dropItem(l, stack);
	}

}
