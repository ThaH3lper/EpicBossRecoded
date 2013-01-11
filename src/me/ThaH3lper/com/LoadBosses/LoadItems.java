package me.ThaH3lper.com.LoadBosses;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

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
		ItemMeta em = stack.getItemMeta();
		
		//ThirdPart if it exicts
		if(parts.length >= 3)
		{	
			if(!parts[2].contains("$"))
			{
				String[] Enchants = parts[2].split(",");
				for(String enc : Enchants)
				{
					String[] EnchantsParts = enc.split(":");
					int level = Integer.parseInt(EnchantsParts[1]);
					Enchantment enchantment = Enchantment.getByName(EnchantsParts[0]);
					if(id == 403)
					{
						stack = addBookEnchantment(stack, enchantment, level);
					}
					else
					{
						em.addEnchant(enchantment, level, true);
					}
				}
			}
		}
		if(id != 403)
			stack.setItemMeta(em);
		return stack;
	}
	public float getItemChance(String s)
	{
		String[] parts = s.split(" ");
		Float chance = Float.parseFloat(parts[1]);
		return chance;
	}
	public String getDisplayName (String s)
	{
		String[] parts = s.split(" ");
		if(parts.length == 3)
		{
			if(parts[2].contains("$"))
			{
				String str = parts[2];
				str = str.replace("$", "");
				str = ChatColor.translateAlternateColorCodes('&', str);
				str = str.replace("_", " ");
				return str;
			}
		}
		else if(parts.length == 4)
		{
			if(parts[3].contains("$"))
			{
				String str = parts[3];
				str = str.replace("$", "");
				str = ChatColor.translateAlternateColorCodes('&', str);
				str = str.replace("_", " ");
				return str;
			}
		}
		return null;
	}
	public ItemStack addBookEnchantment(ItemStack item, Enchantment enchantment, int level){
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }
}
