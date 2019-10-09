package net.worldofsurvival.wosskyblock.utils;

import java.io.File;
import java.io.IOException;

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
		player.teleport(playerConfig.getLocation("islandMiddle"));
	}
}