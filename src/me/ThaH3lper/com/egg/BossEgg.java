package me.ThaH3lper.com.egg;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.LoadBosses.LoadBoss;

public class BossEgg {
	public EpicBoss eb;
	public BossEgg(EpicBoss boss)
	{
		eb = boss;
	}
	public void giveBossEgg(Player p, String s, int amount)
	{
		LoadBoss lb = eb.loadconfig.getLoadBoss(s);
		if(lb != null)
		{
			List<String> lores = new ArrayList<String>();
			ItemStack stack = new ItemStack(383, amount);
			ItemMeta stackMeta = stack.getItemMeta();
			stackMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + lb.getName() + " SpawnEgg");
			stackMeta.addEnchant(Enchantment.DURABILITY, 10, true);
			lores.add(ChatColor.DARK_PURPLE +"" + ChatColor.ITALIC + "A very mysterious egg");
			lores.add(ChatColor.DARK_PURPLE +"" + ChatColor.ITALIC + "that can be used to bring");
			lores.add(ChatColor.DARK_PURPLE +"" + ChatColor.ITALIC + lb.getName());
			lores.add(ChatColor.DARK_PURPLE +"" + ChatColor.ITALIC + "back from the dead!");
			lores.add(ChatColor.GRAY + "Right-Click to Spawn Boss");
			stackMeta.setLore(lores);
			stack.setItemMeta(stackMeta);
			p.getInventory().addItem(stack);
		}
		else
		{
			p.sendMessage(ChatColor.DARK_RED + "There is no Boss whit that name!");
		}
	}

}
