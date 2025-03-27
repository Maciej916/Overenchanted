package com.maciej916.overenchanted.data;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.data.impl.PlayerDataAttachment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModDataAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Overenchanted.MOD_ID);

   public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerDataAttachment>> PLAYER_DATA = ATTACHMENT_TYPES.register("player_data_attachment", () -> AttachmentType.serializable(PlayerDataAttachment::new).copyOnDeath().build());

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
