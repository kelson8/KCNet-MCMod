package net.kelsoncraft.neoforgetest.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import toni.immersivemessages.api.ImmersiveMessage;

public class MessageUtil {

    //---------
    // Immersive Message API
    //---------

    // I got this working! Moved out of ImmersiveMessageApi.java.
    // Display a message on screen, not in chat with Immersive Messages API
    public static void SendMessage(ServerPlayer player, String text) {
        var message = ImmersiveMessage.builder(2.0f, text);
        var styledMessage = ImmersiveMessageApi.goldText(message);

        styledMessage.sendServer(player);
    }

    // https://immersive.txni.dev/presets
    // In the top left corner as a toast.
    public static void SendToastMessage(ServerPlayer player, String text, String subtitle) {
        var message = ImmersiveMessage.toast(2.0f, text, subtitle);

        message.sendServer(player);
    }

    // Above the hotbar, as a popup.
    public static void SendPopupMessage(ServerPlayer player, String text, String subtitle) {
        var message = ImmersiveMessage.popup(2.0f, text, subtitle);

        message.sendServer(player);
    }


    //----------
    // Vanilla messages
    //---------

    // I may use this later.
    private Component messageBuilder(MutableComponent text) {
        return Component.literal(text.toString());
    }


}
