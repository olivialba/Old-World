package net.alba.oldworld.item.custom.grimoires;

import java.util.List;

import net.alba.oldworld.item.custom.MagicItems;
import net.alba.oldworld.util.IEntityDataSaver;
import net.alba.oldworld.util.SpellIndexData;
import net.alba.oldworld.util.magic.SpellsMap;
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
        NbtCompound grimoireSpellNbt = stack.getNbt().getCompound("oldworld.spells");

        if (stack.getItem() == this && (grimoireSpellNbt != null)) {
            int spellSelection = getSpellIndexFromPlayer(player);
            String spellTag = "spell" + spellSelection;

            if (grimoireSpellNbt.contains(spellTag)) {
                String spellKey = grimoireSpellNbt.getString(spellTag);
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

    private static int getSpellIndexFromPlayer(PlayerEntity player) {
        int index = ((IEntityDataSaver) player).getPersistentData().getInt("spell_index");
        if (index == 0) {
            SpellIndexData.addIndex(((IEntityDataSaver) player), 1);
            return 1;
        }
        return index;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("item.oldworld.grimoire_basic.tooltip_1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.oldworld.grimoire_basic.tooltip_2").formatted(Formatting.GRAY));
        //tooltip.add(Text.translatable("item.oldworld.grimoire_basic.tooltip_3", 0).formatted(Formatting.GRAY));
    }

    @Override
    public int getCooldown() {
        return 10;
    }
}
