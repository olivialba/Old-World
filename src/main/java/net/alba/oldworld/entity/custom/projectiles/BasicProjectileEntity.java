package net.alba.oldworld.entity.custom.projectiles;

import net.alba.oldworld.entity.ModEntitiesClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BasicProjectileEntity extends MODProjectileEntity{
    private float damage;
    private StatusEffectInstance status;
    private boolean giveFire;

    public BasicProjectileEntity(EntityType<? extends MODProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public BasicProjectileEntity(World world, LivingEntity owner, double velocityX, double velocityY, double velocityZ, float damage, StatusEffectInstance effect, boolean fire) {
        super(ModEntitiesClient.PROJECTILE_THROW, owner, velocityX, velocityY, velocityZ, world);
        this.damage = damage;
        this.status = effect;
        this.giveFire = fire;
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
            if (this.giveFire) {
                living.setFireTicks(120);
            }
        }
    }

    protected ParticleEffect getParticleType() {
        return ParticleTypes.CLOUD;
    }
}
