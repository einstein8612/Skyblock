package net.worldofsurvival.wosskyblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
		Player player = (Player) sender;
		dm.createPlayerData(player);
		player.sendMessage("Reset playerdata for yourself");


		return true;
	}
}