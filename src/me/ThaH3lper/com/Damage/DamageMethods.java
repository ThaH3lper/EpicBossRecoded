package me.ThaH3lper.com.Damage;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;


public class DamageMethods {
	private EpicBoss eb;
	public DamageMethods(EpicBoss boss)
	{
		eb = boss;
	}
	public void deathBoss(Boss b)
	{
		eb.timerstuff.Death(b);
		if(b.getItems() != null)
		{
			for(String s:b.getItems())
				eb.dropitems.dropItems(eb.loaditems.getItem(s), eb.loaditems.getItemChance(s), b.getLocation(), eb.loaditems.getDisplayName(s));
		}
		if(b.getLivingEntity() != null)
		{
			b.getLivingEntity().remove();
		}
		eb.BossList.remove(b);
	}
}
