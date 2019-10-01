package net.worldofsurvival.wosskyblock.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TestCommand extends Command {
	
	private HashMap<Player, FileConfiguration> playerData;	
	public TestCommand(HashMap<Player, FileConfiguration> playerData) {
		super("test");
		
		
		this.playerData = playerData;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (playerData.containsKey((Player) sender)) {
			FileConfiguration config = playerData.get((Player) sender);
			sender.sendMessage((String) config.get("name"));
		}
		return true;
	}
}