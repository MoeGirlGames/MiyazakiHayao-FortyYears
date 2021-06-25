package games.moegirl.fortyyears.items;

import games.moegirl.fortyyears.FortyYears;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FortyYears.MODID);

    public static RegistryObject<Item> kamikaChicken = ITEMS.register("kamika_chicken", KamikaChinken::new);
}
