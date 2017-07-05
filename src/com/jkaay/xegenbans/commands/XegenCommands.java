package com.jkaay.xegenbans.commands;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class XegenCommands implements CommandExecutor {

	public static Inventory inv;
	public static String player;
	public static String banReason;
	public static String kickReason;
	public static Player mutep;

	public static List<String> staffmode = new ArrayList<String>();
	public static List<String> frozen = new ArrayList<String>();
	public static List<String> reportedplayer = new ArrayList<String>();
	public static List<String> reportedreason = new ArrayList<String>();
	public static List<String> muted = new ArrayList<String>();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("report")) {
			if (sender instanceof Player) {
				if (args.length == 1) {
					player = args[0];
					openGui((Player) sender);
					reportedplayer.add(player);
				} else if (args.length != 1 || args.length == 0) {
					try {
						sender.sendMessage(ChatColor.RED + "Invalid syntax. Use /report <player>");
					}
					catch (Exception ex) {
						sender.sendMessage(ChatColor.RED + "Invalid syntax. Use /report <player>");
					}
				}
			} else {
				sender.sendMessage("This command cannot be executed from console!");
			}
		} else if (commandLabel.equalsIgnoreCase("ban")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Invalid syntax. Use /ban <player> <reason>");
			} else {
				if (args.length == 1) {
					try {
						banReason = "You have been permanently banned.";
						Bukkit.getServer().getPlayer(args[0]).kickPlayer(ChatColor.RED + "You have been permanently banned for: " + ChatColor.translateAlternateColorCodes('&', banReason));
						Bukkit.getServer().getPlayer(args[0]).setBanned(true);
						sender.sendMessage(ChatColor.AQUA + "Banned " + args[0] + " for: " + ChatColor.DARK_AQUA + "\"" + ChatColor.translateAlternateColorCodes('&', banReason) + "\"");
					}
					catch (Exception ex) {
						banReason = "You have been permanently banned.";
						Bukkit.getServer().getOfflinePlayer(args[0]).setBanned(true);
						sender.sendMessage(ChatColor.AQUA + "Banned " + args[0] + " for: " + ChatColor.DARK_AQUA + "\"" + ChatColor.translateAlternateColorCodes('&', banReason) + "\"");
					}
				} else {
					try {
						banReason = args.length > 0 ? StringUtils.join(args, ' ', 1, args.length) : null;
						Bukkit.getServer().getPlayer(args[0]).kickPlayer(ChatColor.RED + "You have been permanently banned for: " + ChatColor.translateAlternateColorCodes('&', banReason));
						Bukkit.getServer().getPlayer(args[0]).setBanned(true);
						sender.sendMessage(ChatColor.AQUA + "Banned " + args[0] + " for: " + ChatColor.DARK_AQUA + "\"" + ChatColor.translateAlternateColorCodes('&', banReason) + "\"");
					}
					catch (Exception ex) {
						banReason = args.length > 0 ? StringUtils.join(args, ' ', 1, args.length) : null;
						Bukkit.getServer().getOfflinePlayer(args[0]).setBanned(true);
						sender.sendMessage(ChatColor.AQUA + "Banned " + args[0] + " for: " + ChatColor.DARK_AQUA + "\"" + ChatColor.translateAlternateColorCodes('&', banReason) + "\"");
					}
				}
			}
		} else if (commandLabel.equalsIgnoreCase("unban")) {
			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED + "Invalid syntax. Use /unban <player>");
			} else {
				try {
					if (Bukkit.getServer().getOfflinePlayer(args[0]).isBanned()) {
						Bukkit.getServer().getOfflinePlayer(args[0]).setBanned(false);
						sender.sendMessage(ChatColor.AQUA + "Successfully unbanned " + args[0]);
					} else {
						sender.sendMessage(ChatColor.RED + "Error: " + args[0] + " is either not banned or doesn\'t exist");
					}
				}
				catch (Exception ex) {
					sender.sendMessage(ChatColor.RED + "Error: " + args[0] + " does not exist");
					return true;
				}
			}
		} else if (commandLabel.equalsIgnoreCase("kick")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Invalid syntax. Use /kick <player> [reason]");
			} else if (args.length == 1) {
				try {
					Bukkit.getServer().getPlayer(args[0]).kickPlayer(ChatColor.RED + "You have been kicked.");
					sender.sendMessage(ChatColor.AQUA + "Kicked " + args[0]);
				}
				catch (Exception ex) {
					sender.sendMessage(ChatColor.RED + "Error: " + args[0] + " appears to be offline or doesn\'t exist");
					return true;
				}
			} else {
				try {
					kickReason = args.length > 0 ? StringUtils.join(args, ' ', 1, args.length) : null;
					Bukkit.getServer().getPlayer(args[0]).kickPlayer(ChatColor.RED + "You have been kicked by " + sender.getName() + " for: " + ChatColor.translateAlternateColorCodes('&', kickReason));
					sender.sendMessage(ChatColor.AQUA + "Kicked " + args[0] + " for: " + ChatColor.DARK_AQUA + "\"" + ChatColor.translateAlternateColorCodes('&', banReason) + "\"");
				}
				catch (Exception ex) {
					sender.sendMessage(ChatColor.RED + "Error: " + args[0] + " appears to be offline or doesn\'t exist");
					return true;
				}
			}
		} else if (commandLabel.equalsIgnoreCase("staff")) {
			Player p = (Player) sender;
			if (staffmode.contains(sender.getName())) {
				staffmode.remove(sender.getName());
				for (Player all : Bukkit.getServer().getOnlinePlayers()) {
					all.showPlayer(p);
				}
				sender.sendMessage(ChatColor.GREEN + "Taken out of staff mode");
			} else {
				staffmode.add(sender.getName());
				for (Player all : Bukkit.getServer().getOnlinePlayers()) {
					all.hidePlayer(p);
				}
				sender.sendMessage(ChatColor.GREEN + "You are now in staff mode and vanished from tablist and all in-game commands. " + ChatColor.YELLOW + "Type /freeze <player> to freeze a player");
			}
		} else if (commandLabel.equalsIgnoreCase("freeze")) {
			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED + "Invalid syntax. Use /freeze <player>");
			} else {
				String player = Bukkit.getServer().getPlayer(args[0]).getName();
				if (frozen.contains(player)) {
					sender.sendMessage(ChatColor.RED + "Error: " + player + " is already frozen.");
				} else {
					try {
						frozen.add(args[0]);
						Bukkit.getServer().getPlayer(args[0]).sendMessage(ChatColor.RED + "You have been frozen by " + sender.getName());
						sender.sendMessage(ChatColor.YELLOW + args[0] + " is now frozen. " + ChatColor.ITALIC + "Type /unfreeze " + args[0] + " to unfreeze them");
					}
					catch (Exception ex) {
						sender.sendMessage(ChatColor.RED + "Error: " + args[0] + " is either offline or doesn\'t exist.");
						return true;
					}
				}
			}
		} else if (commandLabel.equalsIgnoreCase("unfreeze")) {

			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED + "Invalid syntax. Use /unfreeze <player>");
			} else {
				try {
					String player = Bukkit.getServer().getPlayer(args[0]).getName();
					if (frozen.contains(player)) {
						frozen.remove(player);
						sender.sendMessage(ChatColor.AQUA + player + ChatColor.DARK_AQUA + " has been unfrozen.");
					} else {
						sender.sendMessage(ChatColor.RED + "Error: " + player + " not currently frozen!");
					}
				}
				catch (Exception ex) {
					sender.sendMessage(ChatColor.RED + "Error: " + args[0] + " is either offline or doesn\'t exist.");
					return true;
				}
			}
		} else if (commandLabel.equalsIgnoreCase("reports")) {
			sender.sendMessage(ChatColor.DARK_AQUA + "XegenReports List:\n");
			for (String reports : reportedplayer) {
				sender.sendMessage(ChatColor.AQUA + reports + ChatColor.GRAY + " - Reported for: " + ChatColor.RED + reportedreason);
			}
		} else if (commandLabel.equalsIgnoreCase("banlist")) {
			sender.sendMessage(ChatColor.DARK_AQUA + "XegenReports Banned Players list:\n");
			for (OfflinePlayer p : Bukkit.getBannedPlayers()) {
				sender.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " - " + ChatColor.AQUA + "Reason: " + ChatColor.ITALIC + "\""
						+ "\"");
			}
		}
		return true;
	}

	private void openGui(Player player) {
		inv = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Report a player:");
		ItemStack killaura = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta meta = killaura.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "Kill Aura");
		meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		killaura.setItemMeta(meta);

		ItemStack jesus = new ItemStack(Material.WATER_BUCKET);
		ItemMeta meta1 = jesus.getItemMeta();
		meta1.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "Jesus Hacks");
		meta1.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		jesus.setItemMeta(meta1);
		
		ItemStack autoclick = new ItemStack(Material.COMPASS);
		ItemMeta meta2 = autoclick.getItemMeta();
		meta2.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "AutoClicker");
		meta2.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meta2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		autoclick.setItemMeta(meta2);
		
		ItemStack speed = new ItemStack(Material.POTION);
		ItemMeta meta3 = speed.getItemMeta();
		meta3.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "Speed Hacks");
		meta3.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meta3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		speed.setItemMeta(meta3);
		
		ItemStack fly = new ItemStack(Material.FEATHER);
		ItemMeta meta4 = fly.getItemMeta();
		meta4.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "Fly Hacks");
		meta4.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meta4.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fly.setItemMeta(meta4);
		
		ItemStack spam = new ItemStack(Material.PAPER);
		ItemMeta meta5 = spam.getItemMeta();
		meta5.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "Spamming");
		meta5.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meta5.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		spam.setItemMeta(meta5);
		
		ItemStack language = new ItemStack(Material.BARRIER);
		ItemMeta meta6 = language.getItemMeta();
		meta6.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "Excessive Language");
		meta6.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meta6.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		language.setItemMeta(meta6);

	    inv.setItem(1, killaura);
	    inv.setItem(2, jesus);
	    inv.setItem(3, autoclick);
	    inv.setItem(4, speed);
	    inv.setItem(5, fly);
	    inv.setItem(6, spam);
	    inv.setItem(7, language);
	    player.openInventory(inv);
	}
}
