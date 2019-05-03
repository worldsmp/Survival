package com.theworldsmp.server;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.theworldsmp.utils.IsDay;

public class Survival extends JavaPlugin {

	public static Survival instance;

	@Override
	public void onEnable() {
		instance = this;
		new SleepEvent(this);
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
		createShapelessRecipe(Material.QUARTZ_BLOCK, 4, "quartz_key", Material.QUARTZ, 4);
		createShapelessRecipe(Material.BRICKS, 4, "bricks_key", Material.BRICK, 4);
	}

	class SleepEvent implements Listener {

		private int inBed;
		private final Survival plugin;

		public SleepEvent(Survival plugin) {
			this.plugin = plugin;
			this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
		}

		@EventHandler
		public void onSleep(PlayerBedEnterEvent event) {
			inBed = 0;
			new BukkitRunnable() {
				@Override
				public void run() {
					if (IsDay.Day() == false) {
						for(final Player p : Bukkit.getOnlinePlayers()) {
							if (p.isSleeping()) {
								inBed ++;
							}
						}
						if (inBed >= Bukkit.getOnlinePlayers().size() / 2) {
							for (final Player p : Bukkit.getOnlinePlayers()) {
								if (p.getWorld() == event.getPlayer().getWorld()) {
									p.sendMessage(ChatColor.AQUA + "Enough players are sleeping, skipping night...");
								}
							}
							final World world = event.getPlayer().getWorld();
							world.setTime(1000L);
						} else {
							for (final Player p : Bukkit.getOnlinePlayers()) {
								if (p.getWorld() == event.getPlayer().getWorld()) {
									p.sendMessage(ChatColor.AQUA + "Someone is sleeping (" + inBed + "/" + Bukkit.getOnlinePlayers().size() / 2 + ")");
								}
							}
						}
					}

				}
			}.runTaskLater(plugin, 5);
		}
	}
}
