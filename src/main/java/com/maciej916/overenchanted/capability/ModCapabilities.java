package com.maciej916.overenchanted.capability;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.capability.impl.IRicochetArrowCapability;
import com.maciej916.overenchanted.capability.impl.RicochetArrowCapabilityProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = Overenchanted.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModCapabilities {

    public static final EntityCapability<IRicochetArrowCapability,Void> RICOCHET_ARROW = EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "ricochet_arrow"), IRicochetArrowCapability.class);

    @SubscribeEvent
    private static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerEntity(RICOCHET_ARROW, EntityType.ARROW, new RicochetArrowCapabilityProvider());
    }
}