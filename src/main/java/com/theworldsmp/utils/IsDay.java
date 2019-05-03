package com.theworldsmp.utils;

import org.bukkit.Bukkit;

public class IsDay {

	public static boolean Day() {
		final long time = Bukkit.getWorld("world").getTime();

		if(time > 0 && time < 12516) {
			return true;
		} else {
			return false;
		}
	}
}
