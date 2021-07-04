package games.moegirl.fortyyears.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class KamikaChicken extends Item {
    private static final Food food = new Food.Builder()
            .saturation(10)
            .hunger(20)
            .build();

    public KamikaChicken() {
        super(new Item.Properties().food(food).group(FortyGroup.INSTANCE));
    }
}
