package net.worldofsurvival.wosskyblock.generators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public final class IslandGenerator {

	public IslandGenerator () {
		//TODO: empty
	}
	
	public void generateClassic(final Location location) {
		Block block = location.getBlock();
		block.setType(Material.BEDROCK);
	}
	
}
