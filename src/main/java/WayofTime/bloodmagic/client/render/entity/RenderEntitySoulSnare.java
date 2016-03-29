package WayofTime.bloodmagic.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import WayofTime.bloodmagic.entity.projectile.EntitySoulSnare;
import WayofTime.bloodmagic.registry.ModItems;

public class RenderEntitySoulSnare extends Render<EntitySoulSnare>
{
    private final RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

    public RenderEntitySoulSnare(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    public void doRender(EntitySoulSnare entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        this.bindTexture(TextureMap.locationBlocksTexture);
        this.renderItem.renderItem(new ItemStack(ModItems.soulSnare), ItemCameraTransforms.TransformType.GROUND);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected ResourceLocation getEntityTexture(EntitySoulSnare entity)
    {
        return TextureMap.locationBlocksTexture;
    }
}
