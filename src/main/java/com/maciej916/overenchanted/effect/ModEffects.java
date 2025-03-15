package com.maciej916.overenchanted.effect;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.effect.impl.StunEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Overenchanted.MOD_ID);

    public static final Holder<MobEffect> STUN_EFFECT = MOB_EFFECTS.register("stun", () -> new StunEffect(MobEffectCategory.HARMFUL, 0xFFD700)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "stun"), -0.9f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
