package de.luc1412.tp.bungee.utils;

import de.luc1412.tp.bungee.BungeeTeleportSystem;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import org.bukkit.event.EventHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BungeeMessenger implements Listener {

	public BungeeMessenger() {
		BungeeCord.getInstance().registerChannel("teleport-system");
	}

	public void teleportPlayer(ProxiedPlayer player, ServerInfo server, String location) {
		ServerInfo currentServer = player.getServer().getInfo();

		if (!currentServer.getName().equals(server.getName())) {
			player.connect(server);
		}

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(stream);

		try {
			out.writeUTF("tp");
			Map<String, String> values = new HashMap<>();
			values.put("uuid", player.getUniqueId().toString());
			values.put("location", location);
			out.writeUTF(Utils.toJson(values));
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.sendData("teleport-system", stream.toByteArray());
	}

	public void sendLocationRequest(ProxiedPlayer player, int home) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(stream);

		try {
			Map<String, String> values = new HashMap<>();
			values.put("uuid", player.getUniqueId().toString());
			values.put("home", String.valueOf(home));
			out.writeUTF(Utils.toJson(values));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		player.getServer().getInfo().sendData("teleport-system", stream.toByteArray());
	}


	@EventHandler
	public void onPluginMessage(PluginMessageEvent event) {
		if (event.getTag().equals("teleport-system")) {
			DataInputStream stream = new DataInputStream(new ByteArrayInputStream(event.getData()));

			try {
				String channel = stream.readUTF();
				if (channel.equals("locReq")) {
					Map<String, String> values = Utils.fromJson(stream.readUTF());
					ProxiedPlayer player = BungeeCord.getInstance().getPlayer(values.get("uuid"));
					String location = values.get("location");
					BungeeTeleportSystem.getDatabaseManager().setHome(player, 0, null);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}
}
