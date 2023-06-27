package net.alba.oldworld.item.custom.staffs;

import net.alba.oldworld.item.custom.tools.MagicItems;
import net.alba.oldworld.magic.MagicCalc;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CrystalStaff extends MagicItems{
    //final private float damage = 6F;

    public CrystalStaff(Settings settings) {
        super(settings);
        
    }

    @Override
    public void rightClick(World world, PlayerEntity player, ItemStack stack, Hand hand) {
        BlockPos blockP = MagicCalc.getBlockPosition(world, player, 60);

        BlockState blockState = world.getBlockState(blockP);
        if (blockState.isAir()) {
            return;
        }

        LivingEntity targetEntity = MagicCalc.getNearestLivingEntity(blockP, world, 6);

        if (targetEntity != null) {
            spawnEntityNear(targetEntity, 3, world);
        }

        /**
        CrystalProjectileEntity projectile = new CrystalProjectileEntity(world, player, MagicCalc.getRotationX(player), MagicCalc.getRotationY(player), MagicCalc.getRotationZ(player), damage, null, false, true);
        projectile.setPosition(player.getX(), player.getBodyY(0.85D), projectile.getZ());
        projectile.setVelocity(player, player.getPitch(), player.getYaw(), 0.3F, 2.2F, 1.0F);
        world.spawnEntity(projectile);
         */
    }

    public static void spawnEntityNear(LivingEntity targetEntity, int entitiesNumber, World world) {
        for (int i = 0; i < entitiesNumber; i++) {

            // Generate random coordinates within the radius
            double offsetX = (Math.random() * 2 - 1) * (6 + 3);
            double offsetY = Math.random() * 6;
            double offsetZ = (Math.random() * 2 - 1) * (6 + 3);

            // Calculate the spawn position relative to the target entity
            Vec3d spawnPos = new Vec3d(
                targetEntity.getX() + offsetX,
                targetEntity.getY() + offsetY,
                targetEntity.getZ() + offsetZ
            );

            // Calculate direction to hit target
            Vec3d directionTarget = targetEntity.getPos().subtract(spawnPos).normalize();

            // Create and spawn the fireball entity
            FireballEntity fireball = new FireballEntity(EntityType.FIREBALL, world);
            fireball.setPosition(spawnPos);
            fireball.setVelocity(directionTarget);
            world.spawnEntity(fireball);
        }
    }

    @Override
    public String setToolTipString() {
        return "item.oldworld.crystal_staff.tooltip";
    }
}
