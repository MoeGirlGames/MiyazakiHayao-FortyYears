package games.moegirl.fortyyears.generator;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GeneratorEventHandler {

    @SubscribeEvent
    public static void onDataGather(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        generator.addProvider(new ProviderLanguage(generator));
        generator.addProvider(new ProviderBlockState(generator, fileHelper));
        generator.addProvider(new ProviderItemModel(generator, fileHelper));
        generator.addProvider(new ProviderBlockLoot(generator));
    }
}
