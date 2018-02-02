package de.luc1412.tp.bungee.utils;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Collection;

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

}
