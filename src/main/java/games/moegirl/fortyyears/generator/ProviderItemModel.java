package games.moegirl.fortyyears.generator;

import games.moegirl.fortyyears.FortyYears;
import games.moegirl.fortyyears.item.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class ProviderItemModel extends ItemModelProvider {

    private final ResourceLocation defaultParent = mcLoc("item/generated");

    public ProviderItemModel(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, FortyYears.MODID, helper);
    }

    @Override
    protected void registerModels() {
        ItemRegistry.ITEMS.getEntries().forEach(this::singleTexture);
        ItemRegistry.BLOCKS.getEntries().forEach(this::blockModel);
        ItemRegistry.EGGS.getEntries().forEach(this::eggModel);
    }

    private void singleTexture(RegistryObject<? extends Item> item) {
        String name = item.getId().getPath();
        singleTexture(name, defaultParent, "layer0", modLoc("item/" + name));
    }

    private void blockModel(RegistryObject<? extends Item> item) {
        String name = item.getId().getPath();
        withExistingParent(name, modLoc("block/" + name));
    }

    private void eggModel(RegistryObject<? extends Item> item) {
        withExistingParent(item.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
