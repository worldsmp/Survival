package com.theworldsmp.survival.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigHandler {

	static ConfigHandler instance = new ConfigHandler();
	public static ConfigHandler getInstance() { return instance; }

	YamlConfiguration modifyData;
	File dfile;

	public void setup(Plugin p) throws IOException {

		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}

		dfile = new File(p.getDataFolder(), "login-data.yml");


		if (!dfile.exists()) {
			dfile.createNewFile();
		}
		modifyData = YamlConfiguration.loadConfiguration(dfile);


	}
	public YamlConfiguration getData() { return modifyData; }

	public void saveData() throws IOException { modifyData.save(this.dfile); }
	public void reloadData() { modifyData = YamlConfiguration.loadConfiguration(this.dfile); }

}

