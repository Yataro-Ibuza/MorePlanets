package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layernew;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.utils.LoggerMP;

public class GenLayerNibiruHills extends GenLayer
{
    private final GenLayer riverLayer;

    public GenLayerNibiruHills(long seed, GenLayer parent, GenLayer riverLayer)
    {
        super(seed);
        this.parent = parent;
        this.riverLayer = riverLayer;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = this.riverLayer.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                this.initChunkSeed(j + areaX, i + areaY);
                int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];
                int l = aint1[j + 1 + (i + 1) * (areaWidth + 2)];
                boolean flag = (l - 2) % 29 == 0;

                if (k > 255)
                {
                    LoggerMP.debug("old! {}", k);
                }

                Biome biome = Biome.getBiomeForId(k);
                boolean flag1 = biome != null && biome.isMutation();

                if (k != 0 && l >= 2 && (l - 2) % 29 == 1 && !flag1)
                {
                    Biome biome3 = Biome.getMutationForBiome(biome);
                    aint2[j + i * areaWidth] = biome3 == null ? k : Biome.getIdForBiome(biome3);
                }
                else if (this.nextInt(3) != 0 && !flag)
                {
                    aint2[j + i * areaWidth] = k;
                }
                else
                {
                    Biome biome1 = biome;

                    if (biome == MPBiomes.INFECTED_DESERT)
                    {
                        biome1 = Biomes.DESERT_HILLS;
                    }
                    else if (biome == MPBiomes.INFECTED_FOREST)
                    {
                        biome1 = Biomes.FOREST_HILLS;
                    }
                    else if (biome == Biomes.BIRCH_FOREST)
                    {
                        biome1 = Biomes.BIRCH_FOREST_HILLS;
                    }
                    else if (biome == MPBiomes.INFECTED_DEAD_ROOFED_FOREST)
                    {
                        biome1 = MPBiomes.INFECTED_PLAINS;
                    }
                    else if (biome == MPBiomes.INFECTED_DEAD_TAIGA)
                    {
                        biome1 = Biomes.TAIGA_HILLS;
                    }
                    else if (biome == Biomes.REDWOOD_TAIGA)
                    {
                        biome1 = Biomes.REDWOOD_TAIGA_HILLS;
                    }
                    else if (biome == Biomes.COLD_TAIGA)
                    {
                        biome1 = Biomes.COLD_TAIGA_HILLS;
                    }
                    else if (biome == MPBiomes.INFECTED_PLAINS)
                    {
                        if (this.nextInt(3) == 0)
                        {
                            biome1 = Biomes.FOREST_HILLS;
                        }
                        else
                        {
                            biome1 = MPBiomes.INFECTED_FOREST;
                        }
                    }
                    else if (biome == MPBiomes.INFECTED_ICE_PLAINS)
                    {
                        biome1 = Biomes.ICE_MOUNTAINS;
                    }
                    else if (biome == MPBiomes.INFECTED_JUNGLE)
                    {
                        biome1 = Biomes.JUNGLE_HILLS;
                    }
                    else if (biome == MPBiomes.INFECTED_OCEAN)
                    {
                        biome1 = MPBiomes.INFECTED_DEEP_OCEAN;
                    }
                    else if (biome == MPBiomes.INFECTED_EXTREME_HILLS)
                    {
                        biome1 = MPBiomes.INFECTED_EXTREME_HILLS_WITH_TREES;
                    }
                    else if (biome == MPBiomes.INFECTED_DEAD_SAVANNA)
                    {
                        biome1 = Biomes.SAVANNA_PLATEAU;
                    }
                    else if (this.isOrMesaPlateau(k, Biome.getIdForBiome(Biomes.MESA_ROCK)))
                    {
                        biome1 = Biomes.MESA;
                    }
                    else if (biome == MPBiomes.INFECTED_DEEP_OCEAN && this.nextInt(3) == 0)
                    {
                        int i1 = this.nextInt(2);

                        if (i1 == 0)
                        {
                            biome1 = MPBiomes.INFECTED_PLAINS;
                        }
                        else
                        {
                            biome1 = MPBiomes.INFECTED_FOREST;
                        }
                    }

                    int j2 = Biome.getIdForBiome(biome1);

                    if (flag && j2 != k)
                    {
                        Biome biome2 = Biome.getMutationForBiome(biome1);
                        j2 = biome2 == null ? k : Biome.getIdForBiome(biome2);
                    }

                    if (j2 == k)
                    {
                        aint2[j + i * areaWidth] = k;
                    }
                    else
                    {
                        int k2 = aint[j + 1 + (i + 0) * (areaWidth + 2)];
                        int j1 = aint[j + 2 + (i + 1) * (areaWidth + 2)];
                        int k1 = aint[j + 0 + (i + 1) * (areaWidth + 2)];
                        int l1 = aint[j + 1 + (i + 2) * (areaWidth + 2)];
                        int i2 = 0;

                        if (this.isOrMesaPlateau(k2, k))
                        {
                            ++i2;
                        }
                        if (this.isOrMesaPlateau(j1, k))
                        {
                            ++i2;
                        }
                        if (this.isOrMesaPlateau(k1, k))
                        {
                            ++i2;
                        }
                        if (this.isOrMesaPlateau(l1, k))
                        {
                            ++i2;
                        }

                        if (i2 >= 3)
                        {
                            aint2[j + i * areaWidth] = j2;
                        }
                        else
                        {
                            aint2[j + i * areaWidth] = k;
                        }
                    }
                }
            }
        }
        return aint2;
    }

    private boolean isOrMesaPlateau(int biomeIDA, int biomeIDB)
    {
        if (biomeIDA == biomeIDB)
        {
            return true;
        }
        else
        {
            Biome biome = Biome.getBiome(biomeIDA);
            Biome biome1 = Biome.getBiome(biomeIDB);

            if (biome != null && biome1 != null)
            {
                if (biome != Biomes.MESA_ROCK && biome != Biomes.MESA_CLEAR_ROCK)
                {
                    return biome == biome1 || biome.getBiomeClass() == biome1.getBiomeClass();
                }
                else
                {
                    return biome1 == Biomes.MESA_ROCK || biome1 == Biomes.MESA_CLEAR_ROCK;
                }
            }
            else
            {
                return false;
            }
        }
    }
}