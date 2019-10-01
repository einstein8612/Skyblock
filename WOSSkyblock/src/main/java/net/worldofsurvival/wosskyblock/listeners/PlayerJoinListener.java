package net.worldofsurvival.wosskyblock.listeners;

import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.worldofsurvival.wosskyblock.utils.DataManager;

public final class PlayerJoinListener implements Listener {

	private HashMap<Player, FileConfiguration> playerData;
	private DataManager dm;
	private ItemStack menu;
	public PlayerJoinListener(DataManager datam, ItemStack menu, 
			HashMap<Player, FileConfiguration> playerData) {
		this.playerData = playerData;
		this.dm = datam;
		this.menu = menu;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		playerData.putIfAbsent(event.getPlayer(), dm.getPlayerData(event.getPlayer()));
		if (event.getPlayer().hasPlayedBefore()) {
			event.getPlayer().getInventory().setItem(8, menu);
			dm.createPlayerData(event.getPlayer());
		}
	}
	
}
