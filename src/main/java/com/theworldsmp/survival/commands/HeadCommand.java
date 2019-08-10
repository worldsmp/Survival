package com.theworldsmp.survival.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
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

				final ItemStack head = new ItemStack(Material.PLAYER_HEAD);
				final SkullMeta pMeta = (SkullMeta) head.getItemMeta();
				// TO-DO: Implement new method for setOwner, is deprecated.
				pMeta.setOwner(args[0]);
				head.setItemMeta(pMeta);

				player.getInventory().getItemInMainHand().subtract(1);
				player.getInventory().addItem(head);
				player.sendMessage(ChatColor.AQUA + "Changed the head in your hand to player " + ChatColor.BOLD + args[0]);


			} else {
				player.sendMessage(ChatColor.RED + "You aren't holding a head.");
				return false;
			}

		}

		return false;
	}

}
