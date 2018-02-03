package de.luc1412.tp.bungee.utils;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BungeeMessenger {

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

	public void sendLocationRequest(ProxiedPlayer player) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(stream);

		try {
			out.writeUTF("tpReq");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		player.getServer().getInfo().sendData("teleport-system", stream.toByteArray());
	}
}
