package net.pillagecraft.skyblock.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

import net.pillagecraft.skyblock.generators.IslandGenerator;
import net.pillagecraft.skyblock.menus.IslandManageMenu;
import net.pillagecraft.skyblock.utils.Common;
import net.pillagecraft.skyblock.utils.DataManager;
import net.pillagecraft.skyblock.utils.Island;

public final class InventoryClickListener implements Listener {

	private IslandManageMenu islandManageMenu;
	private IslandGenerator generator;
	private FileConfiguration skyblocks;
	private HashMap<UUID, Island> playerData;
	private Common common;
	private DataManager dm;

	public InventoryClickListener(Common common, IslandManageMenu islandManageMenu, FileConfiguration skyblocks,
			HashMap<UUID, Island> playerData, IslandGenerator generator, DataManager dm) {
		this.generator = generator;
		this.common = common;
		this.islandManageMenu = islandManageMenu;
		this.skyblocks = skyblocks;
		this.playerData = playerData;
		this.dm = dm;
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().contains(common.colorize("&c&l&f&3&5&2")) || event.getCurrentItem() == null)
			return;

		event.setCancelled(true);

		final Player player = (Player) event.getWhoClicked();
		final String title = common.decolor(event.getView().getTitle());
		final String item = common.decolor(event.getCurrentItem().getItemMeta().getDisplayName());
		Island island = playerData.get(player.getUniqueId());

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
				Bukkit.getOnlinePlayers().forEach(sender -> {
					if (sender.getName().equals(name)) {
						this.addPlayer(sender, player);
						player.closeInventory();
					}
				});
			}
			if (item.equals("Deny"))
				player.closeInventory();
		}

		switch (title) {
		// Main menu
		case "Skyblock Menu":
			switch (item) {
			case "Invite Players":
				if (player.getUniqueId().toString().equals(island.getConfig().get("teamLeader"))) { // TODO: Add officer
																									// invite capability
																									// also add officers
					player.openInventory(islandManageMenu.playerInvites(player));
				} else
					common.tell(player, common.getPrefix + "Only the team leader may invite members!");

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
			Bukkit.getOnlinePlayers().forEach(all -> {
				if (all.getName().equals(item)) {
					player.closeInventory();
					all.openInventory(islandManageMenu.invited(player));
					return;
				}
			});
			break;
		case "Players":
			OfflinePlayer target = ((SkullMeta) event.getCurrentItem().getItemMeta()).getOwningPlayer();
			UUID targetUUID = target.getUniqueId();
			if (island.getLeader().getUniqueId().equals(player.getUniqueId())) {

				ArrayList<String> newLeaderTeam = island.getTeam();
				newLeaderTeam.remove(targetUUID.toString());

				island.setTeam(newLeaderTeam);

				common.tell(player, ChatColor.DARK_PURPLE + target.getName() + "&d has been kicked from your island!");

				island.getTeam().forEach(idString -> {
					UUID otherUUID = UUID.fromString(idString);
					File playerFile = dm.getPlayerFile(otherUUID);
					Island otherIsland;

					if (!playerData.containsKey(otherUUID)) {
						otherIsland = new Island(playerFile);
					} else {
						otherIsland = playerData.get(otherUUID);
						common.tell(Bukkit.getPlayer(otherUUID),
								ChatColor.DARK_PURPLE + target.getName() + "&d has been kicked from your island!");
					}

					ArrayList<String> newTeam = otherIsland.getTeam();
					newTeam.remove(targetUUID.toString());

					otherIsland.setTeam(newTeam);

					if (!playerData.containsKey(otherUUID)) {
						otherIsland.saveData();
					}

				});

				Island targetIsland;

				if (target.getPlayer() != null) {
					common.tell(target.getPlayer(),
							"&dYou've been kicked from &5" + player.getDisplayName() + "&d's island!");
					targetIsland = playerData.get(targetUUID);
				} else {
					targetIsland = new Island(dm.getPlayerFile(targetUUID));
				}

				targetIsland.getConfig().set("teamLeader", "");
				targetIsland.getConfig().set("teamMates", new ArrayList<String>());
				targetIsland.getConfig().set("hasIsland", false);
				targetIsland.getConfig().set("islandMiddle", "");

				if (target.getPlayer() == null) {
					targetIsland.saveData();
				}

			} else {
				player.closeInventory();
				common.tell(player, "&cOnly the leader can kick players!");
			}

			break;

		}

	}

	private void createIslandInfo(Player player, Island island, FileConfiguration skyblocks) {

		if (skyblocks.getConfigurationSection("Skyblocks") == null) {
			skyblocks.createSection("Skyblocks");
		}
		Set<String> keys = skyblocks.getConfigurationSection("Skyblocks").getKeys(false);
		int count = keys.size();

		if (count == 1 && skyblocks.get("Skyblocks.0").equals("Don't remove this!"))
			count = 0;

		island.getConfig().set("hasIsland", true);

		island.getConfig().set("islandMiddle.World", "Skyblocks");
		island.getConfig().set("islandMiddle.X", (2000 * count) + 0.5);
		island.getConfig().set("islandMiddle.Y", 128);
		island.getConfig().set("islandMiddle.Z", (2000 * count) + 0.5);

		island.getConfig().set("teamLeader", player.getUniqueId().toString());

		skyblocks.set("Skyblocks." + count, "Tested");
	}

	private void addPlayer(Player player, Player target) {
		Island tIsland = playerData.get(target.getUniqueId());
		Island sIsland = playerData.get(player.getUniqueId());

		@SuppressWarnings("unchecked")
		ArrayList<String> teamMatesString = (ArrayList<String>) sIsland.getConfig().get("teamMates");

		if (teamMatesString.contains(target.getUniqueId().toString())) {
			// TODO: Already in team
			target.sendMessage("You've already joined this player's team!");
			player.sendMessage("Already in team");
			return;
		}
		teamMatesString.add(target.getUniqueId().toString());

		tIsland.getConfig().set("islandMiddle", sIsland.getConfig().get("islandMiddle"));
		tIsland.getConfig().set("teamLeader", player.getUniqueId().toString());
		tIsland.getConfig().set("hasIsland", true);

		sIsland.getConfig().set("teamMates", teamMatesString);

		teamMatesString.forEach(pName -> {
			UUID teamMate = UUID.fromString(pName);
			if (Bukkit.getPlayer(teamMate) == null) {
				File configFile = dm.getPlayerFile(teamMate);
				FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
				config.set("teamMates", teamMatesString);
			} else {
				playerData.get(teamMate).getConfig().set("teamMates", teamMatesString);
			}
		});
	}
}