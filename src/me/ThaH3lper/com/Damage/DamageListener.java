package me.ThaH3lper.com.Damage;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Api.BossDeathEvent;
import me.ThaH3lper.com.Boss.Boss;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.inventory.ItemStack;

import com.herocraftonline.heroes.Heroes;

public class DamageListener implements Listener{
	private EpicBoss eb;
	BossDeathEvent event;
	public DamageListener(EpicBoss boss)
	{
		eb = boss;
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	  public void Damage(EntityDamageByEntityEvent e)
	{
		if(eb.HeroesEnabled == false)
		{
			if(DamageMethod(e.getDamager(), e.getEntity(), e.getDamage()))
			{
				e.setCancelled(true);
			}
			if(eb.bossCalculator.isBoss(e.getDamager()))
			{
				Boss boss = eb.bossCalculator.getBoss(e.getDamager());
				e.setDamage(boss.getDamage());
			}
			else if(eb.bossCalculator.isBoss(e.getEntity()))
			{
				e.setDamage(1);
			}
		}
	}
	@EventHandler(priority=EventPriority.HIGH)
	  public void BossNoLose(EntityDamageEvent event)
	{
		if(event.getEntity() != null)
		{
			if(eb.bossCalculator.isBoss(event.getEntity())){
				if(!(event.getCause() == DamageCause.ENTITY_ATTACK || event.getCause() == DamageCause.PROJECTILE ||  event.getCause() == DamageCause.MAGIC ||  event.getCause() == DamageCause.CUSTOM))
				{
					event.setCancelled(true);
				}
			}
		}
	}
	@EventHandler(priority=EventPriority.HIGH)
	  public void BossNoFire(EntityCombustEvent e)
	{	
		if(eb.bossCalculator.isBoss(e.getEntity()))
		{
			e.setCancelled(true);
		}
	}
	@EventHandler(priority=EventPriority.HIGH)
	  public void NoTaming(EntityTameEvent e)
	{
		if(eb.bossCalculator.isBoss(e.getEntity()))
		{
			e.setCancelled(true);
		}
	}
	@EventHandler(priority=EventPriority.HIGH)
	  public void NoBlowCreeper(EntityExplodeEvent e)
	{
			e.getEntity().remove();
			   eb.getServer().getScheduler().scheduleSyncDelayedTask(eb, new Runnable() {
				   	public void run() {eb.timer.despawn.DeSpawnEvent(eb);}
			   }, 1L);
		
		//eb.timer.despawn.DeSpawnEvent(eb);
		
	}
	
	  public boolean DamageMethod(Entity Damager, Entity Hited, int damage)
	{
		//Entity Damager = e.getDamager();
		//Entity Hited = e.getEntity();
		//Make Damager to a Living entity insted of Arrow, Fireball etc
		//Also cheack if Arrow is not from dispenser;
		//Implement hero stuff to see if damage has changed
		if(Damager instanceof Arrow)
		{
			Arrow a = (Arrow) Damager;
			Damager = a.getShooter();
			if(Damager == null && eb.bossCalculator.isBoss(Hited))
			{
				return true;
			}
			else if(Damager == null)
			{
				return false;
			}
		}
		if(Damager instanceof Fireball)
		{
			Fireball a = (Fireball) Damager;
			Damager = a.getShooter();
		}
		if(Damager instanceof SmallFireball)
		{
			SmallFireball a = (SmallFireball) Damager;
			Damager = a.getShooter();
		}
		if(Damager instanceof Snowball)
		{
			Snowball a = (Snowball) Damager;
			Damager = a.getShooter();
		}
		if(Damager instanceof Egg)
		{
			Egg a = (Egg) Damager;
			Damager = a.getShooter();
		}
		if(Damager instanceof ThrownPotion)
		{
			ThrownPotion a = (ThrownPotion) Damager;
			Damager = a.getShooter();
		}
		if(Damager instanceof LivingEntity && Hited instanceof LivingEntity)
		{
			if(eb.bossCalculator.isBoss(Damager) == false && eb.bossCalculator.isBoss(Hited) == false)
			{
				return false;
			}
			
			
			LivingEntity hited = (LivingEntity) Hited; //Hited entity is now "hited" since it's a LivingEntity
			if(eb.bossCalculator.isBoss(Hited))
			{
				//e.setDamage(1);
				if(!eb.bossCalculator.BossHited(hited))
				{
					Boss boss = eb.bossCalculator.getBoss(Hited);
					boss.sethealth(boss.getHealth() - damage);
					hited.setHealth(hited.getMaxHealth());
					if(Damager instanceof Player)
					{
						eb.skillhandler.skills(boss, (Player) Damager);
					}
					if(boss.getHealth() <= 0)
					{
						List<ItemStack> dropItems = eb.dropitems.getDrops(boss);
						
						int exp = eb.dropitems.getExp(boss);						
						int hexp = eb.dropitems.getHeroesExp(boss);
						if(eb.HeroesEnabled)
						{
							if(Damager instanceof Player)
							{
								eb.heroes.getCharacterManager().getHero((Player) Damager).addExp(hexp, eb.heroes.getCharacterManager().getHero((Player) Damager).getHeroClass() , boss.getLocation());
							}
						}
						
						if(Damager instanceof Player)
						{
							event = new BossDeathEvent(eb, (Player) Damager, boss, dropItems, exp);
							Bukkit.getServer().getPluginManager().callEvent(event);
						}
						eb.damagemethods.deathBoss(boss, dropItems, event.getExp());
					}
					else if(boss.getShowHp())
					{
						if(Damager instanceof Player)
						{
							Player p = (Player) Damager;
							if(eb.percentage == false)
							{
									String bossName = boss.getName();
									bossName = bossName.replace("_", " ");
									String s = eb.name + ChatColor.RED + bossName + ChatColor.GRAY + " [" +ChatColor.DARK_RED + boss.getHealth() + ChatColor.GRAY + "/" + ChatColor.DARK_RED + boss.getMaxHealth() + ChatColor.GRAY + "]";
									p.sendMessage(s);							
							}
							else
							{
								double per = (((double)boss.getHealth()/boss.getMaxHealth())*10)+1;
								if(!boss.hasPercent((int)per))
								{
									String bossName = boss.getName();
									bossName = bossName.replace("_", " ");
									String s = eb.name + ChatColor.RED + bossName + ChatColor.GRAY + " [" +ChatColor.DARK_RED + ((int)per*10) + "%" + ChatColor.GRAY + "]";
									for(Player player : eb.skillhandler.getPlayersRadious(20, boss))
									{
										player.sendMessage(s);
									}
									boss.addPercent((int)per);
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
}
