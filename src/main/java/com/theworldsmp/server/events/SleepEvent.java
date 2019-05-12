package com.theworldsmp.server.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.theworldsmp.server.Survival;
import com.theworldsmp.server.utils.IsDay;

public class SleepEvent implements Listener {

	private final Survival plugin;

	public SleepEvent(Survival plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onSleep(PlayerBedEnterEvent event) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (IsDay.Day() == false) {
					for(final Player p : Bukkit.getOnlinePlayers()) {
						if (p.isSleeping()) {
							Bukkit.broadcastMessage(ChatColor.AQUA + "Someone is sleeping, skipping night...");
							final World world = event.getPlayer().getWorld();
							world.setTime(1000L);
						}
					}

				}
			}

		}.runTaskLater(plugin, 5);
	}
}
