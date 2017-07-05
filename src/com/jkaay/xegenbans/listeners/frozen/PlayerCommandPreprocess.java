package com.jkaay.xegenbans.listeners.frozen;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.jkaay.xegenbans.commands.XegenCommands;

import net.md_5.bungee.api.ChatColor;

public class PlayerCommandPreprocess implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void preCommandProcess(PlayerCommandPreprocessEvent e) {
		List<String> frozen = XegenCommands.frozen;
		if (frozen.contains(e.getPlayer().getName())) {
			e.getPlayer().sendMessage(ChatColor.RED + "You are currently frozen and can\'t type commands");
	        e.setCancelled(true);
		} else {
			return;
		}
	}
}
