package com.jkaay.xegenbans.listeners.frozen;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.jkaay.xegenbans.commands.XegenCommands;

public class PlayerMove implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerMove(PlayerMoveEvent e) {
		List<String> frozen = XegenCommands.frozen;
		Location loc = e.getPlayer().getLocation();
		if (frozen.contains(e.getPlayer().getName())) {
			e.getPlayer().teleport(loc);
		}
	}
}
