package me.ThaH3lper.com.LoadBosses;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;

public class LoadBossEquip {
	public EpicBoss eb;
	public LoadBossEquip(EpicBoss boss)
	{
		eb = boss;
	}
	public void SetEqupiment(Boss b)
	{
		if(b.getItems() != null)
		{
			if(b.getLivingEntity() != null)
			{
				for(String s:b.getItems())
				{
					equip(s, eb.loaditems.getItem(s), b.getLivingEntity());
				}
			}
		}
	}
	public void equip(String s, ItemStack stack, LivingEntity l)
	{
		String[] parts = s.split(" ");
		String[] itemParts = parts[0].split(":");
		if(itemParts.length == 4)
		{
			EntityEquipment eq = l.getEquipment();
			
			if(itemParts[3].equals("0"))
				eq.setItemInHand(stack);
			
			if(itemParts[3].equals("1"))
				eq.setBoots(stack);
			
			if(itemParts[3].equals("2"))
				eq.setLeggings(stack);
			
			if(itemParts[3].equals("3"))
				eq.setChestplate(stack);
			
			if(itemParts[3].equals("4"))
				eq.setHelmet(stack);
		}
	}

}
