package games.moegirl.fortyyears;

import games.moegirl.fortyyears.block.BlockRegistry;
import games.moegirl.fortyyears.entity.EntityRegistry;
import games.moegirl.fortyyears.item.ItemRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FortyYears.MODID)
public class FortyYears {
    public static final String MODID = "fortyyears";

    public FortyYears() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistry.register(modEventBus);
        EntityRegistry.ENTITIES.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
    }
}
