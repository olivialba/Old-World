package net.alba.oldworld.item.custom.grimoires;

import java.util.List;
import net.alba.oldworld.item.custom.MagicItems;
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

        if (grimoireSpellNbt != null) {
            String spellTag = "spell" + SpellIndexData.getOrCreateIndex(stack.getNbt());

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
    
    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        NbtCompound grimoireNbt = stack.getOrCreateNbt();

        if (!grimoireNbt.contains("SpellIndex", 1)) {
            grimoireNbt.putByte("SpellIndex", (byte)1);
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("item.oldworld.grimoire_basic.tooltip_1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.oldworld.grimoire_basic.tooltip_2").formatted(Formatting.GRAY));
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }
}
