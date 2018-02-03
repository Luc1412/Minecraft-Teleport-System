package de.luc1412.tp.bungee.commands;

import de.luc1412.tp.bungee.BungeeTeleportSystem;
import de.luc1412.tp.bungee.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SetHomeCommand extends Command {


	public SetHomeCommand(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = ((ProxiedPlayer) sender);

			if (player.hasPermission("tp.sethome")) {
				if (args.length == 1) {
					try {
						int home = Integer.parseInt(args[0]);
						int maxHomes = Utils.getMaxHomes(player);
						if (home >= 1) {
							if (home <= maxHomes) {

							} else
								player.sendMessage(new TextComponent(BungeeTeleportSystem.getConfigManager().getVarMessage("Home.ToHigh").replace("%maxHomes%", String.valueOf(maxHomes))));
						} else player.sendMessage(BungeeTeleportSystem.getConfigManager().getMessage("Home.ToLow"));
					} catch (NumberFormatException ex) {
						player.sendMessage(BungeeTeleportSystem.getConfigManager().getMessage("SetHome.Help"));
					}
				} else player.sendMessage(BungeeTeleportSystem.getConfigManager().getMessage("SetHome.Help"));
			} else player.sendMessage(BungeeTeleportSystem.getConfigManager().getMessage("NoPermission"));
		}
	}
}
