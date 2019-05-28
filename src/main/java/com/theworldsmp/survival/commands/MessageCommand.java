package com.theworldsmp.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.theworldsmp.survival.Survival;

public class MessageCommand implements CommandExecutor {

	private final Survival survival;

	public MessageCommand(Survival survival) {
		this.survival = survival;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		final Player player = (Player) sender;

		if (args.length >= 2) {
			if (Bukkit.getPlayerExact(args[0]) != null) {
				final Player target = Bukkit.getPlayerExact(args[0]);

				final StringBuilder msg = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					msg.append(args[i]).append(" ");
				}

				player.sendMessage(ChatColor.GREEN + "(To " + ChatColor.BOLD + target.getName() + ChatColor.GREEN + ") " + ChatColor.WHITE + msg.toString());
				target.sendMessage(ChatColor.GREEN + "(From " + ChatColor.BOLD + player.getName() + ChatColor.GREEN + ") " + ChatColor.WHITE + msg.toString());

				survival.getMessageHandler().lastMessage.put(player, target);
			} else {
				player.sendMessage(ChatColor.RED + "Player not found.");
			}
		} else {
			player.sendMessage(ChatColor.RED + "Invalid Usage: /msg <player> <message>");
		}

		return false;
	}

}
