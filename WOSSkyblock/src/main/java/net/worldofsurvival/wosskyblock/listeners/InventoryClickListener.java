package net.worldofsurvival.wosskyblock.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.worldofsurvival.wosskyblock.utils.Common;

public final class InventoryClickListener implements Listener {

	private Common common;
	public InventoryClickListener(Common common) {
		this.common = common;
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().contains(common.colorize("&c&l&f&3&5&2")) || event.getCurrentItem() == null) return;

		switch (common.decolor(event.getView().getTitle())) {
		case "Skyblock Menu":
			common.tell(event.getWhoClicked(), "inventory");
			break;
		default:
			common.tell(event.getWhoClicked(), "not inventory");
			break;

		}

	}
}
