package games.moegirl.fortyyears.item;

import games.moegirl.fortyyears.FortyYears;
import games.moegirl.fortyyears.block.BlockRegistry;
import games.moegirl.fortyyears.entity.EntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FortyYears.MODID);
    public static final DeferredRegister<Item> BLOCKS = DeferredRegister.create(ForgeRegistries.ITEMS, FortyYears.MODID);
    public static final DeferredRegister<Item> EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, FortyYears.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        EGGS.register(eventBus);
    }

    // kamika
    public static RegistryObject<Item> kamikaChicken = ITEMS.register("kamika_chicken", KamikaChicken::new);
    public static RegistryObject<KamikaMask> kamikaMask = ITEMS.register("kamika_mask", KamikaMask::new);

    // ponyo
    public static RegistryObject<PonyoGlassJar> ponyoGlassJar = ITEMS.register("ponyo_glass_jar", PonyoGlassJar::new);
    public static RegistryObject<PonyoGlassJarWithPonyo> ponyoGlassJarWithPonyo = ITEMS.register("ponyo_glass_jar_with_ponyo", PonyoGlassJarWithPonyo::new);
    public static RegistryObject<BlockItem> ponyoSearchlight = createItem(BlockRegistry.ponyoSearchlight);
    public static RegistryObject<SpawnEggItem> ponyoEgg = createEgg(EntityRegistry.ponyoType, EntityRegistry.ponyo, 0x00FF00, 0x0000FF);
    public static RegistryObject<SpawnEggItem> fairydustEgg = createEgg(EntityRegistry.fairydustType, EntityRegistry.fairydust, 0xFFFFFF, 0x000000);

    private static RegistryObject<BlockItem> createItem(RegistryObject<Block> block) {
        return BLOCKS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().group(FortyGroup.INSTANCE)));
    }

    private static <T extends Entity> RegistryObject<SpawnEggItem> createEgg(RegistryObject<EntityType<T>> entity, EntityType<T> entityType, int primary, int secondary) {
        return EGGS.register(entity.getId().getPath(), () -> new SpawnEggItem(entityType, primary, secondary, new Item.Properties().group(FortyGroup.INSTANCE)));
    }
}
