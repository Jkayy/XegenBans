package com.jkaay.xegenbans.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import com.jkaay.xegenbans.commands.XegenCommands;

public class PlayerLogin implements Listener {
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerLogin(PlayerLoginEvent e) {
		if (e.getResult() == Result.KICK_BANNED) {
            e.setKickMessage(ChatColor.RED + "You have been permanently banned for: " + ChatColor.translateAlternateColorCodes('&', XegenCommands.banReason));
		}
	}
}
