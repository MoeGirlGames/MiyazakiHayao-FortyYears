package games.moegirl.fortyyears.generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import games.moegirl.fortyyears.FortyYears;
import games.moegirl.fortyyears.block.BlockRegistry;
import games.moegirl.fortyyears.entity.EntityRegistry;
import games.moegirl.fortyyears.item.FortyGroup;
import games.moegirl.fortyyears.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Supplier;

public class ProviderLanguage implements IDataProvider {

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private final Map<String, String> dataZh = new TreeMap<>();
    private final Map<String, String> dataEn = new TreeMap<>();
    private final DataGenerator gen;

    public ProviderLanguage(DataGenerator gen) {
        this.gen = gen;
    }

    private void addTranslations() {
        addGroup(FortyGroup.INSTANCE, "四十年", "Forty Years");
        addItem(ItemRegistry.kamikaChicken, "整鸡", "Chicken");
        addItem(ItemRegistry.kamikaMask, "面具", "Mask");
        addItem(ItemRegistry.ponyoGlassJar, "玻璃罐", "Glass Jar");
        addItem(ItemRegistry.ponyoGlassJarWithPonyo, "玻璃罐（波妞）", "Glass Jar (ponyo)");
        addItem(ItemRegistry.ponyoEgg, "波妞蛋", "Ponyo Egg");
        addItem(ItemRegistry.fairydustEgg, "灰尘精灵蛋", "Fairydust Egg");
        addBlock(BlockRegistry.ponyoSearchlight, "探照灯", "Searchlight");
        addEntityType(EntityRegistry.ponyoType, "波妞", "Ponyo");
        addEntityType(EntityRegistry.fairydustType, "灰尘精灵", "Fairydust");
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        addTranslations();
        if (!dataZh.isEmpty())
            save(cache, dataZh, this.gen.getOutputFolder().resolve("assets/" + FortyYears.MODID + "/lang/zh_cn.json"));
        if (!dataEn.isEmpty())
            save(cache, dataEn, this.gen.getOutputFolder().resolve("assets/" + FortyYears.MODID + "/lang/en_us.json"));
    }

    @Override
    public String getName() {
        return "Languages: zh_cn and en_us";
    }

    private void save(DirectoryCache cache, Object object, Path target) throws IOException {
        String data = GSON.toJson(object);
        data = JavaUnicodeEscaper.outsideOf(0, 0x7f).translate(data); // Escape unicode after the fact so that it's not double escaped by GSON
        String hash = IDataProvider.HASH_FUNCTION.hashUnencodedChars(data).toString();
        if (!Objects.equals(cache.getPreviousHash(target), hash) || !Files.exists(target)) {
            Files.createDirectories(target.getParent());

            try (BufferedWriter bufferedwriter = Files.newBufferedWriter(target)) {
                bufferedwriter.write(data);
            }
        }

        cache.recordHash(target, hash);
    }

    public void addBlock(Supplier<? extends Block> key, String nameZh, String nameEn) {
        add(key.get(), nameZh, nameEn);
    }

    public void add(Block key, String nameZh, String nameEn) {
        add(key.getTranslationKey(), nameZh, nameEn);
    }

    public void addItem(Supplier<? extends Item> key, String nameZh, String nameEn) {
        add(key.get(), nameZh, nameEn);
    }

    public void add(Item key, String nameZh, String nameEn) {
        add(key.getTranslationKey(), nameZh, nameEn);
    }

    public void addEntityType(Supplier<? extends EntityType<?>> key, String nameZh, String nameEn) {
        add(key.get(), nameZh, nameEn);
    }

    public void add(EntityType<?> key, String nameZh, String nameEn) {
        add(key.getTranslationKey(), nameZh, nameEn);
    }

    public void addGroup(ItemGroup group, String nameZh, String nameEn) {
        add(((TranslationTextComponent) group.getGroupName()).getKey(), nameZh, nameEn);
    }

    public void add(String key, String nameZh, String nameEn) {
        if (dataZh.put(key, nameZh) != null)
            throw new IllegalStateException("Duplicate translation key " + key + " in zh_cn");
        if (dataEn.put(key, nameEn) != null)
            throw new IllegalStateException("Duplicate translation key " + key + " in en_us");
    }
}
