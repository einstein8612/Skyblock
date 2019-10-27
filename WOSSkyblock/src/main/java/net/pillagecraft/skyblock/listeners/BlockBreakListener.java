package net.pillagecraft.skyblock.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.pillagecraft.skyblock.utils.Common;
import net.pillagecraft.skyblock.utils.Island;

public final class BlockBreakListener implements Listener {

	private Common common;
	private HashMap<UUID, Island> playerData;

	public BlockBreakListener(Common common, HashMap<UUID, Island> playerData) {
		this.common = common;
		this.playerData = playerData;
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		// TEMP
		final Player player = event.getPlayer();
		if (event.getBlock().getLocation().getWorld() == Bukkit.getWorld("Skyblocks")) {
			Island island = playerData.get(event.getPlayer().getUniqueId());
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