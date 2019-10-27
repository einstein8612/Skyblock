package net.pillagecraft.skyblock.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public final class Island {

	private FileConfiguration playerConfig;
	private File playerFile;

	public Island(File playerFile) {
		this.playerFile = playerFile;
		this.playerConfig = YamlConfiguration.loadConfiguration(playerFile);
	}

	public FileConfiguration getConfig() {
		return playerConfig;
	}

	public void saveData() {
		try {
			playerConfig.save(playerFile);
		} catch (IOException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Data saving has failed for: " + playerConfig.getName());
			e.printStackTrace();
		}
	}

	public void teleport(Player player) {
		World skyblock = Bukkit.getWorld("Skyblocks");
		Location location = new Location(skyblock, playerConfig.getDouble("islandMiddle.X"),
				playerConfig.getInt("islandMiddle.Y"), playerConfig.getDouble("islandMiddle.Z"));

		if (!this.isSafe(location)) {
			player.teleport(this.getHighestBock(location));
			return;
		}
		player.teleport(location);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getTeam() {
		return (ArrayList<String>) playerConfig.get("teamMates");
	}

	public void setTeam(ArrayList<String> teamMates) {
		playerConfig.set("teamMates", teamMates);
	}
	
	public OfflinePlayer getLeader() {
		return Bukkit.getOfflinePlayer(UUID.fromString(playerConfig.getString("teamLeader")));
	}
	
	public void setLeader(UUID uuid) {
		playerConfig.set("teamLeader", uuid.toString());
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

	private Location getHighestBock(Location location) {
		int i = 255;
		while (i > 0) {
			location.setY(i);
			if (location.getBlock().getType() == Material.AIR || location.getBlock().getType() == Material.LAVA) {
				// Not safe
				i--;
				continue;
			} else
				// Safe
				location.setY(location.getY() + 1);
			return location;

		}
		return location;
	}
}