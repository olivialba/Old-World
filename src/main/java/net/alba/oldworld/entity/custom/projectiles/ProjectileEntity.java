package net.alba.oldworld.entity.custom.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.world.World;

public class ProjectileEntity extends AbstractFireballEntity{

    public ProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ProjectileEntity(EntityType<? extends ProjectileEntity> entityType, LivingEntity owner, double velocityX, double velocityY, double velocityZ, World world) {
        super(entityType, owner, velocityX, velocityY, velocityZ, world);
    }

    protected boolean isBurning() {
        return false;
    }
}
