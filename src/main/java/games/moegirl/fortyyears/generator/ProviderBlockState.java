package games.moegirl.fortyyears.generator;

import games.moegirl.fortyyears.FortyYears;
import games.moegirl.fortyyears.block.BlockRegistry;
import games.moegirl.fortyyears.block.PonyoSearchlight;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class ProviderBlockState extends BlockStateProvider {

    public ProviderBlockState(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, FortyYears.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        getVariantBuilder(BlockRegistry.ponyoSearchlight.get()).forAllStates(state -> {
            boolean enabled = state.get(PonyoSearchlight.ENABLED);
            String model = BlockRegistry.ponyoSearchlight.getId().getPath() + (enabled ? "_on" : "_off");
            ResourceLocation texture = modLoc(enabled ? "block/ponyo_searchlight_on" : "block/ponyo_searchlight_off");
            return ConfiguredModel.builder()
                    .modelFile(models().withExistingParent(model, modLoc("block/ponyo_searchlight")).texture("0", texture).texture("particle", texture))
                    .rotationY(((int) state.get(PonyoSearchlight.HORIZONTAL_FACING).getHorizontalAngle() + 180) % 360)
                    .build();
        });
    }
}
