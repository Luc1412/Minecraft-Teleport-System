package de.luc1412.tp.bukkit;

import de.luc1412.tp.bukkit.utils.ServerMessenger;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class BukkitTeleportSystem extends JavaPlugin {

	private BukkitTeleportSystem instance;


	@Override
	public void onEnable() {
		instance = this;

		this.getServer().getMessenger().registerIncomingPluginChannel(this, "teleport-system", new ServerMessenger());
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "teleport-system");

	}

	@Override
	public void onDisable() {

	}

	public BukkitTeleportSystem getInstance() {
		return instance;
	}

	private void sendPlayerLocation(Player player) {

	}


}
