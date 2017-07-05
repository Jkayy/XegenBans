package com.jkaay.xegenbans.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.jkaay.xegenbans.Main;
import com.jkaay.xegenbans.commands.XegenCommands;

public class PlayerChat implements Listener {

	public static List<String> hasrun = new ArrayList<String>();
	public static List<String> reported = InventoryClick.isreported;
	private List<String> reason = XegenCommands.reportedreason;
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		try {
			String player = InventoryClick.clicked.getName();
			if (hasrun.contains(player)) {
			} else {
				if (reported.contains(player)) {
					hasrun.remove(player);
					return;
				} else {
					if (!(e.getMessage().contains("http://") || e.getMessage().contains("https://"))) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "Please use a valid URL.");
					} else {
						e.setCancelled(true);
						InventoryClick.clicked.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("reported_message").replaceAll("%player%", InventoryClick.p.getName()).replaceAll("%reason%", "Chat violation")));
						hasrun.add(player);
						reason.add("Chat violation (Spam/Language)");
						return;
					}
				}
			}
			return;
		}
		catch (Exception ex) {
			return;
		}
	}
}