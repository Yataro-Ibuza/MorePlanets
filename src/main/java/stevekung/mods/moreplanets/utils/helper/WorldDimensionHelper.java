package stevekung.mods.moreplanets.utils.helper;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

public class WorldDimensionHelper
{
    public static WorldServer getStartWorld(WorldServer worldOld)
    {
        WorldProvider wp = WorldUtil.getProviderForNameServer(ConfigManagerMP.moreplanets_general.startedPlanet);
        WorldServer worldNew = wp == null ? null : (WorldServer) wp.world;

        if (worldNew != null)
        {
            return worldNew;
        }
        return worldOld;
    }
}