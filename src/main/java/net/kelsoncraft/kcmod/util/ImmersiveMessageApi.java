package net.kelsoncraft.kcmod.util;

import net.minecraft.ChatFormatting;
import toni.immersivemessages.api.ImmersiveMessage;
import toni.immersivemessages.util.ImmersiveColor;

// https://immersive.txni.dev/api

public class ImmersiveMessageApi {

    // Message builder for gold text with a blue background.
    public static ImmersiveMessage goldText(ImmersiveMessage message) {
        return message
                // Moved into here out of MessageTest
                .background()
                .backgroundColor(new ImmersiveColor(0.0f, 91.0f, 255.0f, 0.5f))
                .borderTopColor(32)
                .borderBottomColor(32)
                // TODO Figure these positions out.
//                .x(0.01f)
//                .y(0.01f)
                // Text wrap can be enabled
//                .wrap(100)
                // Enable an animated RGB border.
//                .rainbow()
                // Set the text color
                .color(ChatFormatting.GOLD);
    }
}
