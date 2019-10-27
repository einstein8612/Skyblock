package net.pillagecraft.skyblock;

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

import net.pillagecraft.skyblock.commands.IslandCommand;
import net.pillagecraft.skyblock.commands.TestCommand;
import net.pillagecraft.skyblock.generators.IslandGenerator;
import net.pillagecraft.skyblock.generators.VoidGenerator;
import net.pillagecraft.skyblock.items.MainItems;
import net.pillagecraft.skyblock.listeners.BlockBreakListener;
import net.pillagecraft.skyblock.listeners.BlockPlaceListener;
import net.pillagecraft.skyblock.listeners.InventoryClickListener;
import net.pillagecraft.skyblock.listeners.PlayerDropListener;
import net.pillagecraft.skyblock.listeners.PlayerInteractListener;
import net.pillagecraft.skyblock.listeners.PlayerJoinListener;
import net.pillagecraft.skyblock.listeners.PlayerRespawnListener;
import net.pillagecraft.skyblock.menus.CreateIslandMenu;
import net.pillagecraft.skyblock.menus.IslandManageMenu;
import net.pillagecraft.skyblock.utils.Common;
import net.pillagecraft.skyblock.utils.DataManager;
import net.pillagecraft.skyblock.utils.Island;

public final class Skyblock extends JavaPlugin {

	private Common common = new Common();
	private MainItems mainItems = new MainItems();
	private IslandManageMenu mainSelectorMenu = new IslandManageMenu(mainItems);
	private DataManager datam;
	private FileConfiguration skyblocks;
	private HashMap<UUID, Island> playerData = new HashMap<UUID, Island>();
	private CreateIslandMenu createIslandMenu = new CreateIslandMenu(mainItems);
	private IslandGenerator islandGenerator = new IslandGenerator();

	private static Skyblock instance;

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
						islandGenerator, datam),
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
		
		WorldCreator world = new WorldCreator("test");
		world.createWorld();
		
		playerData.keySet().forEach(uuid -> {
				playerData.get(uuid).saveData();
		});

		logger.log(Level.INFO, "Saved playerdata.");

		datam.saveSkyblocks();
		logger.log(Level.INFO, "Saved skyblock info file.");

	}

	public static Skyblock getInstance() {
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
				this.playerData.put(player.getUniqueId(), new Island(datam.getPlayerFile(player.getUniqueId())));
			});
		}
	}

	private void setup() {
		datam = new DataManager();
		datam.setup(this);
		skyblocks = datam.getSkyblocksFile();
	}

	// Registering events.
	private void registerEvents(Skyblock plugin, Listener... listeners) {
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