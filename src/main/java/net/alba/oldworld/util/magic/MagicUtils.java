package net.alba.oldworld.util.magic;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class MagicUtils {


    /** Calculate the block position of the block the player is looking at.
     * If the block is air, return 'null';
     * 
     * @return Block - BlockPos
     */
    public static BlockPos getBlockPosition(World world, PlayerEntity player, double maxDistance) {
        Vec3d direction = player.getRotationVec(1.0F);
        Vec3d[] positions = calculateStartAndEndPositions(player, direction, maxDistance);
        return blockRayCast(world, positions[0], positions[1], player);
    }

    public static BlockPos blockRayCast(World world, Vec3d startPosition, Vec3d endPosition, PlayerEntity player) {
        BlockHitResult hitResult = world.raycast(new RaycastContext(startPosition, endPosition, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));
        BlockPos blockPosition = hitResult.getBlockPos();
        if (world.getBlockState(blockPosition).isAir()) { 
            return null; 
        }
        return blockPosition;
    }

    /** Get the living entity the player is looking at.
     * Can return 'null'.
     * 
     * @return Entity - Living Entity
     */
    public static LivingEntity getEntityLookedAt(World world, PlayerEntity player, double maxDistance) {
        Vec3d direction = player.getRotationVec(1.0F);
        Vec3d[] positions = calculateStartAndEndPositions(player, direction, maxDistance);
        return entityCollision(world, positions[0], positions[1], direction, player, maxDistance);
    }

    public static LivingEntity entityCollision(World world, Vec3d startPosition, Vec3d endPosition, Vec3d direction, PlayerEntity player, double maxDistance) {
        Box boxHit = player.getBoundingBox().stretch(direction.multiply(maxDistance)).expand(1.0D, 1.0D, 1.0D);
        EntityHitResult result = ProjectileUtil.getEntityCollision(world, player, startPosition, endPosition, boxHit, (entityx) -> !entityx.isSpectator() && entityx.canHit());
        if (result == null || !(result.getEntity() instanceof LivingEntity)) {
            return null;
        }
        return (LivingEntity) result.getEntity();
    }

    /** Calculate the position of the entity the player is looking at.
     * If no entity is found, then calculate the position of the block being looked at.
     * 
     * @return Living Entity or Block - BlockPos
     */
    public static BlockPos getEntityPosOrBlockPos(World world, PlayerEntity player, double maxDistance) {
        Vec3d direction = player.getRotationVec(1.0F);
        Vec3d[] positions = calculateStartAndEndPositions(player, direction, maxDistance);
        LivingEntity entity = entityCollision(world, positions[0], positions[1], direction, player, maxDistance);

        if (entity != null) {
            return entity.getBlockPos();
        }
        else {
            return blockRayCast(world, positions[0], positions[1], player);
        }
    }

    // START AND END POSITION
    private static Vec3d[] calculateStartAndEndPositions(PlayerEntity player, Vec3d direction, double maxDistance) {
        Vec3d startPosition = player.getCameraPosVec(1.0F);
        Vec3d endPosition = startPosition.add(direction.multiply(maxDistance));
        return new Vec3d[]{startPosition, endPosition};
    }

    // NEAREST ENTITY
    public static LivingEntity getNearestLivingEntity(BlockPos position, World world, int lengthX, int lengthY, int lengthZ) {
        LivingEntity targetEntity = world.getClosestEntity(world.getEntitiesByClass(LivingEntity.class, new Box(position).expand(lengthX, lengthY, lengthZ), EntityPredicates.VALID_LIVING_ENTITY),
            TargetPredicate.DEFAULT, null, position.getX() + 0.5, position.getY() + 0.5, position.getZ() + 0.5);
        return targetEntity;
    }

    public static LivingEntity getNearestLivingEntity(BlockPos position, World world, int length) {
        return getNearestLivingEntity(position, world, length, length, length);
    }


    // ROTATIONS
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
