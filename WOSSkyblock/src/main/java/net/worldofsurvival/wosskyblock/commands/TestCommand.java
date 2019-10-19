package net.worldofsurvival.wosskyblock.commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.worldofsurvival.wosskyblock.generators.IslandGenerator;
import net.worldofsurvival.wosskyblock.utils.DataManager;
import net.worldofsurvival.wosskyblock.utils.IslandMethods;

public class TestCommand extends Command {

	private DataManager dm;
	private HashMap<Player, IslandMethods> playerData;
	private IslandGenerator generator;

	public TestCommand(DataManager dm, HashMap<Player, IslandMethods> playerData, IslandGenerator generator) {
		super("test");

		this.playerData = playerData;
		this.generator = generator;
		this.dm = dm;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		
		if (args[0].equalsIgnoreCase("paste")) {
			Player player = (Player) sender;
			generator.generateIsland(player.getLocation(), "worldChunk");
		}
		
		if (args[0].equalsIgnoreCase("reset")) {
			Player player = (Player) sender;
			dm.createPlayerData(player);
			player.sendMessage("Reset playerdata for yourself");
			return true;
		}

		if (args[0].equals("testing")) {
			IslandMethods data = playerData.get(sender);
			OfflinePlayer leaderPlayer = Bukkit.getOfflinePlayer(UUID.fromString((String) data.getConfig().get("teamLeader")));
			sender.sendMessage(leaderPlayer.toString());

		}

		return true;
	}
}