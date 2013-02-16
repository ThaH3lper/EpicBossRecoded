package me.ThaH3lper.com.Damage;

import java.util.List;

import org.bukkit.inventory.ItemStack;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;


public class DamageMethods {
	private EpicBoss eb;
	public DamageMethods(EpicBoss boss)
	{
		eb = boss;
	}
	public void deathBoss(Boss b, List<ItemStack> stack, int exp)
	{
		eb.timerstuff.Death(b);
		eb.dropitems.dropItems(stack, b, exp);
		if(b.getLivingEntity() != null)
		{
			b.getLivingEntity().remove();
		}
		eb.BossList.remove(b);
	}
}
