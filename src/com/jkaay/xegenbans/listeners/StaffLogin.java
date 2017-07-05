package com.jkaay.xegenbans.listeners;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.jkaay.xegenbans.commands.XegenCommands;

public class StaffLogin implements Listener {
	
	private List<String> staffmode = XegenCommands.staffmode;
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void staffModeCheck(PlayerLoginEvent e) {
		if (staffmode.contains(e.getPlayer().getName())) {
			staffmode.remove(e.getPlayer().getName());
		}
	}
}
