package com.theworldsmp.server;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Survival extends JavaPlugin {

	@Override
	public void onEnable() {
		loadRecipes();
	}

	@Override
	public void onDisable() {
	}

	public void createShapelessRecipe(Material outputItem, int outputAmount, String key, Material inputItem, int inputAmount) {
		final ItemStack recipeStack = new ItemStack(outputItem, outputAmount);
		final NamespacedKey recipeKey = new NamespacedKey(this, key);
		final ShapelessRecipe itemRecipe = new ShapelessRecipe(recipeKey, recipeStack);

		itemRecipe.addIngredient(inputAmount, inputItem);
		Bukkit.addRecipe(itemRecipe);
	}

	private void loadRecipes() {
		createShapelessRecipe(Material.QUARTZ_BLOCK, 4, "quartz_key", Material.QUARTZ, 4);
		createShapelessRecipe(Material.BRICKS, 4, "bricks_key", Material.BRICK, 4);
	}
}
