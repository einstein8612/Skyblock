package net.worldofsurvival.wosskyblock.menus;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.worldofsurvival.wosskyblock.items.MainItems;
import net.worldofsurvival.wosskyblock.utils.IslandMethods;

public final class IslandManageMenu {

	private MainItems mainItems;

	public IslandManageMenu(MainItems mainItems) {
		this.mainItems = mainItems;
	}

	public Inventory main() {

		Inventory inv = Bukkit.createInventory(null, 54,
				ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lSkyblock Menu"));

		for (int i = 0; i < 54; i++) {
			inv.setItem(i, mainItems.blackPane());
		}

		inv.setItem(13, mainItems.warps());
		inv.setItem(40, mainItems.home());
		inv.setItem(20, mainItems.makeClickMeItem(Material.SKELETON_SKULL, "&5&lInvite Players"));
		inv.setItem(29, mainItems.makeClickMeItem(Material.WITHER_SKELETON_SKULL, "&5&lPlayers"));

		inv.setItem(49, mainItems.makeClickMeItem(Material.BARRIER, "&c&lClose"));
		return inv;
	}

	public Inventory warps() {
		Inventory inv = Bukkit.createInventory(null, 27,
				ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lWarp Menu"));

		for (int i = 0; i < 27; i++) {
			inv.setItem(i, mainItems.blackPane());
		}

		inv.setItem(11, mainItems.makeClickMeItem(Material.DIAMOND_AXE, "&c&lWood Farms"));
		inv.setItem(13, mainItems.makeClickMeItem(Material.DIAMOND_PICKAXE, "&5&lMines"));

		inv.setItem(26, mainItems.back());

		return inv;
	}

	public Inventory woodWarps() {
		Inventory inv = Bukkit.createInventory(null, 45,
				ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lWoodlands"));

		for (int i = 0; i < 45; i++) {
			inv.setItem(i, mainItems.blackPane());
		}
		// TOP ROW
		inv.setItem(11, mainItems.makeTeleportItem(Material.OAK_SAPLING, "&a&lOak Wood"));
		inv.setItem(13, mainItems.makeTeleportItem(Material.BIRCH_SAPLING, "&e&lBirch Wood"));
		inv.setItem(15, mainItems.makeTeleportItem(Material.JUNGLE_SAPLING, "&9&lJungle Wood"));

		// BOTTOM ROW
		inv.setItem(29, mainItems.makeTeleportItem(Material.ACACIA_SAPLING, "&c&lAcacia Wood"));
		inv.setItem(31, mainItems.makeTeleportItem(Material.SPRUCE_SAPLING, "&d&lSpruce Wood"));
		inv.setItem(33, mainItems.makeTeleportItem(Material.DARK_OAK_SAPLING, "&6&lDark Oak Wood"));

		inv.setItem(44, mainItems.back());

		return inv;
	}

	public Inventory mineWarps() {
		Inventory inv = Bukkit.createInventory(null, 45,
				ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lMines"));

		for (int i = 0; i < 45; i++) {
			inv.setItem(i, mainItems.blackPane());
		}
		// TOP ROW
		inv.setItem(10, mainItems.makeTeleportItem(Material.COAL_ORE, "&8&lCoal Mine"));
		inv.setItem(12, mainItems.makeTeleportItem(Material.LAPIS_ORE, "&9&lLapis Mine"));
		inv.setItem(14, mainItems.makeTeleportItem(Material.REDSTONE_ORE, "&c&lRedstone Mine"));
		inv.setItem(16, mainItems.makeTeleportItem(Material.IRON_ORE, "&7&lIron Mine"));

		// BOTTOM ROW
		inv.setItem(29, mainItems.makeTeleportItem(Material.GOLD_ORE, "&6&lGold Mine"));
		inv.setItem(31, mainItems.makeTeleportItem(Material.DIAMOND_ORE, "&b&lDiamond Mine"));
		inv.setItem(33, mainItems.makeTeleportItem(Material.EMERALD_ORE, "&a&lEmerald Mine"));

		inv.setItem(44, mainItems.back());

		return inv;
	}

	public Inventory farmWarps() {
		Inventory inv = Bukkit.createInventory(null, 45,
				ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lMines"));

		for (int i = 0; i < 45; i++) {
			inv.setItem(i, mainItems.blackPane());
		}
		// TOP ROW
		inv.setItem(10, mainItems.makeTeleportItem(Material.WHEAT, "&8&lCoal Mine"));
		inv.setItem(12, mainItems.makeTeleportItem(Material.CARROT, "&9&lLapis Mine"));
		inv.setItem(14, mainItems.makeTeleportItem(Material.POTATO, "&c&lRedstone Mine"));
		inv.setItem(16, mainItems.makeTeleportItem(Material.MELON_SLICE, "&7&lIron Mine"));

		// BOTTOM ROW
		inv.setItem(28, mainItems.makeTeleportItem(Material.PUMPKIN, "&6&lGold Mine"));
		inv.setItem(30, mainItems.makeTeleportItem(Material.SUGAR_CANE, "&b&lDiamond Mine"));
		inv.setItem(32, mainItems.makeTeleportItem(Material.CACTUS, "&a&lEmerald Mine"));
		inv.setItem(34, mainItems.makeTeleportItem(Material.BEETROOT, "&a&lEmerald Mine"));

		inv.setItem(44, mainItems.back());

		return inv;
	}

	public Inventory playerInvites(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54,
				ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lInvite Players"));

		for (int i = 0; i < 9; i++) {
			inv.setItem(i, mainItems.blackPane());
		}

		inv.setItem(9, mainItems.blackPane());
		inv.setItem(17, mainItems.blackPane());
		inv.setItem(18, mainItems.blackPane());
		inv.setItem(26, mainItems.blackPane());
		inv.setItem(27, mainItems.blackPane());
		inv.setItem(35, mainItems.blackPane());
		inv.setItem(36, mainItems.blackPane());
		inv.setItem(44, mainItems.blackPane());

		for (int i = 45; i < 53; i++) {
			inv.setItem(i, mainItems.blackPane());
		}

		inv.setItem(53, mainItems.back());

		Bukkit.getOnlinePlayers().forEach(all -> {
			if (!all.equals(player)) {
				String displayName = ChatColor.DARK_PURPLE + all.getName();
				inv.addItem(mainItems.makeSkullItem(displayName, all));
			}
		});
		return inv;
	}

	public Inventory invited(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27,
				ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lInvited by: " + player.getName()));

		for (int i = 0; i < 27; i++) {
			inv.setItem(i, mainItems.blackPane());
		}

		inv.setItem(12, mainItems.makeClickMeItem(Material.EMERALD_BLOCK, "&a&lAccept"));
		inv.setItem(14, mainItems.makeClickMeItem(Material.REDSTONE_BLOCK, "&c&lDeny"));

		// TODO: Add yes/no

		return inv;
	}

	public Inventory players(IslandMethods data) {
		Inventory inv = Bukkit.createInventory(null, 54,
				ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lPlayers"));

		for (int i = 0; i < 9; i++) {
			inv.setItem(i, mainItems.blackPane());
		}

		inv.setItem(9, mainItems.blackPane());
		inv.setItem(17, mainItems.blackPane());
		inv.setItem(18, mainItems.blackPane());
		inv.setItem(26, mainItems.blackPane());
		inv.setItem(27, mainItems.blackPane());
		inv.setItem(35, mainItems.blackPane());
		inv.setItem(36, mainItems.blackPane());
		inv.setItem(44, mainItems.blackPane());

		for (int i = 45; i < 53; i++) {
			inv.setItem(i, mainItems.blackPane());
		}

		inv.setItem(53, mainItems.back());
		OfflinePlayer leaderPlayer = Bukkit.getOfflinePlayer((UUID) data.getConfig().get("teamLeader"));
		
		ItemStack leader = mainItems.makeSkullItem("&5" + leaderPlayer.getName(), leaderPlayer);
		inv.addItem(leader);
		
		data.getTeam().forEach(id -> {
			OfflinePlayer teamMate = Bukkit.getOfflinePlayer(id);
			ItemStack itemstack = mainItems.makeSkullItem("&5" + teamMate.getName(), teamMate);

			inv.addItem(itemstack);
		});

		return inv;
	}
}