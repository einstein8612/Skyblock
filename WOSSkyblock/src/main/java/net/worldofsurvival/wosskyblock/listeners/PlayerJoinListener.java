package net.worldofsurvival.wosskyblock.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.worldofsurvival.wosskyblock.utils.DataManager;
import net.worldofsurvival.wosskyblock.utils.IslandMethods;

public final class PlayerJoinListener implements Listener {

	private HashMap<Player, IslandMethods> playerData;
	private DataManager dm;
	private ItemStack menu;

	public PlayerJoinListener(DataManager datam, ItemStack menu, HashMap<Player, IslandMethods> playerData) {
		this.playerData = playerData;
		this.dm = datam;
		this.menu = menu;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (!event.getPlayer().hasPlayedBefore()) {
			event.getPlayer().getInventory().setItem(8, menu);
			dm.createPlayerData(event.getPlayer());
		}
		IslandMethods data = new IslandMethods(dm.getPlayerFile(event.getPlayer()));
		playerData.putIfAbsent(event.getPlayer(), data);
	}
}