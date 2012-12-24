package me.ThaH3lper.com.LoadBosses;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class LoadItems {
	public ItemStack getItem(String s)
	{
		String[] parts = s.split(" ");
		
		//FirstPart
		String first = parts[0];
		String[] PartItems = first.split(":");
		Integer id = Integer.parseInt(PartItems[0]);
		Integer data = Integer.parseInt(PartItems[1]);
		Integer amount = Integer.parseInt(PartItems[2]);
			
		int data2 = (int) data;
		
		//Create a Itemstack
		ItemStack stack = new ItemStack(id, amount, (short) data2);
		
		//ThirdPart if it exicts
		if(parts.length == 3)
		{						
			String[] Enchants = parts[2].split(",");
			for(String enc : Enchants)
			{
				String[] EnchantsParts = enc.split(":");
				int level = Integer.parseInt(EnchantsParts[1]);
				Enchantment enchantment = Enchantment.getByName(EnchantsParts[0]);
				stack.addEnchantment(enchantment, level);
			}
		}
		return stack;
	}
	public float getItemChance(String s)
	{
		String[] parts = s.split(" ");
		Float chance = Float.parseFloat(parts[1]);
		return chance;
	}
}
