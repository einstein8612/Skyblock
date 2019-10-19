package net.worldofsurvival.wosskyblock;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.worldofsurvival.wosskyblock.commands.IslandCommand;
import net.worldofsurvival.wosskyblock.commands.TestCommand;
import net.worldofsurvival.wosskyblock.generators.IslandGenerator;
import net.worldofsurvival.wosskyblock.generators.VoidGenerator;
import net.worldofsurvival.wosskyblock.items.MainItems;
import net.worldofsurvival.wosskyblock.listeners.BlockBreakListener;
import net.worldofsurvival.wosskyblock.listeners.BlockPlaceListener;
import net.worldofsurvival.wosskyblock.listeners.InventoryClickListener;
import net.worldofsurvival.wosskyblock.listeners.PlayerDropListener;
import net.worldofsurvival.wosskyblock.listeners.PlayerInteractListener;
import net.worldofsurvival.wosskyblock.listeners.PlayerJoinListener;
import net.worldofsurvival.wosskyblock.listeners.PlayerRespawnListener;
import net.worldofsurvival.wosskyblock.menus.CreateIslandMenu;
import net.worldofsurvival.wosskyblock.menus.IslandManageMenu;
import net.worldofsurvival.wosskyblock.utils.Common;
import net.worldofsurvival.wosskyblock.utils.DataManager;
import net.worldofsurvival.wosskyblock.utils.IslandMethods;

public final class WOSSkyblock extends JavaPlugin {

	private Common common = new Common();
	private MainItems mainItems = new MainItems();
	private IslandManageMenu mainSelectorMenu = new IslandManageMenu(mainItems);
	private DataManager datam;
	private FileConfiguration skyblocks;
	private HashMap<UUID, IslandMethods> playerData = new HashMap<UUID, IslandMethods>();
	private CreateIslandMenu createIslandMenu = new CreateIslandMenu(mainItems);
	private IslandGenerator islandGenerator = new IslandGenerator();

	private static WOSSkyblock instance;

	private Logger logger;

	@Override
	public void onEnable() {

		logger = getLogger();

		instance = this;

		worldSetup();
		setup();
		registerOnlinePlayers();
		this.registerCommands(
				new IslandCommand(common, mainSelectorMenu, playerData, createIslandMenu),
				new TestCommand(datam, playerData, islandGenerator));

		this.registerEvents(this, 
				new BlockPlaceListener(common, playerData),
				new BlockBreakListener(common, playerData),
				new InventoryClickListener(common, mainSelectorMenu, skyblocks, playerData,
						islandGenerator),
				new PlayerInteractListener(common, mainSelectorMenu, createIslandMenu, mainItems, playerData),
				new PlayerDropListener(common, mainItems), 
				new PlayerRespawnListener(mainItems),
				new PlayerJoinListener(datam, mainItems.menu(), playerData)
				// TODO: Add listener for join to give players the menu item on first join
				);
	}

	@Override
	public void onDisable() {

		instance = null;

		playerData.keySet().forEach(uuid -> {
			try {
				playerData.get(uuid).saveData();
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Data saving has failed for: " + uuid);
				e.printStackTrace();
			}
		});

		logger.log(Level.INFO, "Saved playerdata.");

		datam.saveSkyblocks();
		logger.log(Level.INFO, "Saved skyblock info file.");

	}

	public static WOSSkyblock getInstance() {
		return instance;
	}

	private void worldSetup() {
		if (getServer().getWorld("Skyblocks") == null) {
			WorldCreator worldCreator = new WorldCreator("Skyblocks");
			ChunkGenerator generator = new VoidGenerator();
			worldCreator.generateStructures(false);
			worldCreator.type(WorldType.CUSTOMIZED);
			worldCreator.generator(generator);
			worldCreator.createWorld();
		}
	}

	private void registerOnlinePlayers() {
		if (!getServer().getOnlinePlayers().isEmpty()) {
			getServer().getOnlinePlayers().forEach(player -> {
				this.playerData.put(player.getUniqueId(), new IslandMethods(datam.getPlayerFile(player)));
			});
		}
	}

	private void setup() {
		datam = new DataManager();
		datam.setup(this);
		skyblocks = datam.getSkyblocksFile();
	}

	// Registering events.
	private void registerEvents(WOSSkyblock plugin, Listener... listeners) {
		final PluginManager pm = plugin.getServer().getPluginManager();

		for (final Listener lis : listeners)
			pm.registerEvents(lis, plugin);

	}

	// Registering commands to the command map
	private void registerCommands(Command... commands) {
		for (Command command : commands) {
			try {
				final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
				commandMapField.setAccessible(true);

				final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
				commandMap.register(command.getLabel(), command);
			} catch (final Exception e) {
				e.printStackTrace();

				logger.log(Level.SEVERE, "&4Could not register command: " + command.getName());
			}
		}
	}
}