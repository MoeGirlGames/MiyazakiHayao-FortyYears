package games.moegirl.fortyyears.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class KamikaChinken extends Item {
    private static final Food food = new Food.Builder()
            .saturation(10)
            .hunger(20)
            .build();

    public KamikaChinken() {
        super(new Item.Properties().food(food).group(ItemGroup.FOOD));
    }
}
