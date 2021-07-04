package games.moegirl.fortyyears.generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import games.moegirl.fortyyears.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ProviderBlockLoot implements IDataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final Map<ResourceLocation, LootTable.Builder> lootTables = new HashMap<>();
    private final DataGenerator generator;

    public ProviderBlockLoot(DataGenerator generatorIn) {
        generator = generatorIn;
    }

    private void addTables() {
        addBlockDropSelf(BlockRegistry.ponyoSearchlight);
    }

    private void addBlockDropSelf(RegistryObject<Block> block) {
        String name = block.getId().getPath();
        LootPool.Builder pool = LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block.get()));
        lootTables.put(block.getId(), LootTable.builder()
                .setParameterSet(LootParameterSets.BLOCK)
                .addLootPool(pool));
    }

    @Override
    public void act(DirectoryCache cache) {
        Path outputFolder = generator.getOutputFolder();
        addTables();
        lootTables.forEach((key, builder) -> {
            Path path = getPath(outputFolder, key);
            try {
                LootTable lootTable = builder.setParameterSet(LootParameterSets.BLOCK).build();
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    private static Path getPath(Path pathIn, ResourceLocation id) {
        return pathIn.resolve("data/" + id.getNamespace() + "/loot_tables/blocks/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Loot Tables";
    }
}
