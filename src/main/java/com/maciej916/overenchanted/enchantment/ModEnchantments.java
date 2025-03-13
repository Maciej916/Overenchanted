package com.maciej916.overenchanted.enchantment;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.enchantment.impl.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> BLAZING_EDGE = create("blazing_edge");

    public static final ResourceKey<Enchantment> REINFORCED_TIP = create("reinforced_tip");

    public static final ResourceKey<Enchantment> FREE_RIDING = create("free_riding");


    public static final ResourceKey<Enchantment> FLOATING = create("floating");
    public static final ResourceKey<Enchantment> EXPLOSIVE_SHOT = create("explosive_shot");
    public static final ResourceKey<Enchantment> PARALYSIS = create("paralysis");
    public static final ResourceKey<Enchantment> QUICK_DRAW = create("quick_draw");

    public static final ResourceKey<Enchantment> ENRAGED = create("enraged");
    public static final ResourceKey<Enchantment> BLAZING_WALKER = create("blazing_walker");
    public static final ResourceKey<Enchantment> FLEETFOOT = create("fleetfoot");
    public static final ResourceKey<Enchantment> HIGH_STEP = create("high_step");
    public static final ResourceKey<Enchantment> AQUA_GLIDE = create("aqua_glide");
    public static final ResourceKey<Enchantment> AQUA_MINER = create("aqua_miner");

    public static final ResourceKey<Enchantment> ETERNAL = create("eternal");

    public static final ResourceKey<Enchantment> BREAKING_CURSE = create("breaking_curse");
    public static final ResourceKey<Enchantment> INEFFICIENCY_CURSE = create("inefficiency_curse");
    public static final ResourceKey<Enchantment> IGNORANCE_CURSE = create("ignorance_curse");



    private static ResourceKey<Enchantment> create(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, name));
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        register(context, BLAZING_EDGE, BlazingEdgeEnchantment.builder(context));

        register(context, REINFORCED_TIP, ReinforcedTipEnchantment.builder(context));

        register(context, FREE_RIDING, FreeRidingEnchantment.builder(context));

        register(context, FLOATING, FloatingEnchantment.builder(context));

        register(context, EXPLOSIVE_SHOT, ExplosiveShotEnchantment.builder(context));
        register(context, PARALYSIS, ParalysisEnchantment.builder(context));
//        register(context, QUICK_DRAW, QuickDrawEnchantment.builder(context));

        register(context, ENRAGED, EnragedEnchantment.builder(context));
        register(context, BLAZING_WALKER, BlazingWalkerEnchantment.builder(context));
        register(context, FLEETFOOT, FleetfootEnchantment.builder(context));
        register(context, HIGH_STEP, HighStepEnchantment.builder(context));
        register(context, AQUA_GLIDE, AquaGlideEnchantment.builder(context));
        register(context, AQUA_MINER, AquaMinerEnchantment.builder(context));

        register(context, BREAKING_CURSE, BreakingEnchantment.builder(context));
        register(context, INEFFICIENCY_CURSE, InefficiencyEnchantment.builder(context));
        register(context, IGNORANCE_CURSE, IgnoranceEnchantment.builder(context));

        register(context, ETERNAL, EternalEnchantment.builder(context));




    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}