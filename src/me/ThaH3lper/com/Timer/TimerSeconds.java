package me.ThaH3lper.com.Timer;

import org.bukkit.Effect;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.Timer.Spawn.Despawn;

public class TimerSeconds {

	public Despawn despawn = new Despawn();
	private EpicBoss eb;

	/*
	 * Changed to sync repeating task. -Cronos
	 */
	public TimerSeconds(EpicBoss boss)
	{
		eb = boss;
		eb.getServer().getScheduler().scheduleSyncRepeatingTask(eb, new Runnable() {
		    @Override  
		    public void run() {
		        despawn.DeSpawnEvent(eb);
		        eb.timerstuff.lower();
		        skillsShow();
		    }
		}, 0L, 20L);
	}
	public void skillsShow()
	{
		for(Boss b : eb.BossList)
		{
			if(b.getEffects() != null && b.getSaved() == false)
			{
				for(String s : b.getEffects())
				{
					if(s.equalsIgnoreCase("fire"))
					{
						b.getWorkingLocation().getWorld().playEffect(b.getWorkingLocation(), Effect.MOBSPAWNER_FLAMES, 0);
					}
					if(s.equalsIgnoreCase("ender"))
					{
						b.getWorkingLocation().getWorld().playEffect(b.getWorkingLocation(), Effect.ENDER_SIGNAL, 0);
					}					
				}
			}
		}
	}

}