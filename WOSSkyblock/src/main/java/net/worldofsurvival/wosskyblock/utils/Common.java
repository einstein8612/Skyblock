package net.worldofsurvival.wosskyblock.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public final class Common {

	public String getPrefix = this.colorize("&5&lWOS &8> &r");
	
	public String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public String decolor(String message) {
		return ChatColor.stripColor(message);
	}
	
	public void tell(CommandSender sender, String message) {
		sender.sendMessage(this.colorize(message));
	}
	
	public void log(String message) {
		Bukkit.getConsoleSender().sendMessage("[WOSSkyblock] " + this.colorize(message));
	}
	
}
