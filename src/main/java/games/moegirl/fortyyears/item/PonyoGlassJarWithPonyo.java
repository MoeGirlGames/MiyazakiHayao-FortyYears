package games.moegirl.fortyyears.item;

import games.moegirl.fortyyears.entity.EntityRegistry;
import games.moegirl.fortyyears.entity.EntityPonyo;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class PonyoGlassJarWithPonyo extends Item implements IDispenseItemBehavior {

    private final DefaultDispenseItemBehavior behavior = new DefaultDispenseItemBehavior();

    public PonyoGlassJarWithPonyo() {
        super(new Properties()
                .maxStackSize(1)
                .group(FortyGroup.INSTANCE));
        DispenserBlock.registerDispenseBehavior(this, this);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        BlockRayTraceResult raytrace = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (raytrace.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos pos = raytrace.getPos();
            if (worldIn.getBlockState(pos).getBlock() == Blocks.WATER && worldIn instanceof ServerWorld) {
                this.placeFish((ServerWorld) worldIn, stack, pos);
                return ActionResult.resultSuccess(new ItemStack(ItemRegistry.ponyoGlassJar.get()));
            }
        }
        return ActionResult.resultPass(stack);
    }

    @Override
    public ItemStack dispense(IBlockSource source, ItemStack stack) {
        BlockPos pos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));
        ServerWorld world = source.getWorld();
        if (world.getBlockState(pos).getBlock() == Blocks.WATER) {
            this.placeFish(world, stack, pos);
            return new ItemStack(ItemRegistry.ponyoGlassJar.get());
        } else {
            return behavior.dispense(source, stack);
        }
    }

    private void placeFish(ServerWorld worldIn, ItemStack stack, BlockPos pos) {
        EntityPonyo entity = (EntityPonyo) EntityRegistry.ponyo
                .spawn(worldIn, stack, null, pos, SpawnReason.BUCKET, true, false);
        if (entity != null) {
            entity.setFromBucket(true);
        }
    }
}
