package net.worldofsurvival.wosskyblock.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import net.worldofsurvival.wosskyblock.menus.CreateIslandMenu;
import net.worldofsurvival.wosskyblock.menus.IslandManageMenu;
import net.worldofsurvival.wosskyblock.utils.Common;
import net.worldofsurvival.wosskyblock.utils.IslandMethods;

public final class IslandCommand extends PlayerCommand implements TabCompleter {

	private Common common;
	private IslandManageMenu menus;
	private HashMap<UUID, IslandMethods> playerData;
	private CreateIslandMenu createIslandMenu;

	public IslandCommand(Common common, IslandManageMenu menus, HashMap<UUID, IslandMethods> playerData,
			CreateIslandMenu createIslandMenu) {
		// Start fake plugin.yml
		super("island");

		setAliases(Arrays.asList("is"));
		setDescription("Main command for skyblock");
		setUsage("/island <subcommand>");
		// END
		// Passing classes part
		this.common = common;
		this.menus = menus;
		this.playerData = playerData;
		this.createIslandMenu = createIslandMenu;
		// END
	}

	@Override
	public void run(Player sender, String[] args) {
		if (args.length == 0) {
			// TODO: Open menu
			sender.openInventory(menus.main());
			
			IslandMethods data = playerData.get(sender.getUniqueId());
			if ((boolean) data.getConfig().get("hasIsland")) {
				sender.openInventory(menus.main());
			} else
				sender.openInventory(createIslandMenu.createIsland());
		} else if (args.length == 1) {
			// Text menu for people who prefer typing
			switch (args[0]) {
			case "help":
			case "info":
				common.tell(sender, "&d--===[ &5&lSkyblock &d]===--");
				common.tell(sender, "&d- &5/is tp <name>");
				common.tell(sender, "&d- &5/is warp [name]");
				common.tell(sender, "&d- &5/is setbiome [biome]");
				common.tell(sender, "&d- &5/is upgrade");
				common.tell(sender, "&d--===[ &5&lSkyblock &d]===--");
				break;

			default:
				common.tell(sender, common.getPrefix + "Unknown argument! Use the GUI instead!");
			}

		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> fullList = Arrays.asList("tp", "warp", "setbiome", "upgrade", "help");
		if (sender instanceof Player) {
			List<String> returnedList = new ArrayList<String>();
			if (command.getName().equalsIgnoreCase("island")) {
				if (!args[0].equals("")) {
					for (String arg : fullList) {
						if (arg.startsWith(args[0])) {
							returnedList.add(arg);
						}
					}
					return returnedList;
				} else
					return fullList;
			}
		}
		return fullList;
	}
}
