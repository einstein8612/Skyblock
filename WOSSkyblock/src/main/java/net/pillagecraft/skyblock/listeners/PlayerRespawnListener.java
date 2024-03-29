package net.pillagecraft.skyblock.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import net.pillagecraft.skyblock.items.MainItems;

public class PlayerRespawnListener implements Listener {

	private MainItems items;

	public PlayerRespawnListener(MainItems items) {
		this.items = items;
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		event.getPlayer().getInventory().setItem(8, items.menu());
	}

}