package de.luc1412.tp.bukkit.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;

public class Utils {

	public static Location decodeLocation(String string) {
		String[] splited = string.split(":");

		World world = Bukkit.getWorld(splited[0]);
		double x = Double.parseDouble(splited[1]);
		double y = Double.parseDouble(splited[2]);
		double z = Double.parseDouble(splited[3]);
		float yaw = Float.parseFloat(splited[4]);
		float pitch = Float.parseFloat(splited[5]);

		return new Location(world, x, y, z, yaw, pitch);
	}

	public static String encodeLocation(Location loc) {
		String wordName = loc.getWorld().getName();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		float yaw = loc.getYaw();
		float pitch = loc.getPitch();

		return wordName + ":" + x + ":" + y + ":" + z + ":" + yaw + ":" + pitch;
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
