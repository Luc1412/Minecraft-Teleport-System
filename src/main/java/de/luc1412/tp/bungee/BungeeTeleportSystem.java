package de.luc1412.tp.bungee;

import de.luc1412.tp.bungee.commands.SetHomeCommand;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeTeleportSystem extends Plugin {

	private static BungeeTeleportSystem instance;

	public static BungeeTeleportSystem getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
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
}
