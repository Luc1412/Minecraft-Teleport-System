package de.luc1412.tp.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HomeCommand extends Command {

	public HomeCommand(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = ((ProxiedPlayer) sender);
		}
	}
}
