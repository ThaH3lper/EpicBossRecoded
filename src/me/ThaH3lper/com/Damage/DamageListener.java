package me.ThaH3lper.com.Damage;

import me.ThaH3lper.com.EpicBoss;
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

public class DamageListener implements Listener{
	private EpicBoss eb;
	public DamageListener(EpicBoss boss)
	{
		eb = boss;
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	  public void Damage(EntityDamageByEntityEvent e)
	{
		Entity Damager = e.getDamager();
		Entity Hited = e.getEntity();
		//Make Damager to a Living entity insted of Arrow, Fireball etc
		//Also cheack if Arrow is not from dispenser;
		if(Damager instanceof Arrow)
		{
			Arrow a = (Arrow) Damager;
			Damager = a.getShooter();
			if(Damager == null && eb.bossCalculator.isBoss(Hited))
			{
				e.setCancelled(true);
				return;
			}
			else if(Damager == null)
			{
				return;
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
			int damage = e.getDamage();
			LivingEntity hited = (LivingEntity) Hited; //Hited entity is now "hited" since it's a LivingEntity
			if(eb.bossCalculator.isBoss(Hited))
			{
				e.setDamage(1);
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
						eb.damagemethods.deathBoss(boss);
					}
					else if(boss.getShowHp())
					{
						if(Damager instanceof Player)
						{
							Player p = (Player) Damager;
							String s = ChatColor.RED + boss.getName() + ChatColor.GRAY + " [" +ChatColor.DARK_RED + boss.getHealth() + ChatColor.GRAY + "/" + ChatColor.DARK_RED + boss.getMaxHealth() + ChatColor.GRAY + "]";
							p.sendMessage(s);						
						}
					}
				}
			}
			else if(eb.bossCalculator.isBoss(Damager))
			{
				Boss boss = eb.bossCalculator.getBoss(Damager);
				e.setDamage(boss.getDamage());
			}
		}
	}
	@EventHandler(priority=EventPriority.HIGH)
	  public void BossNoLose(EntityDamageEvent event)
	{
		if(event.getEntity() != null)
		{
			if(eb.bossCalculator.isBoss(event.getEntity())){
				if(!(event.getCause() == DamageCause.ENTITY_ATTACK || event.getCause() == DamageCause.PROJECTILE ||  event.getCause() == DamageCause.MAGIC))
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
	
}
