package me.ThaH3lper.com;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.ThaH3lper.com.Api.Api;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.Boss.BossCalculations;
import me.ThaH3lper.com.Commands.CommandsHandler;
import me.ThaH3lper.com.Damage.DamageListener;
import me.ThaH3lper.com.Damage.DamageMethods;
import me.ThaH3lper.com.LoadBosses.DropItems;
import me.ThaH3lper.com.LoadBosses.LoadBoss;
import me.ThaH3lper.com.LoadBosses.LoadBossEquip;
import me.ThaH3lper.com.LoadBosses.LoadConfigs;
import me.ThaH3lper.com.LoadBosses.LoadItems;
import me.ThaH3lper.com.Skills.SkillsHandler;
import me.ThaH3lper.com.Timer.Timer;
import me.ThaH3lper.com.Timer.TimerSeconds;
import me.ThaH3lper.com.Timer.TimerStuff;
import me.ThaH3lper.com.egg.BossEgg;
import me.ThaH3lper.com.egg.BossEggListener;
import me.ThaH3lper.com.locations.LocationStuff;
import me.ThaH3lper.com.locations.Locations;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EpicBoss extends JavaPlugin{
	
	public final Logger logger = Logger.getLogger("Minecraft");
	
	//Constructor start
	public EpicBoss plugin;
	public BossCalculations bossCalculator;
	public Mobs mobs;
	public TimerSeconds timer;
	public SaveLoad Bosses, Options, SavedData;
	public LoadConfigs loadconfig;
	public DropItems dropitems;
	public LoadItems loaditems;
	public DamageMethods damagemethods;
	public BossEgg bossegg;
	public SkillsHandler skillhandler;
	public LoadBossEquip loadbossequip;
	public LocationStuff locationstuff;
	public TimerStuff timerstuff;
	public Api api;
	
	public String name;
	public boolean percentage;
	public boolean regain = false;
	
	//Important Stuff!
	public List<Boss> BossList = new ArrayList<Boss>();
	public List<LoadBoss> BossLoadList = new ArrayList<LoadBoss>();
	public List<Locations> LocationList = new ArrayList<Locations>();
	public List<Timer> TimersList = new ArrayList<Timer>();
	public List<String> CustomSkills = new ArrayList<String>();
	
	@Override
	public void onDisable() {
		loadconfig.SaveAllBosses();
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[EpicBoss-Recoded] " + pdfFile.getVersion() +  " Has Been Disabled!");	
		
	}
	@Override
	public void onEnable() {
		plugin = this;
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
		    @Override 
		    public void run() {

		PluginManager manager = plugin.getServer().getPluginManager();
		manager.registerEvents(new DamageListener(plugin), plugin);
		manager.registerEvents(new BossEggListener(plugin), plugin);
		
		PluginDescriptionFile pdfFile = plugin.getDescription();
		plugin.logger.info("[EpicBoss-Recoded] " + pdfFile.getVersion() +  " Has Been Enabled!");
		
		getCommand("EpicBoss").setExecutor(new CommandsHandler(plugin));
		
		//Constructor Give Info
		bossCalculator = new BossCalculations(plugin);
		mobs = new Mobs();
		Bosses = new SaveLoad(plugin, "Bosses.yml");
		Options = new SaveLoad(plugin, "Options.yml");
		SavedData = new SaveLoad(plugin, "SavedData.yml");
		loadconfig = new LoadConfigs(plugin);
		dropitems = new DropItems(plugin);
		loaditems = new LoadItems();
		damagemethods = new DamageMethods(plugin);
		bossegg = new BossEgg(plugin);
		skillhandler = new SkillsHandler(plugin);
		loadbossequip = new LoadBossEquip(plugin);
		locationstuff = new LocationStuff(plugin);
		timerstuff = new TimerStuff(plugin);
		timer = new TimerSeconds(plugin);
		api = new Api(plugin);
		
		name = Options.getCustomConfig().getString("BossTitle");
		name = ChatColor.translateAlternateColorCodes('&', name);
		
		percentage = Options.getCustomConfig().getBoolean("percentage");
		
		regain = Options.getCustomConfig().getBoolean("RegainHealth");
		
		loadconfig.LoadAllBosses();
		    }
		}, 1L);
	}
}
