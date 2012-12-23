package me.ThaH3lper.com.Timer;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Timer.Spawn.Despawn;

public class Timer {
	
	Despawn despawn = new Despawn();
	private EpicBoss eb;
	
	@SuppressWarnings("deprecation")
	public Timer(EpicBoss boss)
	{
		eb = boss;
		eb.getServer().getScheduler().scheduleAsyncRepeatingTask(eb, new Runnable() {
		    @Override  
		    public void run() {
		        despawn.DeSpawnEvent(eb);
		    }
		}, 0L, 20L);
	}

}
