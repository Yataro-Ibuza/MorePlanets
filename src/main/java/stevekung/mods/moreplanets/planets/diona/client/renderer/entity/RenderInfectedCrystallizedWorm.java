package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.model.ModelInfectedCrystallizedWorm;
import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedCrystallizedWorm;
import stevekung.mods.moreplanets.utils.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderInfectedCrystallizedWorm extends RenderLiving<EntityInfectedCrystallizedWorm>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_crystallized_worm.png");

    public RenderInfectedCrystallizedWorm(RenderManager manager)
    {
        super(manager, new ModelInfectedCrystallizedWorm(), 0.4F);
        this.addLayer(new LayerGlowingTexture(this, "infected_crystallized_worm_eyes", true));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCrystallizedWorm entity)
    {
        return RenderInfectedCrystallizedWorm.TEXTURE;
    }
}