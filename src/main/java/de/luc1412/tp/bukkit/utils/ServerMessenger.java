package de.luc1412.tp.bukkit.utils;

import de.luc1412.tp.bukkit.BukkitTeleportSystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ServerMessenger implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		DataInputStream stream = new DataInputStream(new ByteArrayInputStream(message));
		try {
			String subChannel = stream.readUTF();

			if (subChannel.equals("tp")) {
				Map<String, String> values = Utils.fromJson(stream.readUTF());
				Player playerTP = Bukkit.getPlayer(values.get("uuid"));
				Location location = Utils.decodeLocation(values.get("location"));
			} else if (subChannel.equals("locReq")) {
				sendPlayerLocation(player);
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendPlayerLocation(Player player) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(stream);

		Location loc = player.getLocation();
		Map<String, String> values = new HashMap<>();
		values.put("uuid", player.getUniqueId().toString());
		values.put("location", Utils.encodeLocation(loc));
		try {
			out.writeUTF("locReq");
			out.writeUTF(Utils.toJson(values));
		} catch (IOException e) {
			e.printStackTrace();
		}

		player.sendPluginMessage(BukkitTeleportSystem.getInstance(), "teleport-system", stream.toByteArray());
	}
}
