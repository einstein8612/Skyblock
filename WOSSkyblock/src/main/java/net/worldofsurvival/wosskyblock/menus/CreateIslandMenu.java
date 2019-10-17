package net.worldofsurvival.wosskyblock.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;
import net.worldofsurvival.wosskyblock.items.MainItems;

public final class CreateIslandMenu {

	private MainItems mainItems;

	public CreateIslandMenu(MainItems mainItems) {
		this.mainItems = mainItems;
	}

	public Inventory createIsland() {
		Inventory inv = Bukkit.createInventory(null, 27,
				ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lSkyblock Creation"));

		for (int i = 0; i < 27; i++) {
			inv.setItem(i, mainItems.blackPane());
		}

		inv.setItem(11, mainItems.makeClickMeItem(Material.GRASS_BLOCK, "&5&lClassic Island"));
		inv.setItem(13, mainItems.makeClickMeItem(Material.NETHER_STAR, "&5&lCustom Island"));
		inv.setItem(15, mainItems.makeClickMeItem(Material.DIAMOND_PICKAXE, "&5&l*Placeholder* Island"));

		return inv;

	}
}
