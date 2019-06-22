package com.theworldsmp.survival.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.theworldsmp.survival.utils.UUIDFetcher;

public class HeadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		final Player player = (Player) sender;

		if (!sender.hasPermission("worldsmp.head")) {
			sender.sendMessage(ChatColor.RED + "No permission.");
			return false;
		}

		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /head <playername>");
			return false;
		}

		if (args.length == 1) {

			if (player.getInventory().getItemInMainHand().getType().equals(Material.PLAYER_HEAD)) {

				UUID UUID = null;

				try {
					UUID = UUIDFetcher.getUUID(args[0]);
				} catch (final Exception e) {
					sender.sendMessage(ChatColor.RED + "Couldn't fetch UUID from API. Invalid player?");
					return false;
				}

				final ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
				final SkullMeta meta = (SkullMeta) skull.getItemMeta();

				meta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID));
				meta.setDisplayName(ChatColor.AQUA + args[0] + "'s Head");
				skull.setItemMeta(meta);

				player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
				player.getPlayer().getInventory().addItem(skull);
				player.sendMessage(ChatColor.AQUA + "Changed the head in your hand to player " + ChatColor.BOLD + args[0]);

			} else {
				player.sendMessage(ChatColor.RED + "You aren't holding a head.");
				return false;
			}

		}

		return false;
	}

}
