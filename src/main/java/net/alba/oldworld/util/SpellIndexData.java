package net.alba.oldworld.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class SpellIndexData {
    public static int addIndex(ItemStack stack, int amount) {
        NbtCompound nbt = stack.getOrCreateNbt();
        int index = getOrCreateIndex(nbt);

        if (index + amount >= 5) {
            index = 5;
        } 
        else if (index + amount <= 1) {
            index = 1;
        } 
        else {
            index += amount;
        }
        nbt.putByte("SpellIndex", (byte)index);
        return index;
    }

    public static byte getOrCreateIndex(NbtCompound nbt) {
        if (!nbt.contains("SpellIndex", 1)) {
            nbt.putByte("SpellIndex", (byte)1);
        }
        return nbt.getByte("SpellIndex");
    }
}
