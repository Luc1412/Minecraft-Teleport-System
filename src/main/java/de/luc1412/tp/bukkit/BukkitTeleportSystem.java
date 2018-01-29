package de.luc1412.tp.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

public class BukkitTeleportSystem extends JavaPlugin {

	private BukkitTeleportSystem instance;

	@Override
	public void onEnable() {
		instance = this;
	}

	@Override
	public void onDisable() {

	}

	public BukkitTeleportSystem getInstance() {
		return instance;
	}
}
