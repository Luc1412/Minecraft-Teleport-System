package de.luc1412.tp.bungee.utils;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Utils {

	public static int getMaxHomes(ProxiedPlayer player) {
		Collection<String> permissions = player.getPermissions();
		for (String permission : permissions) {
			if (permission.startsWith("tp.home.")) {
				String[] splited = permission.split(".");
				if (splited.length == 3) {
					return Integer.parseInt(splited[2]);
				}
			}
		}
		BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§4Error: The player " + player.getName() + " has the tp.home permission, but not the tp.home.x (x is the max amount of homes) permission!"));
		return 1;
	}

	public static String toJson(Map<String, String> values) {
		StringBuilder json = new StringBuilder("{");
		boolean comma = false;
		for (Map.Entry<String, String> entrys : values.entrySet()) {
			if (comma) {
				json.append(",");
			} else comma = true;
			json.append("\"").append(entrys.getKey().replace(",", "").replace("\"", "").replace("}", "").replace("{", "")).append("\"");
			json.append(":");
			json.append("\"").append(entrys.getValue().replace(",", "").replace("\"", "").replace("}", "").replace("{", "")).append("\"");
		}
		json.append("}");
		return json.toString();
	}

	public static Map<String, String> fromJson(String json) {
		Map<String, String> values = new HashMap<>();
		json = json.replace("{", "").replace("}", "");
		String[] splitJson = json.split(",");
		for (String element : splitJson) {
			String[] splitedElement = element.split(":");
			values.put(splitedElement[0].replace("\"", ""), splitedElement[1].replace("\"", ""));
		}
		return values;
	}

}
