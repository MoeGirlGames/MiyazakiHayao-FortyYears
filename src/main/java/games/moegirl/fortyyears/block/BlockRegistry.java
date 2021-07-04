package games.moegirl.fortyyears.block;

import games.moegirl.fortyyears.FortyYears;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FortyYears.MODID);

    // ponyo
    public static RegistryObject<Block> ponyoSearchlight = BLOCKS.register("ponyo_searchlight", PonyoSearchlight::new);
}
