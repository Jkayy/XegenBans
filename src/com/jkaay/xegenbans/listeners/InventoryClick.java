package com.jkaay.xegenbans.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.jkaay.xegenbans.Main;
import com.jkaay.xegenbans.commands.XegenCommands;

public class InventoryClick implements Listener {
	
	public static List<String> isreported = new ArrayList<String>();
	public static Player clicked;
	public static Player p;
	public static int slot;
	private List<String> reason = XegenCommands.reportedreason;
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onInventoryClick(InventoryClickEvent e) {
		slot = e.getSlot();
		if (e.getInventory().getName() == XegenCommands.inv.getName()) {
			 p = Bukkit.getServer().getPlayer(XegenCommands.player);
			 e.setCancelled(true);
			 clicked = Bukkit.getServer().getPlayer(e.getWhoClicked().getName());
			 if (e.getSlot() == 1) {
				 clicked.closeInventory();
				 try {
					 clicked.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("reported_message").replaceAll("%player%", p.getName()).replaceAll("%reason%", "Kill Aura")));
					 reason.add("Kill Aura");
				 }
				 catch (Exception ex) {
					 clicked.sendMessage(ChatColor.RED + XegenCommands.player + " appears to be offline or doesn\'t exist!");
				 }
			 } else if (e.getSlot() == 2) {
				 clicked.closeInventory();
				 try {
					 clicked.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("reported_message").replaceAll("%player%", p.getName()).replaceAll("%reason%", "Jesus Hacks")));
					 reason.add("Jesus Hacks");
				 }
				 catch (Exception ex) {
					 clicked.sendMessage(ChatColor.RED + XegenCommands.player + " appears to be offline or doesn\'t exist!");
				 }
			 } else if (e.getSlot() == 3) {
				 clicked.closeInventory();
				 try {
					 clicked.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("reported_message").replaceAll("%player%", p.getName()).replaceAll("%reason%", "AutoClicker")));
					 reason.add("Autoclicker");
				 }
				 catch (Exception ex) {
					 clicked.sendMessage(ChatColor.RED + XegenCommands.player + " appears to be offline or doesn\'t exist!");
				 }
			 } else if (e.getSlot() == 4) {
				 clicked.closeInventory();
				 try {
					 clicked.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("reported_message").replaceAll("%player%", p.getName()).replaceAll("%reason%", "Speed Hacks")));
					 reason.add("Speed Hacking"); 
				 }
				 catch (Exception ex) {
					 clicked.sendMessage(ChatColor.RED + XegenCommands.player + " appears to be offline or doesn\'t exist!");
				 }
			 } else if (e.getSlot() == 5) {
				 clicked.closeInventory();
				 try {
					 clicked.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("reported_message").replaceAll("%player%", p.getName()).replaceAll("%reason%", "Fly Hacks")));
					 reason.add("Fly Hacking"); 
				 }
				 catch (Exception ex) {
					 clicked.sendMessage(ChatColor.RED + XegenCommands.player + " appears to be offline or doesn\'t exist!");
				 }
			 } else if (e.getSlot() == 6) {
				 clicked.closeInventory();
				 try {
					 if (PlayerChat.hasrun.contains(clicked.getName())) {
						 clicked.sendMessage(ChatColor.AQUA + "Please post a screenshot link of " + p.getName() + " spamming in chat.\n" + ChatColor.DARK_AQUA + "(Your message will not be public)");
						 PlayerChat.hasrun.remove(clicked.getName());
					 } else {
						 clicked.sendMessage(ChatColor.AQUA + "Please post a screenshot link of " + p.getName() + " spamming in chat.\n" + ChatColor.DARK_AQUA + "(Your message will not be public)");
						 return;
					 }
				 }
				 catch (Exception ex) {
					 clicked.sendMessage(ChatColor.RED + XegenCommands.player + " appears to be offline or doesn\'t exist!");
				 }
			 } else if (e.getSlot() == 7) {
				 clicked.closeInventory();
				 try {
					 if (PlayerChat.hasrun.contains(clicked.getName())) {
						 clicked.sendMessage(ChatColor.AQUA + "Please post a screenshot link of " + p.getName() + " using excessive language.\n" + ChatColor.DARK_AQUA + "(Your message will not be public)");
						 PlayerChat.hasrun.remove(clicked.getName());
					 } else {
						 clicked.sendMessage(ChatColor.AQUA + "Please post a screenshot link of " + p.getName() + " using excessive language.\n" + ChatColor.DARK_AQUA + "(Your message will not be public)");
						 return;
					 }
				 }
				 catch (Exception ex) {
					 clicked.sendMessage(ChatColor.RED + XegenCommands.player + " appears to be offline or doesn\'t exist!");
				 }
			 }
		}
	}
}
