package games.moegirl.fortyyears.client;

import games.moegirl.fortyyears.FortyYears;
import games.moegirl.fortyyears.entity.EntityPonyo;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderPonyo extends MobRenderer<EntityPonyo, ModelPonyo> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(FortyYears.MODID, "textures/entity/ponyo.png");

    public RenderPonyo(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelPonyo(), 0.2f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPonyo entity) {
        return TEXTURE;
    }
}
