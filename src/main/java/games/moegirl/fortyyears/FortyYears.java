package games.moegirl.fortyyears;

import games.moegirl.fortyyears.items.ItemRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FortyYears.MODID)
public class FortyYears {
    public static final String MODID = "fortyyears";

    public FortyYears() {
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
