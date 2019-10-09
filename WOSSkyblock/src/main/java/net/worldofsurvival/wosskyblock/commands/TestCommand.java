package net.worldofsurvival.wosskyblock.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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
		World world = Bukkit.getWorld("Skyblocks");
		Player player = (Player) sender;
		Location location = new Location(world, 0, 128, 0);
		player.teleport(location);
		player.sendMessage("test");
		return true;
	}
}