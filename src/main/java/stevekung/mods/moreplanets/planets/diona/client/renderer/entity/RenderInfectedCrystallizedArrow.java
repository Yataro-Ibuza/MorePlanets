package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.entity.projectile.EntityInfectedCrystallizedArrow;
import stevekung.mods.stevekunglib.utils.client.GLConstants;

@SideOnly(Side.CLIENT)
public class RenderInfectedCrystallizedArrow extends Render<EntityInfectedCrystallizedArrow>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_crystallized_arrow.png");

    public RenderInfectedCrystallizedArrow(RenderManager manager)
    {
        super(manager);
    }

    @Override
    public void doRender(EntityInfectedCrystallizedArrow entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.bindEntityTexture(entity);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        int i = 0;
        float f = 0.0F;
        float f1 = 0.5F;
        float f2 = (0 + i * 10) / 32.0F;
        float f3 = (5 + i * 10) / 32.0F;
        float f4 = 0.0F;
        float f5 = 0.15625F;
        float f6 = (5 + i * 10) / 32.0F;
        float f7 = (10 + i * 10) / 32.0F;
        float f8 = 0.05625F;
        GlStateManager.enableRescaleNormal();
        float f9 = entity.arrowShake - partialTicks;

        if (f9 > 0.0F)
        {
            float f10 = -MathHelper.sin(f9 * 3.0F) * f9;
            GlStateManager.rotate(f10, 0.0F, 0.0F, 1.0F);
        }

        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(f8, f8, f8);
        GlStateManager.translate(-4.0F, 0.0F, 0.0F);
        GlStateManager.glNormal3f(f8, 0.0F, 0.0F);
        worldrenderer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(-7.0D, -2.0D, -2.0D).tex(f4, f6).endVertex();
        worldrenderer.pos(-7.0D, -2.0D, 2.0D).tex(f5, f6).endVertex();
        worldrenderer.pos(-7.0D, 2.0D, 2.0D).tex(f5, f7).endVertex();
        worldrenderer.pos(-7.0D, 2.0D, -2.0D).tex(f4, f7).endVertex();
        tessellator.draw();
        GlStateManager.glNormal3f(-f8, 0.0F, 0.0F);
        worldrenderer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(-7.0D, 2.0D, -2.0D).tex(f4, f6).endVertex();
        worldrenderer.pos(-7.0D, 2.0D, 2.0D).tex(f5, f6).endVertex();
        worldrenderer.pos(-7.0D, -2.0D, 2.0D).tex(f5, f7).endVertex();
        worldrenderer.pos(-7.0D, -2.0D, -2.0D).tex(f4, f7).endVertex();
        tessellator.draw();

        for (int j = 0; j < 4; ++j)
        {
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.glNormal3f(0.0F, 0.0F, f8);
            worldrenderer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX);
            worldrenderer.pos(-8.0D, -2.0D, 0.0D).tex(f, f2).endVertex();
            worldrenderer.pos(8.0D, -2.0D, 0.0D).tex(f1, f2).endVertex();
            worldrenderer.pos(8.0D, 2.0D, 0.0D).tex(f1, f3).endVertex();
            worldrenderer.pos(-8.0D, 2.0D, 0.0D).tex(f, f3).endVertex();
            tessellator.draw();
        }
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCrystallizedArrow entity)
    {
        return RenderInfectedCrystallizedArrow.TEXTURE;
    }
}