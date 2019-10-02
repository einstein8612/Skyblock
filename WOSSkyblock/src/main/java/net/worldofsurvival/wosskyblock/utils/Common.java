package net.worldofsurvival.wosskyblock.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

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
		Bukkit.getConsoleSender().sendMessage(this.colorize("[WOSSkyblock] " + message));
	}
	
	public void logError(String message) {
		Bukkit.getConsoleSender().sendMessage(this.colorize("&4[&cWOSSkyblock&4] &c" + message));
	}
	
	public void sendActionBar(Player player, String message) {
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
	}
	
}
