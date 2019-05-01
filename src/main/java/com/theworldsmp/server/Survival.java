package com.theworldsmp.server;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Survival extends JavaPlugin {

	@Override
	public void onEnable() {
		loadRecipe();
	}

	@Override
	public void onDisable() {
	}

	private void loadRecipe() {
		final ItemStack item  = new ItemStack(Material.QUARTZ_BLOCK, 4);
		final NamespacedKey quartzkey = new NamespacedKey(this, "quartz_key");
		final ShapedRecipe quartzrecipe = new ShapedRecipe(quartzkey, item);

		quartzrecipe.shape("qq ","qq ","   ");
		quartzrecipe.setIngredient('q', Material.QUARTZ);

		Bukkit.addRecipe(quartzrecipe);
	}
}
