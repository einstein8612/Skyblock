package net.worldofsurvival.wosskyblock.generators;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

public final class VoidGenerator extends ChunkGenerator {
	
	@Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
		biome.setBiome(chunkX, chunkZ, Biome.PLAINS);
		return createChunkData(world);
    }
}