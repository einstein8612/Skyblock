package net.pillagecraft.skyblock.commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pillagecraft.skyblock.generators.IslandGenerator;
import net.pillagecraft.skyblock.utils.DataManager;
import net.pillagecraft.skyblock.utils.Island;

public class TestCommand extends Command {

	private DataManager dm;
	private HashMap<UUID, Island> playerData;
	private IslandGenerator generator;

	public TestCommand(DataManager dm, HashMap<UUID, Island> playerData, IslandGenerator generator) {
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
			playerData.put(player.getUniqueId(), new Island(dm.getPlayerFile(player.getUniqueId())));
			
			return true;
		}

		if (args[0].equals("testing")) {
			Island data = playerData.get(sender);
			OfflinePlayer leaderPlayer = Bukkit.getOfflinePlayer(UUID.fromString((String) data.getConfig().get("teamLeader")));
			sender.sendMessage(leaderPlayer.toString());

		}
		
		if (args[0].equals("tp")) {
			Island data = playerData.get(sender);
			data.teleport((Player) sender);

		}

		return true;
	}
}