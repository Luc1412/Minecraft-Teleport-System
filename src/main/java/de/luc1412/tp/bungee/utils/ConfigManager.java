package de.luc1412.tp.bungee.utils;

import de.luc1412.tp.bungee.BungeeTeleportSystem;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

	private File file = new File(BungeeTeleportSystem.getInstance().getDataFolder(), "config.yml");
	private Configuration cfg;

	public ConfigManager() {
		try {
			cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createConfig() throws IOException {
		if (!file.exists()) {
			file.createNewFile();

			cfg.set("Messages.Prefix", "&7[Teleport-System]");

			cfg.set("Messages.NoPermission", "&cDazu hast du keine Rechte!");

			cfg.set("Messages.Home.Help", "&5Bitte nutze /home oder /home <1,2,3...>");
			cfg.set("Messages.Home.ToLow", "&5Bitte verwende eine positive Zahl");
			cfg.set("Messages.Home.ToHigh", "&5Du hast nur maximal %maxHomes% Homes");
			cfg.set("Messages.Home.NotSet", "§5Dieses Home hast du noch nicht gesetzt. Nutze /sethome %nbr%");


			cfg.set("Messages.SetHome.Help", "&5Bitte nutze /sethome <1,2,3...>");
		}
	}

	public TextComponent getMessage(String path) {
		String value = cfg.getString("Messages." + path);
		return (value != null) ? new TextComponent(value) : new TextComponent("§4Message not found in Messages." + path);
	}

	public String getVarMessage(String path) {
		String value = cfg.getString("Messages." + path);
		return (value != null) ? value : "§4Message not found in Messages." + path;
	}

}
