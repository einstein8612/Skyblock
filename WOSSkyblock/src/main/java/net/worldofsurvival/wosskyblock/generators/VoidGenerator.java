package net.worldofsurvival.wosskyblock.generators;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

public final class VoidGenerator extends ChunkGenerator {

	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
		ChunkData chunk = createChunkData(world);
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				biome.setBiome(x, z, Biome.PLAINS);
			}
		}
		return chunk;
	}
}