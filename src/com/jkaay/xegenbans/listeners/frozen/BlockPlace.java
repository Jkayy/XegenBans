package com.jkaay.xegenbans.listeners.frozen;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.jkaay.xegenbans.commands.XegenCommands;

public class BlockPlace implements Listener {
	@EventHandler(priority = EventPriority.MONITOR)
	
	public void onBlockBreak(BlockPlaceEvent e) {
		List<String> frozen = XegenCommands.frozen;
		if (frozen.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "You are currently frozen and can\'t break or place blocks");
		}
	}
}
