package net.worldofsurvival.wosskyblock.listeners;

import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.worldofsurvival.wosskyblock.items.MainItems;
import net.worldofsurvival.wosskyblock.menus.IslandManageMenu;
import net.worldofsurvival.wosskyblock.utils.Common;

public final class PlayerInteractListener implements Listener {

	private Common common;
	private IslandManageMenu menus;
	private MainItems items;
	private HashMap<Player, FileConfiguration> playerData;

	public PlayerInteractListener(Common common, IslandManageMenu menu, 
			MainItems items, HashMap<Player, FileConfiguration> playerData) {
		this.playerData = playerData;
		this.common = common;
		this.menus = menu;
		this.items = items;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getItem() == null || !event.getItem().getItemMeta().getDisplayName().contains(common.colorize("&c&l&f&3&5&2"))) return;

		if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			event.setCancelled(true);
		}
		if (event.getItem().equals(items.menu()) ) {
			if ((boolean) playerData.get(event.getPlayer()).get("hasIsland")) {
			event.getPlayer().openInventory(menus.main());
			} else common.tell(event.getPlayer(), "Insert create island menu");
		}
	}
}