package de.luc1412.tp.bungee;

import de.luc1412.tp.bungee.commands.SetHomeCommand;
import de.luc1412.tp.bungee.utils.ConfigManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeTeleportSystem extends Plugin {

	private static BungeeTeleportSystem instance;

	private static ConfigManager configManager;

	public static BungeeTeleportSystem getInstance() {
		return instance;
	}

	public static ConfigManager getConfigManager() {
		return configManager;
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

	@Override
	public void onEnable() {
		instance = this;

		configManager = new ConfigManager();

		init();
	}
}
