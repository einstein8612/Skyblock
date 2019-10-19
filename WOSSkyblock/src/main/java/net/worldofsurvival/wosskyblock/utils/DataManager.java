package net.worldofsurvival.wosskyblock.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public final class DataManager {

	private File dataFolder;
	private File schemFolder;

	private File configFile;
	private FileConfiguration config;

	private File skyblocksFile;
	private FileConfiguration skyblocks;

	private Plugin plugin;

	public void setup(Plugin plugin) {
		this.plugin = plugin;

		this.dataFolder = new File(plugin.getDataFolder(), "playerdata");
		this.configFile = new File(plugin.getDataFolder(), "config.yml");
		this.skyblocksFile = new File(plugin.getDataFolder(), "skyblocks.yml");

		this.schemFolder = new File(plugin.getDataFolder(), "schematics");

		// MAKE DATA FOLDER
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		if (!dataFolder.exists()) {
			dataFolder.mkdir();
		}

		if (!schemFolder.exists()) {
			schemFolder.mkdir();
			plugin.saveResource("schematics/classic.schem", false);
		}

		if (!configFile.exists()) {
			plugin.saveResource("config.yml", false);
		}

		if (!skyblocksFile.exists()) {
			plugin.saveResource("skyblocks.yml", false);
		}

		config = YamlConfiguration.loadConfiguration(configFile);
		skyblocks = YamlConfiguration.loadConfiguration(skyblocksFile);

	}

	public File getPlayerFile(Player player) {
		File file = new File(plugin.getDataFolder(), "playerdata/" + player.getUniqueId().toString() + ".yml");
		return file;
	}

	public FileConfiguration getSkyblocksFile() {
		return skyblocks;
	}

	public void saveSkyblocks() {
		try {
			skyblocks.save(skyblocksFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createPlayerData(Player player) {
		File file = new File(plugin.getDataFolder(), "playerdata/" + player.getUniqueId().toString() + ".yml");
		FileConfiguration data = YamlConfiguration.loadConfiguration(file);
		data.set("name", player.getName());
		data.set("uuid", player.getUniqueId().toString());
		data.set("hasTeam", false);
		data.set("hasIsland", false);
		data.set("islandResets", 3);
		data.set("maxIsland", 125);
		data.set("teamMates", new ArrayList<UUID>());
		data.set("teamLeader", "");
		try {
			data.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FileConfiguration config() {
		return config;
	}
}