package net.worldofsurvival.wosskyblock.listeners;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.worldofsurvival.wosskyblock.generators.IslandGenerator;
import net.worldofsurvival.wosskyblock.menus.CreateIslandMenu;
import net.worldofsurvival.wosskyblock.menus.IslandManageMenu;
import net.worldofsurvival.wosskyblock.utils.Common;
import net.worldofsurvival.wosskyblock.utils.IslandMethods;

public final class InventoryClickListener implements Listener {

	private CreateIslandMenu createIslandMenu;
	private IslandManageMenu islandManageMenu;
	private IslandGenerator generator;
	private FileConfiguration skyblocks;
	private HashMap<Player, IslandMethods> playerData;
	private Common common;
	public InventoryClickListener(Common common, CreateIslandMenu createIslandMenu, 
			IslandManageMenu islandManageMenu, FileConfiguration skyblocks, 
			HashMap<Player, IslandMethods> playerData, IslandGenerator generator) {
		this.generator = generator;
		this.common = common;
		this.islandManageMenu = islandManageMenu;
		this.createIslandMenu = createIslandMenu;
		this.skyblocks = skyblocks;
		this.playerData = playerData;
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().contains(common.colorize("&c&l&f&3&5&2")) || event.getCurrentItem() == null) return;

		event.setCancelled(true);

		final Player player = (Player) event.getWhoClicked();
		final String title = common.decolor(event.getView().getTitle());
		final String item = common.decolor(event.getCurrentItem().getItemMeta().getDisplayName());
		final IslandMethods island = playerData.get(player);

		if (common.decolor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Back")) {
			switch(common.decolor(event.getView().getTitle())) {
			case"Warp Menu":
			case"Players":
				player.openInventory(islandManageMenu.main());
				break;
			case"Wood Farms":
			case"Mines":
				player.openInventory(islandManageMenu.warps());
				break;
			default:
				break;
			}
			return;
		}
		switch (title) {
		//Main menu
		case"Skyblock Menu":
			switch(item) {
			case"Set Home":
				common.tell(player, "sethome");
				player.closeInventory();
				break;
			case"Home":
				common.tell(player, "Home");
				island.teleport(player);
				player.closeInventory();
				break;
			case"Warps":
				player.openInventory(islandManageMenu.warps());
				break;
			case"Close":
				player.closeInventory();
				break;
			default:
				break;
			}

			//Main > warps buttons
		case"Warp Menu":
			switch(item) {
			case"Mines":
				player.openInventory(islandManageMenu.mineWarps());
				break;
			case"Wood Farms":
				player.openInventory(islandManageMenu.woodWarps());
				break;
			default:
				break;
			}

			//Warp > mines buttons
		case"Mines":
			switch(item) {
			case"Coal Mine":
				common.tell(player, "Coal");
				player.closeInventory();
				break;
			case"Lapis Mine":
				common.tell(player, "Lapis");
				player.closeInventory();
				break;
			case"Redstone Mine":
				common.tell(player, "Redstone");
				player.closeInventory();
				break;
			case"Iron Mine":
				common.tell(player, "Iron");
				player.closeInventory();
				break;
			case"Gold Mine":
				common.tell(player, "Gold");
				player.closeInventory();
				break;
			case"Diamond Mine":
				common.tell(player, "Diamond");
				player.closeInventory();
				break;
			case"Emerald Mine":
				common.tell(player, "Emerald");
				player.closeInventory();
				break;
			default:
				break;
			}

			//Warp > woodfarms buttons
		case"Woodlands":
			switch(item) {
			case"Oak Wood":
				common.tell(player, "Oak");
				player.closeInventory();
				break;
			case"Birch Wood":
				common.tell(player, "Birch");
				player.closeInventory();
				break;
			case"Jungle Wood":
				common.tell(player, "Jungle");
				player.closeInventory();
				break;
			case"Acacia Wood":
				common.tell(player, "Acacia");
				player.closeInventory();
				break;
			case"Spruce Wood":
				common.tell(player, "Spruce");
				player.closeInventory();
				break;
			case"Dark Oak Wood":
				common.tell(player, "Dark Oak");
				player.closeInventory();
				break;
			default:
				break;
			}
		case"Skyblock Creation":
			switch(item) {
			case"Classic Island":
				World skyblock = Bukkit.getWorld("Skyblocks");
				Location location = new Location(skyblock,
						island.getConfig().getInt("islandMiddle.X"),
						128,
						island.getConfig().getInt("islandMiddle.Z"));
				generator.generateClassic(location);
				this.createIslandInfo(island, this.skyblocks);
				player.closeInventory();
				break;
			case"Custom Island":
				common.tell(player, "custom");
				player.closeInventory();
				break;
			case"*Placeholder* Island":
				common.tell(player, "placeholder");
				player.closeInventory();
				break;
			default:
				break;
			}
		}
	}
	private void createIslandInfo(IslandMethods island, FileConfiguration skyblocks) {

		if (skyblocks.getConfigurationSection("Skyblocks") == null) {
			skyblocks.createSection("Skyblocks");
		}
		Set<String> keys = skyblocks.getConfigurationSection("Skyblocks").getKeys(false);
		int count = keys.size();
		
		if (count==1 && skyblocks.get("Skyblocks."+String.valueOf(1)).equals("Don't remove this!")) count=0;

		island.getConfig().set("islandMiddle.World", "Skyblocks");
		island.getConfig().set("islandMiddle.X", 2000*count);
		island.getConfig().set("islandMiddle.Y", 135);
		island.getConfig().set("islandMiddle.Z", 2000*count);

		island.getConfig().set("hasIsland", true);

		skyblocks.set("Skyblocks." + count, "Tested");
	}
}