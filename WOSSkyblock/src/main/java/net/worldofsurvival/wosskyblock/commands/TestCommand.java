package net.worldofsurvival.wosskyblock.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.worldofsurvival.wosskyblock.utils.IslandMethods;

public class TestCommand extends Command {
	
	private HashMap<Player, IslandMethods> playerData;	
	public TestCommand(HashMap<Player, IslandMethods> playerData) {
		super("test");
		
		
		this.playerData = playerData;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (playerData.containsKey((Player) sender)) {
			IslandMethods config = playerData.get((Player) sender);
			sender.sendMessage((String) config.getConfig().get("name"));
		}
		return true;
	}
}