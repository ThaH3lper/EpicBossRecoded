package me.ThaH3lper.com.LoadBosses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DropItems {
	Random r = new Random();
	EpicBoss eb;
	public DropItems(EpicBoss neb)
	{
		eb = neb;
	}
	public void dropItems(List<ItemStack> items, Boss b, int exp)
	{
		for(ItemStack s : items)
		{
			b.getLocation().getWorld().dropItem(b.getLocation(), s);
		}
		if(exp != 0)
		{
			int i = 0;
			int x = exp/20;
			while(i<20)
			{
				ExperienceOrb ob = (ExperienceOrb) b.getLocation().getWorld().spawnEntity(b.getLocation(), EntityType.EXPERIENCE_ORB);
				ob.setExperience(x + 1);
				i += 1;
			}
		}
	}
	public List<ItemStack> getDrops(Boss b)
	{
		List<ItemStack> items = new ArrayList<ItemStack>();
		if(b.getItems() != null)
		{
			for(String s:b.getItems())
			{
				String[] part = s.split(" ");
				if(!part[0].equalsIgnoreCase("exp"))
				{
					ItemStack sta = eb.dropitems.getDropsItems(eb.loaditems.getItem(s), eb.loaditems.getItemChance(s), eb.loaditems.getDisplayName(s));
					if(sta != null)
					{
						items.add(sta);
					}
				}
			}	
		}
		return items;
	}
	public int getExp(Boss b)
	{
		if(b.getItems() != null)
		{
			for(String s:b.getItems())
			{
				String[] part = s.split(" ");
				if(part[0].equalsIgnoreCase("exp"))
				{
					int i = Integer.parseInt(part[1]);
					return i;
				}
			}	
		}
		return 0;
	}
	public ItemStack getDropsItems(ItemStack stack, float f, String s)
	{
		if(s != null)
		{
			ItemMeta stackMeta = stack.getItemMeta();
			stackMeta.setDisplayName(s);
			stack.setItemMeta(stackMeta);
		}
		if(r.nextFloat()<=f)
			return stack;
		
		return null;
	}

}
