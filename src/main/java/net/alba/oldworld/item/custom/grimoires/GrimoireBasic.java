package net.alba.oldworld.item.custom.grimoires;

import java.util.List;

import net.alba.oldworld.item.custom.tools.MagicItems;
import net.alba.oldworld.magic.SpellsMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GrimoireBasic extends MagicItems {
    private static final String Error = "Error: can't find spell in the spellMap, SPELL: ";

    public GrimoireBasic(Settings settings) {
        super(settings);
    }

    @Override
    public void rightClick(World world, PlayerEntity player, ItemStack stack, Hand hand) {
        NbtCompound tag = stack.getNbt();

        if (stack.getItem() == this && tag != null) {
            String spellTag = "spell" + 1;

            if (tag.contains(spellTag)) {
                String spellKey = tag.getString(spellTag);
                SpellsMap.SpellFunction method = SpellsMap.getMethodMap().get(spellKey);
                
                if (method != null) {
                    method.apply(world, player);
                }
                else {
                    player.sendMessage(Text.literal(Error + spellKey));
                }
            }
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("item.oldworld.grimoire_basic.tooltip_1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.oldworld.grimoire_basic.tooltip_2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.oldworld.grimoire_basic.tooltip_3", 0).formatted(Formatting.GRAY));
    }

    @Override
    public int getCooldown() {
        return 40;
    }
}
