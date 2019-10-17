package net.worldofsurvival.wosskyblock.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

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

	public InventoryClickListener(Common common, CreateIslandMenu createIslandMenu, IslandManageMenu islandManageMenu,
			FileConfiguration skyblocks, HashMap<Player, IslandMethods> playerData, IslandGenerator generator) {
		this.generator = generator;
		this.common = common;
		this.islandManageMenu = islandManageMenu;
		this.createIslandMenu = createIslandMenu;
		this.skyblocks = skyblocks;
		this.playerData = playerData;
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().contains(common.colorize("&c&l&f&3&5&2")) || event.getCurrentItem() == null)
			return;

		event.setCancelled(true);

		final Player player = (Player) event.getWhoClicked();
		final String title = common.decolor(event.getView().getTitle());
		final String item = common.decolor(event.getCurrentItem().getItemMeta().getDisplayName());
		final IslandMethods island = playerData.get(player);

		if (common.decolor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Back")) {
			switch (common.decolor(event.getView().getTitle())) {
			case "Warp Menu":
			case "Players":
			case "Invite Players":
				player.openInventory(islandManageMenu.main());
				break;
			case "Wood Farms":
			case "Mines":
				player.openInventory(islandManageMenu.warps());
				break;
			default:
				break;
			}
			return;
		}

		if (title.startsWith("Invited by: ")) {
			final String name = title.substring(12);

			if (item.equals("Accept")) {
				for (Player sender : Bukkit.getOnlinePlayers()) {
					if (sender.getName().equals(name)) {
						this.addPlayer(sender, player);
						player.closeInventory();
					}
				}
			}
			if (item.equals("Deny"))
				player.closeInventory();
		}

		switch (title) {
		// Main menu
		case "Skyblock Menu":
			switch (item) {
			case "Invite Players":
				if (player.getUniqueId().equals(island.getConfig().get("teamLeader"))) { //TODO: Add officer invite capability also add officers
					player.openInventory(islandManageMenu.playerInvites(player));
				} else common.tell(player, common.getPrefix + "Only the team leader may invite members!");
				
				break;
			case "Players":
				player.openInventory(islandManageMenu.players(island));
				break;
			case "Set Home":
				common.tell(player, "sethome");
				player.closeInventory();
				break;
			case "Home":
				common.tell(player, common.getPrefix + "You've warped to your island!");
				island.teleport(player);
				player.closeInventory();
				break;
			case "Warps":
				player.openInventory(islandManageMenu.warps());
				break;
			case "Close":
				player.closeInventory();
				break;
			default:
				break;
			}

			// Main > warps buttons
		case "Warp Menu":
			switch (item) {
			case "Mines":
				player.openInventory(islandManageMenu.mineWarps());
				break;
			case "Wood Farms":
				player.openInventory(islandManageMenu.woodWarps());
				break;
			default:
				break;
			}

			// Warp > mines buttons
		case "Mines":
			switch (item) {
			case "Coal Mine":
				common.tell(player, "Coal");
				player.closeInventory();
				break;
			case "Lapis Mine":
				common.tell(player, "Lapis");
				player.closeInventory();
				break;
			case "Redstone Mine":
				common.tell(player, "Redstone");
				player.closeInventory();
				break;
			case "Iron Mine":
				common.tell(player, "Iron");
				player.closeInventory();
				break;
			case "Gold Mine":
				common.tell(player, "Gold");
				player.closeInventory();
				break;
			case "Diamond Mine":
				common.tell(player, "Diamond");
				player.closeInventory();
				break;
			case "Emerald Mine":
				common.tell(player, "Emerald");
				player.closeInventory();
				break;
			default:
				break;
			}

			// Warp > woodfarms buttons
		case "Woodlands":
			switch (item) {
			case "Oak Wood":
				common.tell(player, "Oak");
				player.closeInventory();
				break;
			case "Birch Wood":
				common.tell(player, "Birch");
				player.closeInventory();
				break;
			case "Jungle Wood":
				common.tell(player, "Jungle");
				player.closeInventory();
				break;
			case "Acacia Wood":
				common.tell(player, "Acacia");
				player.closeInventory();
				break;
			case "Spruce Wood":
				common.tell(player, "Spruce");
				player.closeInventory();
				break;
			case "Dark Oak Wood":
				common.tell(player, "Dark Oak");
				player.closeInventory();
				break;
			default:
				break;
			}
		case "Skyblock Creation":
			switch (item) {
			case "Classic Island":
				World skyblock = Bukkit.getWorld("Skyblocks");
				this.createIslandInfo(player, island, this.skyblocks);
				Location location = new Location(skyblock, island.getConfig().getInt("islandMiddle.X"), 128,
						island.getConfig().getInt("islandMiddle.Z"));
				generator.generateIsland(location, "classic");

				player.closeInventory();
				common.tell(player, common.getPrefix + "You've created a classic island!");
				break;
			case "Custom Island":
				common.tell(player, common.getPrefix + "You've created a custom island!");
				player.closeInventory();
				break;
			case "*Placeholder* Island":
				common.tell(player, "This island hasn't been built yet.");
				player.closeInventory();
				break;
			default:
				break;
			}
		case "Invite Players":
			switch (item) {
			case " ":
				break;
			default:
				Bukkit.getOnlinePlayers().forEach(all -> {
					if (all.getName().equals(item)) {
						player.closeInventory();
						all.openInventory(islandManageMenu.invited(player));
						return;
					}
				});
				// TODO: message
				break;
			}
			break;
		}

	}

	private void createIslandInfo(Player player, IslandMethods island, FileConfiguration skyblocks) {

		if (skyblocks.getConfigurationSection("Skyblocks") == null) {
			skyblocks.createSection("Skyblocks");
		}
		Set<String> keys = skyblocks.getConfigurationSection("Skyblocks").getKeys(false);
		int count = keys.size();

		if (count == 1 && skyblocks.get("Skyblocks.0").equals("Don't remove this!"))
			count = 0;

		island.getConfig().set("islandMiddle.World", "Skyblocks");
		island.getConfig().set("islandMiddle.X", 2000 * count);
		island.getConfig().set("islandMiddle.Y", 135);
		island.getConfig().set("islandMiddle.Z", 2000 * count);
		
		island.getConfig().set("teamLeader", player.getUniqueId());

		island.getConfig().set("hasIsland", true);

		skyblocks.set("Skyblocks." + count, "Tested");
	}

	private void addPlayer(Player player, Player target) {
		IslandMethods tIsland = playerData.get(target);
		IslandMethods sIsland = playerData.get(player);

		@SuppressWarnings("unchecked")
		ArrayList<UUID> teamMates = (ArrayList<UUID>) sIsland.getConfig().get("teamMates");

		if (teamMates.contains(target.getUniqueId())) {
			// TODO: Already in team
			target.sendMessage("You've already joined this player's team!");
			player.sendMessage("Already in team");
			return;
		}
		teamMates.add(target.getUniqueId());

		tIsland.getConfig().set("islandMiddle", sIsland.getConfig().get("islandMiddle"));
		sIsland.getConfig().set("teamMates", teamMates);
		tIsland.getConfig().set("teamLeader", player.getUniqueId());
	}
}