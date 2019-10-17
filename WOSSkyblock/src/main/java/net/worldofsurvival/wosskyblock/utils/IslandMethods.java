package net.worldofsurvival.wosskyblock.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public final class IslandMethods {

	private FileConfiguration playerConfig;
	private File playerFile;

	public IslandMethods(File playerFile) {
		this.playerFile = playerFile;
		this.playerConfig = YamlConfiguration.loadConfiguration(playerFile);
	}

	public FileConfiguration getConfig() {
		return playerConfig;
	}

	public void saveData(Player player) throws IOException {
		playerConfig.save(playerFile);
	}

	public void teleport(Player player) {
		World skyblock = Bukkit.getWorld("Skyblocks");
		Location location = new Location(skyblock, playerConfig.getInt("islandMiddle.X"),
				playerConfig.getInt("islandMiddle.Y"), playerConfig.getInt("islandMiddle.Z"));
		if (!this.isSafe(location)) {
			location.setY(skyblock.getHighestBlockYAt(location));
			player.teleport(location);
			return;
		}
		player.teleport(location);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<UUID> getTeam() {
		return (ArrayList<UUID>) playerConfig.get("teamMates");
	}

	private boolean isSafe(Location location) {
		Block feet = location.getBlock();
		if (feet.getType().isSolid()) {
			return false; // not transparent (will suffocate)
		}
		Block head = feet.getRelative(BlockFace.UP);
		if (head.getType().isSolid()) {
			return false; // not transparent (will suffocate)
		}
		Block ground = feet.getRelative(BlockFace.DOWN);
		// returns if the ground is solid or not.
		return ground.getType().isSolid();
	}
}