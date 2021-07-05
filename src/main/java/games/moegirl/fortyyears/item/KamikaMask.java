package games.moegirl.fortyyears.item;

import games.moegirl.fortyyears.FortyYears;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import javax.annotation.Nullable;

public class KamikaMask extends ArmorItem {

    public static final IArmorMaterial MATERIAL = new IArmorMaterial() {

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return Integer.MAX_VALUE;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromItems(Items.LEATHER);
        }

        @Override
        public String getName() {
            return FortyYears.MODID + ":mask";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    };

    public KamikaMask() {
        super(MATERIAL, EquipmentSlotType.HEAD, new Properties().maxStackSize(1));
    }

    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
    }
}
