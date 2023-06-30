package net.alba.oldworld.util.magic;

import java.util.List;

import net.alba.oldworld.entity.custom.projectiles.BallBasicEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class FireSpells {

    public static void spellBasic(World world, PlayerEntity player) {
        BallBasicEntity projectile = new BallBasicEntity(world, player, MagicUtils.getRotationX(player), MagicUtils.getRotationY(player), MagicUtils.getRotationZ(player), 5, null, true);
        projectile.setPosition(player.getX(), player.getBodyY(0.80D), projectile.getZ());
        projectile.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 2.2F, 1.0F);
        world.spawnEntity(projectile);
        world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, .65f, 1f);
    }
    
    public static void spellFireball(World world, PlayerEntity player) {
        FireballEntity fireBall = new FireballEntity(world, player, MagicUtils.getRotationX(player), MagicUtils.getRotationY(player), MagicUtils.getRotationZ(player), 1);
        fireBall.setPosition(player.getX(), player.getBodyY(0.85D), fireBall.getZ());
        fireBall.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 2.2F, 1.0F);
        world.spawnEntity(fireBall);
        world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, .65f, 1f);
    }

    public static void spellCombustion(World world, PlayerEntity player) {
        BlockPos position = MagicUtils.getEntityPosOrBlockPos(world, player, 40);
        Box boundingBox = new Box(position).expand(4);
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, boundingBox, EntityPredicates.VALID_LIVING_ENTITY);
        if (entities.size() > 0) {
            for (LivingEntity entity : entities) {
                entity.setOnFireFor(6);
            }
        }
        ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME, position.getX() + 0.5, position.getY() + 0.9, position.getZ() + 0.5, 60, 0.5, 0.25, 0.5, 0.2); 
    }

    public static void spellMeteor(World world, PlayerEntity player) {
        BlockPos position = MagicUtils.getBlockPosition(world, player, 35);
        if (position == null) {
            return;
        } 
        FireballEntity fireBall = new FireballEntity(world, player, 0, -90, 0, 4);
        fireBall.setPosition(position.getX(), position.getY() + 35, position.getZ());
        world.spawnEntity(fireBall);
    }
}
