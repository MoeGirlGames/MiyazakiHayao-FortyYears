package games.moegirl.fortyyears.entity;

import games.moegirl.fortyyears.FortyYears;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, FortyYears.MODID);

    public static final EntityType<EntityPonyo> ponyo = EntityType.Builder.create(EntityPonyo::new, EntityClassification.WATER_CREATURE)
            .size(0.4375f, 0.5f)
            .trackingRange(4)
            .build("ponyo");

    public static final RegistryObject<EntityType<EntityPonyo>> ponyoType = ENTITIES.register("ponyo", () -> ponyo);

    @SubscribeEvent
    public static void onAttributeRegister(EntityAttributeCreationEvent event) {
        event.put(ponyo, AbstractFishEntity.func_234176_m_().create());
    }
}
