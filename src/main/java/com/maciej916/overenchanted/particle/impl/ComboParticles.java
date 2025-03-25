package com.maciej916.overenchanted.particle.impl;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;

public class ComboParticles extends TextureSheetParticle {
    protected ComboParticles(ClientLevel level, double x, double y, double z, SpriteSet spriteSet, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.setSpriteFromAge(spriteSet);
        this.friction = 0.7F;
        this.gravity = 0.5F;
        this.xd *= 0.1F;
        this.yd *= 0.1F;
        this.zd *= 0.1F;
        this.xd += xSpeed * 0.3;
        this.yd += ySpeed * 0.3;
        this.zd += zSpeed * 0.3;
        this.quadSize *= 0.85F;
        this.lifetime = Math.max((int)(6.0 / (Math.random() * 0.8 + 0.6)), 1);
        this.hasPhysics = false;
        this.tick();
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        return this.quadSize * Mth.clamp(((float)this.age + scaleFactor) / (float)this.lifetime * 32.0F, 0.0F, 1.0F);
    }

    @Override
    public void tick() {
        super.tick();
        this.gCol *= 0.96F;
        this.bCol *= 0.9F;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new ComboParticles(clientLevel, pX, pY, pZ, this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }











//    @OnlyIn(Dist.CLIENT)
//    public static class MobEffectProvider implements ParticleProvider<ColorParticleOption> {
//        private final SpriteSet sprite;
//
//        public MobEffectProvider(SpriteSet sprite) {
//            this.sprite = sprite;
//        }
//
//        public Particle createParticle(
//                ColorParticleOption type,
//                ClientLevel level,
//                double x,
//                double y,
//                double z,
//                double xSpeed,
//                double ySpeed,
//                double zSpeed
//        ) {
//            Particle particle = new SpellParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
//            particle.setColor(type.getRed(), type.getGreen(), type.getBlue());
//            particle.setAlpha(type.getAlpha());
//            return particle;
//        }
//    }
}
