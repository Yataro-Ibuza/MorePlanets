package stevekung.mods.moreplanets.utils;

import java.lang.reflect.Method;

import net.minecraftforge.fml.common.Loader;

public class CompatibilityManagerMP
{
    public static final String baublesModId = "baubles";
    public static final boolean isCTMLoaded = Loader.isModLoaded("ctm");
    public static final boolean isBigReactorLoaded = Loader.isModLoaded("bigreactors");
    public static final boolean isOpenBlocksLoaded = Loader.isModLoaded("openblocks");
    public static final boolean isEnderIOLoaded = Loader.isModLoaded("enderio");
    public static final boolean isMobGrindingUtilsLoaded = Loader.isModLoaded("mob_grinding_utils");
    public static final boolean isCCLLoaded = Loader.isModLoaded("codechickenlib");
    public static final boolean isBaubleLoaded = Loader.isModLoaded("baubles");

    // Extreme Reactors
    private static Method erRegisterFluid;
    private static float conductivityEmerald;
    private static float conductivityCopper;
    private static float conductivityGold;

    public static void init()
    {
        if (CompatibilityManagerMP.isCTMLoaded)
        {
            LoggerMP.info("Enabled CTM integrations");
        }
        if (CompatibilityManagerMP.isBigReactorLoaded)
        {
            CompatibilityManagerMP.initBigReactorCompat();
            CompatibilityManagerMP.registerExtremeReactorFluid("infected_water_fluid", 0.45F, 0.925F, 1.25F, CompatibilityManagerMP.conductivityCopper);
            CompatibilityManagerMP.registerExtremeReactorFluid("purify_water_fluid", 0.525F, 0.75F, 4.0F, CompatibilityManagerMP.conductivityEmerald);
            CompatibilityManagerMP.registerExtremeReactorFluid("cheese_milk_fluid", 0.675F, 0.575F, 1.95F, CompatibilityManagerMP.conductivityGold);
            CompatibilityManagerMP.registerExtremeReactorFluid("crystallized_water_fluid", 0.3F, 0.65F, 2.45F, CompatibilityManagerMP.conductivityCopper);
        }
    }

    public static boolean isModAddedXpFluid()
    {
        return !CompatibilityManagerMP.isOpenBlocksLoaded && !CompatibilityManagerMP.isEnderIOLoaded && !CompatibilityManagerMP.isMobGrindingUtilsLoaded;
    }

    public static void registerExtremeReactorFluid(String name, float absorption, float heatEfficiency, float moderation, float heatConductivity)
    {
        LoggerMP.info("Registering {} into reactor interior", name);

        try
        {
            CompatibilityManagerMP.erRegisterFluid.invoke(null, name, absorption, heatEfficiency, moderation, heatConductivity);
        }
        catch (Exception e) {}
    }

    private static void initBigReactorCompat()
    {
        LoggerMP.info("Enabled Extreme Reactors integrations");

        try
        {
            Class<?> reactorInterior = Class.forName("erogenousbeef.bigreactors.api.registry.ReactorInterior");
            Class<?> iHeatEntity = Class.forName("erogenousbeef.bigreactors.api.IHeatEntity");
            CompatibilityManagerMP.erRegisterFluid = reactorInterior.getDeclaredMethod("registerFluid", String.class, float.class, float.class, float.class, float.class);
            CompatibilityManagerMP.conductivityEmerald = iHeatEntity.getDeclaredField("conductivityEmerald").getFloat(iHeatEntity);
            CompatibilityManagerMP.conductivityCopper = iHeatEntity.getDeclaredField("conductivityCopper").getFloat(iHeatEntity);
            CompatibilityManagerMP.conductivityGold = iHeatEntity.getDeclaredField("conductivityGold").getFloat(iHeatEntity);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}