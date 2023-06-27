package net.alba.oldworld.item.custom.staffs;

import net.alba.oldworld.entity.custom.projectiles.BasicProjectileEntity;
import net.alba.oldworld.item.custom.tools.MagicItems;
import net.alba.oldworld.magic.MagicCalc;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class FrostStaff extends MagicItems{
    final private float damage = 6F;
    final private StatusEffectInstance status = new StatusEffectInstance(StatusEffects.SLOWNESS, 120);

    public FrostStaff(Settings settings) {
        super(settings);
    }

    @Override
    public void rightClick(World world, PlayerEntity player, ItemStack stack, Hand hand) {
        BasicProjectileEntity projectile = new BasicProjectileEntity(world, player, MagicCalc.getRotationX(player), MagicCalc.getRotationY(player), MagicCalc.getRotationZ(player), damage, status, false);
        projectile.setPosition(player.getX(), player.getBodyY(0.85D), projectile.getZ());
        projectile.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 2.2F, 1.0F);
        world.spawnEntity(projectile);
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_SHOOT;
    }

    @Override
    public String setToolTipString() {
        return "item.oldworld.frost_staff.tooltip";
    }
}
