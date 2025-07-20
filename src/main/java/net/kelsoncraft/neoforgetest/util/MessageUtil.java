package net.kelsoncraft.neoforgetest.util;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import toni.immersivemessages.ImmersiveMessages;
import toni.immersivemessages.ImmersiveMessagesCommands;
import toni.immersivemessages.ImmersiveMessagesManager;
import toni.immersivemessages.api.ImmersiveMessage;

public class MessageUtil {

    //---------
    // Immersive Message API
    //---------

    // TODO Figure out how to clear these messages, if I run a bunch of commands they always wait a minute before running the next one.
    public static void ClearMessage(){

    }

    private static final float messageTime = 1.0f;

    // I got this working! Moved out of ImmersiveMessageApi.java.
    // Display a message on screen, not in chat with Immersive Messages API
    public static void SendMessage(ServerPlayer player, String text) {
        var message = ImmersiveMessage.builder(messageTime, text);
        var styledMessage = ImmersiveMessageApi.goldText(message);

        styledMessage.sendServer(player);
    }

    // https://immersive.txni.dev/presets
    // In the top left corner as a toast.
    public static void SendToastMessage(ServerPlayer player, String text, String subtitle) {
        var message = ImmersiveMessage.toast(messageTime, text, subtitle);

        message.sendServer(player);
    }

    // Above the hotbar, as a popup.
    public static void SendPopupMessage(ServerPlayer player, String text, String subtitle) {
        var message = ImmersiveMessage.popup(messageTime, text, subtitle);

        message.sendServer(player);
    }


    //----------
    // Vanilla messages
    //---------

    public static void SendChatMessage(CommandSourceStack source, String text) {
        source.sendSystemMessage(Component.literal(text));
    }


    // I may use this later.
    private Component messageBuilder(MutableComponent text) {
        return Component.literal(text.toString());
    }


}
