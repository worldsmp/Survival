package com.theworldsmp.survival.events;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		final Player p = e.getPlayer();

		try {
			final File fileToRead = new File("plugins/Survival/motd.txt");
			@SuppressWarnings("resource")
			final BufferedReader br = new BufferedReader(new FileReader(fileToRead));
			String line = null;

			while ((line = br.readLine()) != null) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
			}

		} catch (final FileNotFoundException err) {
			if (p.hasPermission("worldsmp.admin")) {
				p.sendMessage(ChatColor.RED + "Couldn't locate the motd file.");
			}
		} catch (final IOException err) {
			if (p.hasPermission("worldsmp.admin")) {
				p.sendMessage(ChatColor.RED + "Error reading the motd file.");
			}
		}
	}
}
