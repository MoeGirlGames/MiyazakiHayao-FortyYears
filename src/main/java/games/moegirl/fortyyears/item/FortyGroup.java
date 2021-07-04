package games.moegirl.fortyyears.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class FortyGroup extends ItemGroup {

    public static final ItemGroup INSTANCE = new FortyGroup();

    public FortyGroup() {
        super("fortyyears.item_group");
    }

    @Override
    public ItemStack createIcon() {
        // todo select icon
        return ItemStack.EMPTY;
    }
}
