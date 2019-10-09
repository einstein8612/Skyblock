package net.worldofsurvival.wosskyblock.commands;

import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import net.worldofsurvival.wosskyblock.utils.DataManager;

public class TestCommand extends Command {
	
	private DataManager dm;	
	public TestCommand(DataManager dm) {
		super("test");
		
		
		this.dm = dm;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		FileConfiguration skyblocks = dm.getSkyblocksFile();
		if (skyblocks.getConfigurationSection("Skyblocks") == null) {
			skyblocks.createSection("Skyblocks");
		}
		Set<String> keys = skyblocks.getConfigurationSection("Skyblocks").getKeys(false);
		int count = keys.size();
		
		sender.sendMessage(String.valueOf(count));
		return true;
	}
}