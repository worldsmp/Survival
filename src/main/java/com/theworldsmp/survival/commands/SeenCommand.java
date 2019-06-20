package com.theworldsmp.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.theworldsmp.survival.utils.ConfigHandler;
import com.theworldsmp.survival.utils.UUIDFetcher;

public class SeenCommand implements CommandExecutor {

	ConfigHandler conf = ConfigHandler.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!sender.hasPermission("worldsmp.seen")) {

			sender.sendMessage(ChatColor.RED + "No permission.");
			return false;
		}

		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /seen <playername>");
			return false;
		}

		if (args.length == 1) {

			String player = null;

			try {
				player = UUIDFetcher.getUUID(args[0]).toString();
			} catch (final Exception e) {
				e.printStackTrace();
				sender.sendMessage(ChatColor.RED + "Couldn't fetch UUID from API. Invalid player?");
				return false;
			}

			if (player != null) {
				if (!this.conf.getData().contains(player)) {
					sender.sendMessage(ChatColor.RED + "That player has never been online before or does not exist in data file.");
					return false;
				}

				if (Bukkit.getPlayerExact(args[0]) != null && Bukkit.getPlayerExact(args[0]).isOnline()) {
					sender.sendMessage(ChatColor.GREEN + "Player is currently online!");
					return false;
				}

			}

			sender.sendMessage(ChatColor.AQUA + "That player has last been seen at: " + ChatColor.DARK_AQUA + ChatColor.BOLD + this.conf.getData().getString(player));
			return true;
		}

		return false;
	}
}
