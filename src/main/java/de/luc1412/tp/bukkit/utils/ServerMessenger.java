package de.luc1412.tp.bukkit.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class ServerMessenger implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		DataInputStream stream = new DataInputStream(new ByteArrayInputStream(message));
		try {
			String subChannel = stream.readUTF();

			if (subChannel.equals("tp")) {
				Map<String, String> values = Utils.fromJson(stream.readUTF());


			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
