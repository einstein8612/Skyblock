package net.pillagecraft.skyblock.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import net.pillagecraft.skyblock.items.MainItems;
import net.pillagecraft.skyblock.utils.Common;

public class PlayerDropListener implements Listener {

	private Common common;
	private MainItems items;

	public PlayerDropListener(Common common, MainItems items) {
		this.common = common;
		this.items = items;
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack().equals(items.menu())) {
			event.setCancelled(true);
			common.tell(event.getPlayer(), "&cYou can't drop the menu item!");
		}
	}
}