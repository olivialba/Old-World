package net.alba.oldworld.item.custom.crystals;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class FireCrystal extends Item {
    private static final Text NAME = Text.translatable("item.oldworld.fire_crystal_name");
    private final String tooltipText;
    private final String spellKey;

    public FireCrystal(Settings settings, String tooltipText, String key) {
        super(settings);
        this.tooltipText = tooltipText;
        this.spellKey = key;
    }

    public String getSpellKey() {
        return this.spellKey;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public Text getName(ItemStack stack) {
        return NAME;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(tooltipText).formatted(Formatting.GRAY));
    }
}
