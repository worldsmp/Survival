package com.theworldsmp.survival.events;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.theworldsmp.survival.utils.ConfigHandler;

public class QuitEvent implements Listener {
	ConfigHandler conf = ConfigHandler.getInstance();

	@EventHandler
	public void onLeave(PlayerQuitEvent e) throws IOException {
		final String p = e.getPlayer().getUniqueId().toString();

		final Date now = new Date();
		final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		final String date = format.format(now);

		if (this.conf.getData().getString(p) == null) {
			this.conf.getData().createSection(p);
			this.conf.getData().set(p, date);
			this.conf.saveData();

		} else {

			this.conf.getData().set(p, date);
			this.conf.saveData();
		}
	}
}
