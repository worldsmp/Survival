package com.theworldsmp.survival.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigHandler {

	static ConfigHandler instance = new ConfigHandler();
	public static ConfigHandler getInstance() { return instance; }

	FileConfiguration data;
	File dfile;

	public void setup(Plugin p) {

		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}

		this.dfile = new File(p.getDataFolder(), "login-data.yml");

		if (!this.dfile.exists()) {
			try {
				this.dfile.createNewFile();
			}
			catch (final IOException e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create login-data.yml!");
			}
		}

		this.data = YamlConfiguration.loadConfiguration(this.dfile);
	}

	public FileConfiguration getData() { return this.data; }

	public void saveData() {
		try {
			this.data.save(this.dfile);
		}
		catch (final IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save login-data.yml!");
		}
	}

	public void reloadData() { this.data = YamlConfiguration.loadConfiguration(this.dfile); }

}

