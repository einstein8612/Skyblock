package net.pillagecraft.skyblock.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;

import net.pillagecraft.skyblock.utils.DataManager;
import net.pillagecraft.skyblock.utils.Island;

public final class PlayerJoinListener implements Listener {

	private HashMap<UUID, Island> playerData;
	private DataManager dm;
	private ItemStack menu;

	public PlayerJoinListener(DataManager datam, ItemStack menu, HashMap<UUID, Island> playerData) {
		this.playerData = playerData;
		this.dm = datam;
		this.menu = menu;
	}

	@EventHandler
	public void onJoin(PlayerLoginEvent event) {
		if (!event.getPlayer().hasPlayedBefore()) {
			event.getPlayer().getInventory().setItem(8, menu);
			dm.createPlayerData(event.getPlayer());
		}
		playerData.putIfAbsent(event.getPlayer().getUniqueId(), new Island(dm.getPlayerFile(event.getPlayer().getUniqueId())));
	}
}