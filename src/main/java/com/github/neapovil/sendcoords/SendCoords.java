package com.github.neapovil.sendcoords;

import org.lwjgl.glfw.GLFW;

import com.github.neapovil.sendcoords.config.ModConfig;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.BlockPos;

public class SendCoords implements ClientModInitializer
{
    private KeyBinding keyBinding;
    private ModConfig modConfig;

    @Override
    public void onInitializeClient()
    {
        this.keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Send Coords",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "Send Coords"));

        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);

        this.modConfig = (ModConfig) AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null)
            {
                return;
            }

            final BlockPos pos = client.player.getBlockPos();

            while (keyBinding.wasPressed())
            {
                client.player.sendChatMessage(this.modConfig.commandPrefix + " " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
            }
        });
    }
}
