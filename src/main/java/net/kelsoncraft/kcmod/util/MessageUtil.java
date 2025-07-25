package net.kelsoncraft.kcmod.util;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import toni.immersivemessages.api.ImmersiveMessage;

public class MessageUtil {

    //---------
    // Java specific, these should work anywhere outside of Minecraft.
    //---------

    /**
     * Very simple implementation to print to the console in java, instead of typing out 'System.out.println' everytime.
     * @param text The text to display in the console.
     */
    public static void printToConsole(String text) {
        System.out.println(text);
    }

    //---------

    //---------
    // Immersive Message API
    //---------

    // TODO Figure out how to clear these messages, if I run a bunch of commands they always wait a minute before running the next one.
//    public static void ClearMessage(){
//
//    }

    // The time for the messages to stay on the screen.
    private static final float messageTime = 1.0f;

    /**
     * Display a message on screen, with the Immersive Messages API
     * @param player The player to send the message to.
     * @param text The text to display.
     */
    public static void SendMessage(ServerPlayer player, String text) {
        var message = ImmersiveMessage.builder(messageTime, text);
        var styledMessage = ImmersiveMessageApi.goldText(message);

        styledMessage.sendServer(player);
    }

    //
    // https://immersive.txni.dev/presets
    //

    /**
     * Show a message in the top left corner as a toast.
     * @param player The player to send the message to.
     * @param text The text to display.
     * @param subtitle The subtitle to display
     */
    public static void SendToastMessage(ServerPlayer player, String text, String subtitle) {
        var message = ImmersiveMessage.toast(messageTime, text, subtitle);

        message.sendServer(player);
    }

    /**
     * Show a message above the hotbar, as a popup.
     * @param player The player to send the message to.
     * @param text The text to display.
     * @param subtitle The subtitle to display
     */
    public static void SendPopupMessage(ServerPlayer player, String text, String subtitle) {
        var message = ImmersiveMessage.popup(messageTime, text, subtitle);

        message.sendServer(player);
    }


    //----------
    // Vanilla messages
    //---------

    /**
     *
     * @param source Source to display chat message to.
     * @param text The text to display.
     */
    public static void SendChatMessage(CommandSourceStack source, String text) {
        source.sendSystemMessage(Component.literal(text));
    }

    /**
     * Send a colored message to the player using my enum.
     * @param source Source to display chat message to.
     * @param text The text to display.
     * @param chatColor The enum in use from here {@link ChatColors}
     */
    public static void sendColorMessage(CommandSourceStack source, String text, ChatColors chatColor) {

        ChatFormatting formatting = chatColor.getFormatting();
        source.sendSystemMessage(kcMessageBuilder(text, formatting));
    }

    // I may use this later.
//    private Component messageBuilder(MutableComponent text) {
//        return Component.literal(text.toString());
//    }

    //----
    // Extras

    /**
     * A basic message with my '[KCNet]' prefix
     * @param text The text to display.
     * @return The message with the '[KCNet]' prefix, such as '[KCNet]: Test message'
     */
    private static Component kcMessageBuilder(String text, ChatFormatting chatColor) {
        // For the prefix
        MutableComponent prefixComponent = Component.literal(Messages.KCNetMain).withStyle(ChatFormatting.GOLD);

        // For the main text
        MutableComponent mainTextComponent = Component.literal(text).withStyle(chatColor);

        // Append the main text component to the prefix.
        // This correctly combines them without overriding styles.
        return prefixComponent.append(mainTextComponent);
    }
    //----


}
