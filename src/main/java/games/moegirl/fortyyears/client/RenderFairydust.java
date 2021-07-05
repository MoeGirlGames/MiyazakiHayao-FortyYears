package games.moegirl.fortyyears.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import games.moegirl.fortyyears.FortyYears;
import games.moegirl.fortyyears.entity.EntityFairydust;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class RenderFairydust extends MobRenderer<EntityFairydust, ModelFairydust> {

    private static final ResourceLocation[] TEXTURES = new ResourceLocation[]{
            new ResourceLocation(FortyYears.MODID, "textures/entity/fairy0.png"),
            new ResourceLocation(FortyYears.MODID, "textures/entity/fairy1.png"),
            new ResourceLocation(FortyYears.MODID, "textures/entity/fairy2.png"),
            new ResourceLocation(FortyYears.MODID, "textures/entity/fairy3.png")};
    private static final Random random = new Random(System.currentTimeMillis());

    private long lastRenderTick = -1;
    private long eyeAnimBeginTick = -1;
    private long eyeAnimCooldown = 1;
    private int selectedImage = 0;

    public RenderFairydust(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelFairydust(), 0.2f);
    }

    @Override
    public void render(EntityFairydust entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        long currentTick = entityIn.world.getGameTime();
        if (eyeAnimCooldown > 0) {
            selectedImage = 3;
            if (lastRenderTick >= 0) {
                eyeAnimCooldown -= (currentTick - lastRenderTick);
            }
            lastRenderTick = currentTick;
        } else if (random.nextFloat() <= 0.1f) {
            selectedImage = 3;
            eyeAnimBeginTick = currentTick;
        } else if (eyeAnimBeginTick > 0) {
            long tick = currentTick - eyeAnimBeginTick;
            if (tick <= 2) {
                selectedImage = 3;
            } else if (tick <= 4 || (tick > 10 && tick <= 12)) {
                selectedImage = 2;
            } else if (tick <= 6 || (tick > 8 && tick <= 10)) {
                selectedImage = 1;
            } else if (tick <= 8) {
                selectedImage = 0;
            } else {
                selectedImage = 3;
                eyeAnimBeginTick = -1;
                eyeAnimCooldown = 100;
                lastRenderTick = currentTick;
            }
        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityFairydust entity) {
        return TEXTURES[selectedImage];
    }
}
