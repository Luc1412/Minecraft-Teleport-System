package de.luc1412.tp.bungee;

import de.luc1412.tp.bungee.commands.SetHomeCommand;
import de.luc1412.tp.bungee.utils.BungeeMessenger;
import de.luc1412.tp.bungee.utils.ConfigManager;
import de.luc1412.tp.bungee.utils.DatabaseManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeTeleportSystem extends Plugin {

	private static BungeeTeleportSystem instance;

	private static ConfigManager configManager;
	private static BungeeMessenger bungeeMessenger;
	private static DatabaseManager databaseManager;

	public static BungeeTeleportSystem getInstance() {
		return instance;
	}

	private void init() {
		PluginManager pluginManager = BungeeCord.getInstance().pluginManager;
		pluginManager.registerCommand(this, new SetHomeCommand("sethome"));
		pluginManager.registerCommand(this, new SetHomeCommand("home"));
		pluginManager.registerCommand(this, new SetHomeCommand("setwarp"));
		pluginManager.registerCommand(this, new SetHomeCommand("warp"));

	}

	@Override
	public void onDisable() {

	}

	public static ConfigManager getConfigManager() {
		return configManager;
	}

	public static BungeeMessenger getBungeeMessenger() {
		return bungeeMessenger;
	}

	public static DatabaseManager getDatabaseManager() {
		return databaseManager;
	}

	@Override
	public void onEnable() {
		instance = this;

		configManager = new ConfigManager();
		bungeeMessenger = new BungeeMessenger();

		databaseManager = new DatabaseManager(configManager.getString("Database.Host"), configManager.getInt("Database.Port"), configManager.getString("Database.Database"), configManager.getString("Database.User"), configManager.getString("Database.Passwd"));


		init();
	}
}
