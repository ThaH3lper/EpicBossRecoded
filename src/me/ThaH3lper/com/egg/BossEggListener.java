package me.ThaH3lper.com.egg;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.LoadBosses.LoadBoss;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BossEggListener implements Listener{
	private EpicBoss eb;
	public BossEggListener(EpicBoss boss)
	{
		eb = boss;
	}
	@EventHandler(priority=EventPriority.HIGH)
	  public void UseEgg(PlayerInteractEvent e)
	{
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(e.getItem() != null)
			{
			if(e.getItem().getTypeId() == 383)
			{
				if(e.getItem().getItemMeta().hasLore())
				{
					List<String> list = e.getItem().getItemMeta().getLore();
					if(list.get(0).equals(ChatColor.DARK_PURPLE +"" + ChatColor.ITALIC + "A very mysterious egg"))
					{
						final LoadBoss lb = getloadBossforEgg(list.get(2));
						if(lb != null)
						{
							ItemStack stack = e.getItem();
							if(stack.getAmount() == 1)
							{
								stack = new ItemStack(Material.AIR, 1);
								e.getPlayer().setItemInHand(stack);
							}
							else
							{
								stack.setAmount(stack.getAmount() - 1);
							}
							Player player = e.getPlayer();
							final Item item = e.getPlayer().getWorld().dropItem(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1.4,player.getLocation().getZ()), new ItemStack(383, 0));
					    	Vector dir = player.getLocation().getDirection();
					        Vector vec = new Vector(dir.getX(), dir.getY(), dir.getZ()).multiply(1);
					        item.setVelocity(vec);
					        
					        eb.getServer().getScheduler().scheduleSyncDelayedTask(eb, new Runnable() {

					        	   public void run() {
									eb.BossList.add(new Boss(lb.getName(), lb.getHealth(), item.getLocation(), lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems(), lb.getSkills()));
										
									//Call despawner/spawner for instant spawn!
									eb.timer.despawn.DeSpawnEvent(eb);
					        		item.remove();
					        	   }
					        	}, 60L);
						}
					}
				}
			}
			}
		}
	}
	@EventHandler(priority=EventPriority.HIGH)
	  public void ShootEgg(BlockDispenseEvent e)
	{
		if(e.getItem().getTypeId() == 383)
		{
			if(e.getItem().getItemMeta().hasLore())
			{
				List<String> list = e.getItem().getItemMeta().getLore();
				if(list.get(0).equals(ChatColor.DARK_PURPLE +"" + ChatColor.ITALIC + "A very mysterious egg"))
				{
					final LoadBoss lb = getloadBossforEgg(list.get(2));
					if(lb != null)
					{
						Boss b = new Boss(lb.getName(), lb.getHealth(), e.getBlock().getLocation(), lb.getType(), lb.getDamage(), lb.getShowhp(), lb.getItems(), lb.getSkills());
						eb.BossList.add(b);			
						eb.timer.despawn.DeSpawnEvent(eb);
						if(b.getLivingEntity() != null)
							b.getLivingEntity().setVelocity(new Vector(0f, 0.5f, 0f));
						
				}
			}
		}
		}
	}
	public LoadBoss getloadBossforEgg(String s)
	{
		if(eb.BossLoadList != null)
		{
			for(LoadBoss lb: eb.BossLoadList)
			{
				String str = ChatColor.DARK_PURPLE +"" + ChatColor.ITALIC + lb.getName();
				if(str.equals(s))
				{
					return lb;
				}
			}
		}
		return null;
	}
	
}
