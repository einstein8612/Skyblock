package net.worldofsurvival.wosskyblock.generators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;

public final class IslandGenerator {

	public IslandGenerator () {
		//TODO: empty
	}

	public void generateClassic(final Location location) {
		Block block = location.getBlock();
		block.setType(Material.BEDROCK);

		location.setY(location.getY()+1);
		location.getBlock().setType(Material.DIRT);
		location.setY(location.getY()+1);
		location.getWorld().generateTree(location, TreeType.TREE);
	}
}