package net.worldofsurvival.wosskyblock.generators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;

import net.worldofsurvival.wosskyblock.WOSSkyblock;

public final class IslandGenerator {

	public IslandGenerator() {
		// TODO: empty
	}

	public void generateClassic(final Location location) {
		Block block = location.getBlock();
		block.setType(Material.BEDROCK);

		location.setY(location.getY() + 1);
		location.getBlock().setType(Material.DIRT);
		location.setY(location.getY() + 1);
		location.getWorld().generateTree(location, TreeType.TREE);
	}

	public void generateIsland(final Location location, final String schemName) {
		
		File schematic = new File(WOSSkyblock.getInstance().getDataFolder(), "schematics/" + schemName + ".schem");

		ClipboardFormat format = ClipboardFormats.findByFile(schematic);

		try {
			Clipboard clipboard = format.getReader(new FileInputStream(schematic)).read();
			com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(location.getWorld());
			EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(adaptedWorld, -1);
			Operation operation = new ClipboardHolder(clipboard).createPaste(editSession)
					.to(BlockVector3.at(location.getX(), location.getY(), location.getZ())).ignoreAirBlocks(false).build();
			try {
				Operations.complete(operation);
			} catch (WorldEditException e) {
				
				e.printStackTrace();
			}
			editSession.flushSession();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}




	}
}