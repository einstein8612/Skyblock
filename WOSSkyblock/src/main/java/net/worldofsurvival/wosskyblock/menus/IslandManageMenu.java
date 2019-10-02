package net.worldofsurvival.wosskyblock.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;
import net.worldofsurvival.wosskyblock.items.MainItems;

public final class IslandManageMenu {

	private MainItems mainItems;
	public IslandManageMenu(MainItems mainItems) {
		this.mainItems = mainItems;
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
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lWarp Menu"));

		for (int i=0; i<27; i++) {
			inv.setItem(i, mainItems.blackPane());
		}
		
		inv.setItem(11, mainItems.makeClickMeItem(Material.DIAMOND_AXE, "&c&lWood Farms"));
		inv.setItem(13, mainItems.makeClickMeItem(Material.DIAMOND_PICKAXE, "&5&lMines"));
		
		inv.setItem(26, mainItems.back());
		
		return inv;
	}

	public Inventory woodWarps() {
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lWood Farms"));

		for (int i=0; i<45; i++) {
			inv.setItem(i, mainItems.blackPane());
		}
		//TOP ROW
		inv.setItem(11, mainItems.makeTeleportItem(Material.OAK_SAPLING, "&a&lOak Wood"));
		inv.setItem(13, mainItems.makeTeleportItem(Material.BIRCH_SAPLING, "&e&lBirch Wood"));
		inv.setItem(15, mainItems.makeTeleportItem(Material.JUNGLE_SAPLING, "&9&lJungle Wood"));
		
		//BOTTOM ROW
		inv.setItem(29, mainItems.makeTeleportItem(Material.ACACIA_SAPLING, "&c&lAcacia Wood"));
		inv.setItem(31, mainItems.makeTeleportItem(Material.SPRUCE_SAPLING, "&d&lSpruce Wood"));
		inv.setItem(33, mainItems.makeTeleportItem(Material.DARK_OAK_SAPLING, "&6&lDark Oak Wood"));
		
		inv.setItem(44, mainItems.back());
		
		return inv;
	}
	
	public Inventory mineWarps() {
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lMines"));

		for (int i=0; i<45; i++) {
			inv.setItem(i, mainItems.blackPane());
		}
		//TOP ROW
		inv.setItem(10, mainItems.makeTeleportItem(Material.COAL_ORE, "&8&lCoal Mine"));
		inv.setItem(12, mainItems.makeTeleportItem(Material.LAPIS_ORE, "&9&lLapis Mine"));
		inv.setItem(14, mainItems.makeTeleportItem(Material.REDSTONE_ORE, "&c&lRedstone Mine"));
		inv.setItem(16, mainItems.makeTeleportItem(Material.IRON_ORE, "&7&lIron Mine"));
		
		//BOTTOM ROW
		inv.setItem(29, mainItems.makeTeleportItem(Material.GOLD_ORE, "&6&lGold Mine"));
		inv.setItem(31, mainItems.makeTeleportItem(Material.DIAMOND_ORE, "&b&lDiamond Mine"));
		inv.setItem(33, mainItems.makeTeleportItem(Material.EMERALD_ORE, "&a&lEmerald Mine"));
		
		inv.setItem(44, mainItems.back());
		
		return inv;
	}
}