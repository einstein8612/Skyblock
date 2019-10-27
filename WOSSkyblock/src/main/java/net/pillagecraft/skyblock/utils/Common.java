package net.pillagecraft.skyblock.utils;

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

	public void sendActionBar(Player player, String message) {
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
	}

}
