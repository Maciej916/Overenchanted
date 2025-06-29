package com.maciej916.overenchanted.client;

import com.maciej916.overenchanted.Overenchanted;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Overenchanted.MOD_ID, value = Dist.CLIENT)
public class ModKeyMappings {
    public static final String CATEGORY = "key.categories." + Overenchanted.MOD_ID;

    public static final KeyMapping KEY_ECHO_SIGHT = new KeyMapping(name("echo_sight"), GLFW.GLFW_KEY_R, CATEGORY);
    public static final KeyMapping KEY_LUMBERJACK = new KeyMapping(name("lumberjack"), GLFW.GLFW_KEY_C, CATEGORY);

    private static String name (String name) {
        return "key." + Overenchanted.MOD_ID + "." + name;
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KEY_ECHO_SIGHT);
        event.register(KEY_LUMBERJACK);
    }
}
