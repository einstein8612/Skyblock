package net.worldofsurvival.wosskyblock;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.worldofsurvival.wosskyblock.commands.IslandCommand;
import net.worldofsurvival.wosskyblock.commands.TestCommand;
import net.worldofsurvival.wosskyblock.items.MainItems;
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
	private HashMap<Player, IslandMethods> playerData = new HashMap<Player, IslandMethods>();
	private CreateIslandMenu createIslandMenu = new CreateIslandMenu(mainItems);

	@Override
	public void onEnable() {
		setup();
		this.registerCommands(
				new IslandCommand(common, mainSelectorMenu),
				new TestCommand(playerData)
				);

		this.registerEvents(this, 
				new InventoryClickListener(common, createIslandMenu, mainSelectorMenu),
				new PlayerInteractListener(common, mainSelectorMenu, createIslandMenu, mainItems, playerData),
				new PlayerDropListener(common, mainItems),
				new PlayerRespawnListener(mainItems),
				new PlayerJoinListener(datam, mainItems.menu(), playerData)
				//TODO: Add listener for join to give players the menu item on firs join
				);
	}
	
	@Override
	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			try {
				playerData.get(player).saveData(player);
			} catch (IOException e) {
				common.logError("Data saving has failed for: " + player.getName());
				e.printStackTrace();
			}
		}
		Bukkit.getConsoleSender().sendMessage("Saved");
	}

	private void setup() {
		datam = new DataManager();
		datam.setup(this);
	}
	
	//Registering events.
	private void registerEvents(WOSSkyblock plugin, Listener... listeners) {
		final PluginManager pm = plugin.getServer().getPluginManager();

		for (final Listener lis : listeners)
			pm.registerEvents(lis, plugin);
	}

	//Registering commands to the command map
	private void registerCommands(Command... commands) {
		for (Command command : commands) {
			try {
				final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
				commandMapField.setAccessible(true);

				final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
				commandMap.register(command.getLabel(), command);
			} catch (final Exception e) {
				e.printStackTrace();

				common.log("&4Could not register command: " + command.getName());
			}
		}
	}
}