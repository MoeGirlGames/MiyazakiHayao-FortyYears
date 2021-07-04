package games.moegirl.fortyyears.generator;

import games.moegirl.fortyyears.FortyYears;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ProviderBlockModel extends BlockModelProvider {

    public ProviderBlockModel(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, FortyYears.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
