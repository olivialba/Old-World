package net.alba.oldworld.magic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class MagicCalc {

    public static BlockPos getBlockPosition(World world, PlayerEntity player, double maxDistance) {
        Vec3d directionBlock = player.getRotationVec(1.0F);
        Vec3d startPositionBlock = player.getCameraPosVec(1.0F);
        Vec3d endPositionBlock = startPositionBlock.add(directionBlock.multiply(maxDistance));

        BlockHitResult hitResult = world.raycast(new RaycastContext(startPositionBlock, endPositionBlock, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));
        BlockPos targetPos = hitResult.getBlockPos();
        
        return targetPos;
    }

    public static Entity getEntityLookedAt(World world, PlayerEntity player, double maxDistance) {
        Vec3d directionEntity = player.getRotationVec(1.0F);
        Vec3d startPositionEntity = player.getCameraPosVec(1.0f);
        Vec3d endPositionEntity = startPositionEntity.add(directionEntity.multiply(maxDistance));
        Box boxHit = player.getBoundingBox().stretch(directionEntity.multiply(maxDistance)).expand(1.0D, 1.0D, 1.0D);
    
        EntityHitResult result = ProjectileUtil.getEntityCollision(world, player, startPositionEntity, endPositionEntity, boxHit, (entityx) -> !entityx.isSpectator() && entityx.canHit());
        if (result == null || result.getEntity() == null) {
            return null;
        }
        return result.getEntity();
    }


    public static double getRotationY(PlayerEntity user) {
        return (user.getY() + user.getRotationVec(0.0F).y * 4.0D) - user.getY();
    }
    
    public static double getRotationX(PlayerEntity user) {
        return (user.getX() + user.getRotationVec(0.0F).x * 4.0D) - user.getX();
    }

    public static double getRotationZ(PlayerEntity user) {
        return (user.getZ() + user.getRotationVec(0.0F).z * 4.0D) - user.getZ();
    }
}
