package com.theworldsmp.server.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.theworldsmp.server.Survival;

public class MessageHandler {

	@SuppressWarnings("unused")
	private final Survival survival;

	public MessageHandler(Survival survival) {
		this.survival = survival;
	}

	public HashMap<Player, Player> lastMessage = new HashMap<>();
}
