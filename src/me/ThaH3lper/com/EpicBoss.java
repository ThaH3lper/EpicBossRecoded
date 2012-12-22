package me.ThaH3lper.com;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.Boss.BossCalculations;
import me.ThaH3lper.com.Commands.CommandsHandler;
import me.ThaH3lper.com.Damage.DamageListener;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EpicBoss extends JavaPlugin{
	
	public final Logger logger = Logger.getLogger("Minecraft");
	//Constructor start
	public BossCalculations bossCalculator;
	
	//Important Stuff!
	public List<Boss> BossList = new ArrayList<Boss>();
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[EpicBoss-Recoded] " + pdfFile.getVersion() +  " Has Been Disabled!");	
		
	}
	@Override
	public void onEnable() {

		PluginManager manager = this.getServer().getPluginManager();
		manager.registerEvents(new DamageListener(this), this);
		
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[EpicBoss-Recoded] " + pdfFile.getVersion() +  " Has Been Enabled!");
		
		getCommand("EpicBoss").setExecutor(new CommandsHandler(this));
		
		//Constructor Give Info
		bossCalculator = new BossCalculations(this);
		

	}
}
