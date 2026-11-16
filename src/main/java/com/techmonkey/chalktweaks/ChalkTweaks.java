package com.techmonkey.chalktweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Makes every Chalk stick (normal and glow, all colors) last 4 times as many marks.
 * Chalk sets 64 uses in code with no setting for it, so this changes the items' default max damage instead.
 */
public final class ChalkTweaks implements ModInitializer {
    public static final String MOD_ID = "chalk_tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final String CHALK_NAMESPACE = "chalk";
    private static final int CHALK_ORIGINAL_USES = 64;
    private static final int DURABILITY_MULTIPLIER = 4;
    private static final int CHALK_NEW_USES = CHALK_ORIGINAL_USES * DURABILITY_MULTIPLIER;

    @Override
    public void onInitialize() {
        DefaultItemComponentEvents.MODIFY.register(context -> context.modify(ChalkTweaks::isChalkStick,
                (components, item) -> components.set(DataComponents.MAX_DAMAGE, CHALK_NEW_USES)));
        LOGGER.info("Chalk Tweaks: chalk sticks last {} marks", CHALK_NEW_USES);
    }

    /** Chalk's sticks are named like white_chalk and white_glow_chalk. Its mark blocks have no items. */
    private static boolean isChalkStick(Item item) {
        Identifier itemId = BuiltInRegistries.ITEM.getKey(item);
        return CHALK_NAMESPACE.equals(itemId.getNamespace()) && itemId.getPath().endsWith("_chalk");
    }
}
