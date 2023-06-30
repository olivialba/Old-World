package net.alba.oldworld.util;

import net.minecraft.nbt.NbtCompound;

public class SpellIndexData {
    public static int addIndex(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int index = nbt.getInt("spell_index");

        if (index + amount >= 5) {
            index = 5;
        } 
        else if (index + amount <= 1) {
            index = 1;
        } 
        else {
            index += amount;
        }
        nbt.putInt("spell_index", index);
        return index;
    }
}
