package net.worldofsurvival.wosskyblock.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import net.worldofsurvival.wosskyblock.utils.Common;
import net.worldofsurvival.wosskyblock.utils.IslandMethods;

public final class BlockPlaceListener implements Listener {

	private Common common;
	private HashMap<Player, IslandMethods> playerData;

	public BlockPlaceListener(Common common, HashMap<Player, IslandMethods> playerData) {
		this.common = common;
		this.playerData = playerData;
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		final Player player = event.getPlayer();
		if (event.getBlock().getLocation().getWorld() == Bukkit.getWorld("Skyblocks")) {
			IslandMethods island = playerData.get(event.getPlayer());
			if (!event.getPlayer().hasPermission("wosskyblock.bypass")) {

				final int x = event.getBlock().getX();
				final int z = event.getBlock().getZ();

				if (!(island.getConfig().get("islandMiddle") instanceof Location)) return;

				Location loc = (Location) island.getConfig().get("islandMiddle");
				final int middleX = loc.getBlockX();
				final int middleZ = loc.getBlockZ();
				
				final int max = island.getConfig().getInt("maxIsland");

				if (!((x - middleX) <= max && (x - middleX) >= -max && (z - middleZ) <= max && (z - middleZ) >= -max)) {
					event.setCancelled(true);
					common.tell(player, common.getPrefix + "You can only break blocks inside of your island border!");
				}
			}
		}
	}

}
