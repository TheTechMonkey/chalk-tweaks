package com.techmonkey.chalktweaks.client;

import com.techmonkey.chalktweaks.ChalkTweaks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/** Turns on the built-in pack with the hand-drawn arrow marks. It sits above Chalk's own textures. */
public final class ChalkTweaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricLoader.getInstance().getModContainer(ChalkTweaks.MOD_ID).ifPresent(container -> ResourceLoader.registerBuiltinPack(
                Identifier.fromNamespaceAndPath(ChalkTweaks.MOD_ID, "hand_drawn_arrows"),
                container,
                Component.literal("Hand-drawn Chalk Arrows"),
                PackActivationType.DEFAULT_ENABLED));
    }
}
