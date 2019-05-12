package com.theworldsmp.server;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import com.theworldsmp.server.commands.MessageCommand;
import com.theworldsmp.server.commands.ReplyCommand;
import com.theworldsmp.server.events.SleepEvent;
import com.theworldsmp.server.utils.MessageHandler;

public class Survival extends JavaPlugin {

	public static Survival instance;
	private MessageHandler handler;

	@Override
	public void onEnable() {
		instance = this;
		handler = new MessageHandler(this);

		Bukkit.getPluginManager().registerEvents(new SleepEvent(this), this);

		getCommand("message").setExecutor(new MessageCommand(this));
		getCommand("reply").setExecutor(new ReplyCommand(this));
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
		createShapelessRecipe(Material.QUARTZ_BLOCK, 4, "quartzkey", Material.QUARTZ, 4);
		createShapelessRecipe(Material.BRICKS, 4, "brickskey", Material.BRICK, 4);
	}

	public MessageHandler getMessageHandler() {return handler; }
}
