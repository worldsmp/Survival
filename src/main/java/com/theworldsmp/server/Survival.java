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

	private void loadRecipes() {
		final ItemStack quartzItem  = new ItemStack(Material.QUARTZ_BLOCK, 4);
		final NamespacedKey quartzKey = new NamespacedKey(this, "quartz_key");
		final ShapelessRecipe quartzRecipe = new ShapelessRecipe(quartzKey, quartzItem);
		quartzRecipe.addIngredient(4, Material.QUARTZ);


		final ItemStack brickItem  = new ItemStack(Material.BRICKS, 4);
		final NamespacedKey brickKey = new NamespacedKey(this, "brick_key");
		final ShapelessRecipe brickRecipe = new ShapelessRecipe(brickKey, brickItem);
		brickRecipe.addIngredient(4, Material.BRICK);

		Bukkit.addRecipe(quartzRecipe);
		Bukkit.addRecipe(brickRecipe);
	}
}
