package me.ThaH3lper.com;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
import me.ThaH3lper.com.egg.BossEgg;
import me.ThaH3lper.com.egg.BossEggListener;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EpicBoss extends JavaPlugin{
	
	public final Logger logger = Logger.getLogger("Minecraft");
	
	//Constructor start
	public BossCalculations bossCalculator;
	public Mobs mobs;
	public Timer timer;
	public SaveLoad Bosses, Options, SavedData;
	public LoadConfigs loadconfig;
	public DropItems dropitems;
	public LoadItems loaditems;
	public DamageMethods damagemethods;
	public BossEgg bossegg;
	public SkillsHandler skillhandler;
	public LoadBossEquip loadbossequip;
	
	//Important Stuff!
	public List<Boss> BossList = new ArrayList<Boss>();
	public List<LoadBoss> BossLoadList = new ArrayList<LoadBoss>();
	
	@Override
	public void onDisable() {
		loadconfig.SaveAllBosses();
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[EpicBoss-Recoded] " + pdfFile.getVersion() +  " Has Been Disabled!");	
		
	}
	@Override
	public void onEnable() {

		PluginManager manager = this.getServer().getPluginManager();
		manager.registerEvents(new DamageListener(this), this);
		manager.registerEvents(new BossEggListener(this), this);
		
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[EpicBoss-Recoded] " + pdfFile.getVersion() +  " Has Been Enabled!");
		
		getCommand("EpicBoss").setExecutor(new CommandsHandler(this));
		
		//Constructor Give Info
		bossCalculator = new BossCalculations(this);
		mobs = new Mobs();
		timer = new Timer(this);
		Bosses = new SaveLoad(this, "Bosses.yml");
		Options = new SaveLoad(this, "Options.yml");
		SavedData = new SaveLoad(this, "SavedData.yml");
		loadconfig = new LoadConfigs(this);
		dropitems = new DropItems();
		loaditems = new LoadItems();
		damagemethods = new DamageMethods(this);
		bossegg = new BossEgg(this);
		skillhandler = new SkillsHandler(this);
		loadbossequip = new LoadBossEquip(this);
		
		loadconfig.LoadAllBosses();
	}
}
