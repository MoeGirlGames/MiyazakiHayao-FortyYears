package games.moegirl.fortyyears.entity;

import games.moegirl.fortyyears.item.ItemRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class EntityPonyo extends AbstractFishEntity {

    public EntityPonyo(EntityType<EntityPonyo> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(Items.WATER_BUCKET);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_SALMON_FLOP;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    protected ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        ItemStack jar = playerIn.getHeldItem(hand);
        if (jar.getItem() == ItemRegistry.ponyoGlassJar.get() && this.isAlive()) {
            this.playSound(SoundEvents.ITEM_BUCKET_FILL_FISH, 1.0F, 1.0F);
            jar.shrink(1);
            ItemStack ponyoJar = new ItemStack(ItemRegistry.ponyoGlassJarWithPonyo.get());
            if (!this.world.isRemote) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) playerIn, ponyoJar);
            }

            if (jar.isEmpty()) {
                playerIn.setHeldItem(hand, ponyoJar);
            } else if (!playerIn.inventory.addItemStackToInventory(ponyoJar)) {
                playerIn.dropItem(ponyoJar, false);
            }

            this.remove();
            return ActionResultType.func_233537_a_(this.world.isRemote);
        } else {
            return ActionResultType.PASS;
        }
    }
}
