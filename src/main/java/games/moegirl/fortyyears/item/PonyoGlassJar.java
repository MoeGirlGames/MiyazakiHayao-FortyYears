package games.moegirl.fortyyears.item;

import games.moegirl.fortyyears.item.FortyGroup;
import net.minecraft.item.Item;

public class PonyoGlassJar extends Item {

    public PonyoGlassJar() {
        super(new Properties()
                .maxStackSize(1)
                .group(FortyGroup.INSTANCE));
    }
}
