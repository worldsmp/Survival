package com.theworldsmp.survival.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.theworldsmp.survival.Survival;

public class MoveEvent implements Listener {

	@SuppressWarnings("unused")
	private final Survival plugin;

	public MoveEvent(Survival plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onCropWalk(PlayerMoveEvent e) {

		final Player p = e.getPlayer();
		final Location loc = p.getLocation().clone().add(0, 1, 0);
		final Block block = loc.getBlock();
		final Material crop = block.getType();
		final BlockData bdata = block.getBlockData();

		if(bdata instanceof Ageable && p.getEquipment().getItemInMainHand().getType().equals(Material.DIAMOND_HOE) && p.getGameMode() == GameMode.SURVIVAL){
			final Ageable age = (Ageable) bdata;
			if(age.getAge() == age.getMaximumAge()){
				block.breakNaturally();
				block.setType(crop);
			}

		}

	}
}
