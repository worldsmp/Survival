package com.theworldsmp.survival;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import com.theworldsmp.survival.commands.HeadCommand;
import com.theworldsmp.survival.commands.MessageCommand;
import com.theworldsmp.survival.commands.ReplyCommand;
import com.theworldsmp.survival.commands.SeenCommand;
import com.theworldsmp.survival.events.MoveEvent;
import com.theworldsmp.survival.events.QuitEvent;
import com.theworldsmp.survival.events.SleepEvent;
import com.theworldsmp.survival.utils.ConfigHandler;
import com.theworldsmp.survival.utils.MessageHandler;

public class Survival extends JavaPlugin {

	public static Survival instance;
	private MessageHandler handler;
	ConfigHandler conf = ConfigHandler.getInstance();
	FileConfiguration config;
	File cfile;

	@Override
	public void onEnable() {
		instance = this;
		handler = new MessageHandler(this);

		this.config = getConfig();
		this.config.options().copyDefaults(true);
		this.conf.setup(this);

		Bukkit.getPluginManager().registerEvents(new SleepEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new MoveEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);

		getCommand("message").setExecutor(new MessageCommand(this));
		getCommand("reply").setExecutor(new ReplyCommand(this));
		getCommand("seen").setExecutor(new SeenCommand());
		getCommand("head").setExecutor(new HeadCommand());
		loadRecipes();
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	public void createShapelessRecipe(Material outputItem, int outputAmount, String key, Material inputItem, int inputAmount) {
		final ItemStack recipeStack = new ItemStack(outputItem, outputAmount);
		final NamespacedKey recipeKey = new NamespacedKey(this, key);
		final ShapelessRecipe itemRecipe = new ShapelessRecipe(recipeKey, recipeStack);

		itemRecipe.addIngredient(inputAmount, inputItem);
		Bukkit.addRecipe(itemRecipe);

	}

	private void loadRecipes() {
		//createShapelessRecipe(Material.QUARTZ_BLOCK, 4, "quartz_recipe_key", Material.QUARTZ, 4);
		//createShapelessRecipe(Material.BRICKS, 4, "bricks_recipe_key", Material.BRICK, 4);
	}

	public MessageHandler getMessageHandler() {return handler; }
}
