package net.worldofsurvival.wosskyblock.menus;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;
import net.worldofsurvival.wosskyblock.items.MainItems;
import net.worldofsurvival.wosskyblock.items.WarpItems;

public final class IslandManageMenu {

	private WarpItems warpItems;
	private MainItems mainItems;
	public IslandManageMenu(MainItems mainItems, WarpItems warpItems) {
		this.mainItems = mainItems;
		this.warpItems = warpItems;
	}

	public Inventory main() {

		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lSkyblock Menu"));

		for (int i=0; i<54; i++) {
			inv.setItem(i, mainItems.blackPane());
		}

		inv.setItem(13, mainItems.warps());
		inv.setItem(40, mainItems.home());
		return inv;
	}

	public Inventory warps() {
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lSkyblock Menu"));

		for (int i=0; i<27; i++) {
			inv.setItem(i, mainItems.blackPane());
		}
		
		inv.setItem(11, warpItems.woodFarms());
		
		return inv;
	}

	public Inventory woodWarps() {
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lSkyblock Menu"));

		for (int i=0; i<45; i++) {
			inv.setItem(i, mainItems.blackPane());
		}
		//TOP ROW
		inv.setItem(11, warpItems.oakFarm());
		inv.setItem(13, warpItems.birchFarm());
		inv.setItem(15, warpItems.jungleFarm());
		
		//BOTTOM ROW
		inv.setItem(29, warpItems.acaciaFarm());
		inv.setItem(31, warpItems.spruceFarm());
		inv.setItem(33, warpItems.darkOakFarm());
		
		
		return inv;
	}
}