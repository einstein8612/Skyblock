package net.worldofsurvival.wosskyblock.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import net.worldofsurvival.wosskyblock.menus.IslandManageMenu;
import net.worldofsurvival.wosskyblock.utils.Common;

public final class IslandCommand extends PlayerCommand implements TabCompleter {

	private Common common;
	private IslandManageMenu menus;
	
	public IslandCommand(Common common, IslandManageMenu menus) {
		//Start fake plugin.yml
		super("island");

		setAliases(Arrays.asList("is"));
		setDescription("Main command for skyblock");
		setUsage("/island <subcommand>");
		//END
		//Passing classes part
		this.common = common;
		this.menus = menus;
		//END
	}

	@Override
	public void run(Player sender, String[] args) {
		if (args.length==0) {
			//TODO: Open menu
			sender.openInventory(menus.main());
		} else
			if (args.length==1) {
				//Text menu for people who prefer typing
				switch(args[0]) {
				case "help":
				case "info":
					common.tell(sender, "&e--===[ &6&lWOSSkyblock &e]===--");
					common.tell(sender, "&e- &6/is tp <name>");
					common.tell(sender, "&e- &6/is warp [name]");
					common.tell(sender, "&e- &6/is setbiome [biome]");
					common.tell(sender, "&e- &6/is upgrade");
					common.tell(sender, "&e--===[ &6&lWOSSkyblock &e]===--");
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
				} else return fullList;
			}
		}
		return fullList;
	}
}
