package net.worldofsurvival.wosskyblock.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.worldofsurvival.wosskyblock.menus.IslandManageMenu;
import net.worldofsurvival.wosskyblock.utils.Common;

public final class InventoryClickListener implements Listener {

	private IslandManageMenu islandManageMenu;
	private Common common;
	public InventoryClickListener(Common common, IslandManageMenu islandManageMenu) {
		this.common = common;
		this.islandManageMenu = islandManageMenu;
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().contains(common.colorize("&c&l&f&3&5&2")) || event.getCurrentItem() == null) return;

		event.setCancelled(true);
		Player player = (Player) event.getWhoClicked();
		switch (common.decolor(event.getView().getTitle())) {
		case "Skyblock Menu":
			switch(common.decolor(event.getCurrentItem().getItemMeta().getDisplayName())) {
			case"Home":
				common.tell(player, "Home");
				player.closeInventory();
				break;
			case"Warps":
				player.openInventory(islandManageMenu.warps());
				break;
			case"Wood Farms":
				player.openInventory(islandManageMenu.woodWarps());
				break;
			default:
				break;
			}
			break;
		case "Warp Menu":
			switch(common.decolor(event.getCurrentItem().getItemMeta().getDisplayName())) {
			case"Home":
				common.tell(player, "Home");
				player.closeInventory();
				break;
			case"Warps":
				player.openInventory(islandManageMenu.warps());
				break;
			default:
				break;
			}
			break;
		default:
			break;

		}

	}
}
