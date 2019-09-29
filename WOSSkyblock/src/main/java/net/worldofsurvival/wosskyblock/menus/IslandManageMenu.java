package net.worldofsurvival.wosskyblock.menus;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;
import net.worldofsurvival.wosskyblock.items.MainItems;

public final class IslandManageMenu {
	
	private MainItems items;
	public IslandManageMenu(MainItems items) {
		this.items = items;
	}
	
	public Inventory main() {
		
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lSkyblock Menu"));
		
		for (int i=0; i<54; i++) {
			inv.setItem(i, items.blackPane());
		}
		
		inv.setItem(40, items.home());
		inv.setItem(53, items.back());
		return inv;
		
	}
	
}
