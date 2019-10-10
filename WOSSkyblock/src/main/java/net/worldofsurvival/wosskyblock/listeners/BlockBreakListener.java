package net.worldofsurvival.wosskyblock.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.worldofsurvival.wosskyblock.utils.Common;
import net.worldofsurvival.wosskyblock.utils.IslandMethods;

public final class BlockBreakListener implements Listener {

	private Common common;
	private HashMap<Player, IslandMethods> playerData;

	public BlockBreakListener(Common common, HashMap<Player, IslandMethods> playerData) {
		this.common = common;
		this.playerData = playerData;
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		//TEMP
		final Player player = event.getPlayer();
		if (event.getBlock().getLocation().getWorld() == Bukkit.getWorld("Skyblocks")) {
			IslandMethods island = playerData.get(event.getPlayer());
			if (!event.getPlayer().hasPermission("wosskyblock.bypass")) {

				final int x = event.getBlock().getX();
				final int z = event.getBlock().getZ();

				final int middleX = island.getConfig().getInt("islandMiddle.X");
				final int middleZ = island.getConfig().getInt("islandMiddle.Z");

				final int max = island.getConfig().getInt("maxIsland");

				if (!((x - middleX) <= max && (x - middleX) >= -max && (z - middleZ) <= max && (z - middleZ) >= -max)) {
					event.setCancelled(true);
					common.tell(player, common.getPrefix + "You can only break blocks inside of your island border!");
				}
			}
		}
	}
}