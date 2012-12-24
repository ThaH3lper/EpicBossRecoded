package me.ThaH3lper.com.LoadBosses;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DropItems {
	Random r = new Random();
	public void dropItems(ItemStack stack, float f, Location l, String s)
	{
		if(s != null)
		{
			ItemMeta stackMeta = stack.getItemMeta();
			stackMeta.setDisplayName(s);
			stack.setItemMeta(stackMeta);
		}
		if(r.nextFloat()<=f)
			l.getWorld().dropItem(l, stack);
	}

}
