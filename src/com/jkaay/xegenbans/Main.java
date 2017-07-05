package com.jkaay.xegenbans;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.jkaay.xegenbans.commands.XegenCommands;
import com.jkaay.xegenbans.listeners.InventoryClick;
import com.jkaay.xegenbans.listeners.PlayerChat;
import com.jkaay.xegenbans.listeners.PlayerLogin;
import com.jkaay.xegenbans.listeners.StaffLogin;
import com.jkaay.xegenbans.listeners.frozen.BlockBreak;
import com.jkaay.xegenbans.listeners.frozen.BlockPlace;
import com.jkaay.xegenbans.listeners.frozen.PlayerCommandPreprocess;
import com.jkaay.xegenbans.listeners.frozen.PlayerMove;

public class Main extends JavaPlugin {

	public static Main instance;
	
	public void onEnable() {
    	instance = this;
		getConfig();
		getConfig().addDefault("reported_message", "&bThanks for reporting &3%player% &bfor: &3%reason%. &bA staff member will look into this asap.");

		saveDefaultConfig();
		registerCommands();
		regsiterListeners();
    	getLogger().info("--- XegenBans Has been Enabled Successfully ---");
	}
	
	private void regsiterListeners() {
    	PluginManager pm = Bukkit.getPluginManager();
    	pm.registerEvents(new InventoryClick(), this);
    	pm.registerEvents(new PlayerChat(), this);
    	pm.registerEvents(new PlayerLogin(), this);
    	pm.registerEvents(new PlayerMove(), this);
    	pm.registerEvents(new StaffLogin(), this);
    	pm.registerEvents(new PlayerCommandPreprocess(), this);
    	pm.registerEvents(new BlockBreak(), this);
    	pm.registerEvents(new BlockPlace(), this);;
	}

	private void registerCommands() {
    	getCommand("report").setExecutor(new XegenCommands());
    	getCommand("ban").setExecutor(new XegenCommands());
    	getCommand("unban").setExecutor(new XegenCommands());
    	getCommand("kick").setExecutor(new XegenCommands());
    	getCommand("staff").setExecutor(new XegenCommands());
    	getCommand("freeze").setExecutor(new XegenCommands());
    	getCommand("unfreeze").setExecutor(new XegenCommands());
    	getCommand("reports").setExecutor(new XegenCommands());
    	getCommand("banlist").setExecutor(new XegenCommands());
	}
	
	public void onDisable() {
		YamlConfiguration.loadConfiguration(new File(getDataFolder(), "mutes.yml"));
    	getLogger().info("--- XegenBans Has been Disabled ---");
    	instance = null;
	}
	
	public static Main getInstance() {
    	return instance;
    }
}
