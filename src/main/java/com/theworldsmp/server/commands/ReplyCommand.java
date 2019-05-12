package com.theworldsmp.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.theworldsmp.server.Survival;

import net.md_5.bungee.api.ChatColor;

public class ReplyCommand implements CommandExecutor {

	private final Survival survival;

	public ReplyCommand(Survival survival) {
		this.survival = survival;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		final Player player = (Player) sender;

		if (args.length > 0) {
			if (survival.getMessageHandler().lastMessage.containsKey(player)) {
				if (survival.getMessageHandler().lastMessage.get(player) != null) {
					final Player target = survival.getMessageHandler().lastMessage.get(player);

					final StringBuilder msg = new StringBuilder();
					for (int i = 0; i < args.length; i++) {
						msg.append(args[i]).append(" ");
					}

					player.sendMessage(ChatColor.GREEN + "(To " + ChatColor.BOLD + target.getName() + ChatColor.GREEN + ") " + ChatColor.WHITE + msg.toString());
					target.sendMessage(ChatColor.GREEN + "(From " + ChatColor.BOLD + player.getName() + ChatColor.GREEN + ") " + ChatColor.WHITE + msg.toString());

				} else {
					player.sendMessage(ChatColor.RED + "That player is no longer online.");
				}
			} else {
				player.sendMessage(ChatColor.RED + "You have nobody to respond to.");
			}

		} else {
			player.sendMessage(ChatColor.RED + "Invalid Usage: /reply <message>");
		}
		return false;
	}
}
