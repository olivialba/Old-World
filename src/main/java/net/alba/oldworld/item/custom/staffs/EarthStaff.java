package net.alba.oldworld.item.custom.staffs;

import net.alba.oldworld.entity.custom.projectiles.BasicProjectileEntity;
import net.alba.oldworld.item.custom.StaffWeapons;
import net.alba.oldworld.magic.MagicCalc;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EarthStaff extends StaffWeapons{
    final private float damage = 6F;
    final private StatusEffectInstance status = new StatusEffectInstance(StatusEffects.WEAKNESS, 120);

    public EarthStaff(Settings settings) {
        super(settings);
    }

    @Override
    public void rightClick(World world, PlayerEntity player, ItemStack stack, Hand hand) {
        BasicProjectileEntity projectile = new BasicProjectileEntity(world, player, MagicCalc.getRotationX(player), MagicCalc.getRotationY(player), MagicCalc.getRotationZ(player), damage, status, false);
        projectile.setPosition(player.getX(), player.getBodyY(0.85D), projectile.getZ());
        projectile.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 2.5F, 1.0F);
        world.spawnEntity(projectile);
    }

    @Override
    public String setToolTipString() {
        return "item.oldworld.earth_staff.tooltip";
    }
}