package net.alba.oldworld.item.custom;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class MagicItems extends Item{

    public MagicItems(Settings settings) {
        super(settings);
    }

    /**
     * If the user right click with the item.
     * 
     * @param world - world object
     * @param player - player with the staff weapons
     * @param stack - stack with the item
     * @param hand - hand of the player
     */
    public abstract void rightClick(World world, PlayerEntity player, ItemStack stack, Hand hand);

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            player.getItemCooldownManager().set(this, getCooldown());

            if (getSound() != null) {
                world.playSound(null, player.getBlockPos(), getSound(), SoundCategory.PLAYERS, 0.85F, 1.0F);
            }
            rightClick(world, player, stack, hand);
            
            return TypedActionResult.success(stack);
        }
        return TypedActionResult.pass(stack);
    }

    /** 
     * Sound of the staff, called before 'rightClick' method.
     * 
     * @return sound emitted upon staff use
     */
    public SoundEvent getSound() {
        return null;
    }

    /**
     * Setting tooltips for staffs (color GRAY).
     * 
     * @return lang path for tooltip, example: 'item.oldworld.frost_staff.tooltip'
     */
    public String setToolTipString() {
        return null;
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (setToolTipString() == null) {
            return;
        }
        tooltip.add(Text.translatable(setToolTipString()).formatted(Formatting.GRAY));
    }

    /**
     * Get cooldown of the item.
     * 
     * @return return cooldown of the weapon 
     */
    public int getCooldown() {
        return 30;
    }
    
}
