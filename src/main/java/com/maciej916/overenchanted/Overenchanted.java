package com.maciej916.overenchanted;

import com.maciej916.overenchanted.attribute.ModAttributes;
import com.maciej916.overenchanted.block.ModBlocks;
import com.maciej916.overenchanted.data.ModDataAttachments;
import com.maciej916.overenchanted.data.ModDataComponents;
import com.maciej916.overenchanted.effect.ModEffects;
import com.maciej916.overenchanted.enchantment.ModEnchantmentEffects;
import com.maciej916.overenchanted.item.ModItems;
import com.maciej916.overenchanted.particle.ModParticles;
import com.maciej916.overenchanted.particle.impl.ComboParticles;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(Overenchanted.MOD_ID)
public class Overenchanted {
    public static final String MOD_ID = "overenchanted";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Overenchanted(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEffects.register(modEventBus);
        ModAttributes.register(modEventBus);
        ModEnchantmentEffects.register(modEventBus);
        ModDataAttachments.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModParticles.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.COMBO_PARTICLES.get(), ComboParticles.Provider::new);
        }
    }
}
