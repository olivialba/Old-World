package net.alba.oldworld.magic;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class SpellsMap {
    private static final Map<String, SpellFunction> spellsMap = new HashMap<>();

    static {
        spellsMap.put("FireBasic", FireSpells::spellBasic);
        spellsMap.put("FireFireball", FireSpells::spellFireball);
        spellsMap.put("FireCombustion", FireSpells::spellCombustion);
        spellsMap.put("FireMeteor", FireSpells::spellMeteor);
    }

    public static Map<String, SpellFunction> getMethodMap() {
        return spellsMap;
    }

    @FunctionalInterface
    public interface SpellFunction {
        void apply(World world, PlayerEntity player);
    }
}
