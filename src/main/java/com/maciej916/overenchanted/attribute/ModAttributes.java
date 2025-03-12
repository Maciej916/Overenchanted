package com.maciej916.overenchanted.attribute;

import com.maciej916.overenchanted.Overenchanted;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Overenchanted.MOD_ID);

    public static final Holder<Attribute> REINFORCED_TIP = ATTRIBUTES.register("reinforced_tip", () -> new RangedAttribute("attribute.reinforced_tip", 0.0, 0.0, 1024.0D).setSyncable(true));


    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
