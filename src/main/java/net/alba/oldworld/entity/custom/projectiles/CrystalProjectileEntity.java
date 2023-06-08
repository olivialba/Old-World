package net.alba.oldworld.entity.custom.projectiles;

import net.alba.oldworld.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class CrystalProjectileEntity extends ProjectileEntity {
    private float damage = 0;
    private StatusEffectInstance status = null;

    public CrystalProjectileEntity(EntityType<? extends CrystalProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public CrystalProjectileEntity(World world, LivingEntity owner, double velocityX, double velocityY, double velocityZ, float damage, StatusEffectInstance effect, boolean fire) {
        super(ModEntities.CRYSTAL_PROJECTILE, owner, velocityX, velocityY, velocityZ, world);
        this.damage = damage;
        this.status = effect;
    }

    protected void onCollision(HitResult hitResult) { 
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.discard();
        }
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.world.isClient) {
            return;
        }
        Entity entityHit = entityHitResult.getEntity();
        entityHit.damage(world.getDamageSources().magic(), this.damage);
        if (entityHit instanceof LivingEntity living) {
            if (!(this.status == null)) {
                living.addStatusEffect(this.status);
            }
        }
    }
    
    protected ParticleEffect getParticleType() {
        return ParticleTypes.AMBIENT_ENTITY_EFFECT;
    }
}
