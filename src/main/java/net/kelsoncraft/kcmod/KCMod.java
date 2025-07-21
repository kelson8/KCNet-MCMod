package net.kelsoncraft.kcmod;

import net.kelsoncraft.kcmod.block.ModBlocks;
import net.kelsoncraft.kcmod.commands.GamemodeCommands;
import net.kelsoncraft.kcmod.commands.KCCommands;
import net.kelsoncraft.kcmod.commands.MiscCommands;
import net.kelsoncraft.kcmod.datagen.DataGenerators;
import net.kelsoncraft.kcmod.commands.ModCommands;
import net.kelsoncraft.kcmod.item.ModCreativeModeTabs;
import net.kelsoncraft.kcmod.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Register commands guide
// https://forums.minecraftforge.net/topic/109194-add-commands-in-118/

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(KCMod.MOD_ID)
public class KCMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "kcnet_mod";

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // Store the mod version, will be obtained from the version.properties
    public static String MOD_NAME = "UNKNOWN";    // Default value
    public static String MOD_VERSION = "UNKNOWN"; // Default value
    public static String MOD_DESCRIPTION = "UNKNOWN"; // Default value

    // Static block to load the version on mod startup
    static {
        Properties props = new Properties();
        try (InputStream is = KCMod.class.getClassLoader().getResourceAsStream("version.properties")) {
            if (is != null) {
                props.load(is);
                MOD_VERSION = props.getProperty("project.version", "UNKNOWN");
                MOD_NAME = props.getProperty("project.name", "UNKNOWN");
                MOD_DESCRIPTION = props.getProperty("project.description", "UNKNOWN");

                // This line logs all three properties when the mod class is loaded
                LOGGER.info("Loaded mod properties: Version={}, Name={}", MOD_VERSION, MOD_NAME);
            } else {
                LOGGER.warn("version.properties not found in resources. Mod version/name/description will be UNKNOWN.");
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load version.properties", e);
        }
    }

    /**
     * Register all commands
     */
    private void registerCommands() {
        NeoForge.EVENT_BUS.register(KCCommands.class);
        NeoForge.EVENT_BUS.register(GamemodeCommands.class);
        NeoForge.EVENT_BUS.register(MiscCommands.class);


        NeoForge.EVENT_BUS.register(ModCommands.class);
        LOGGER.info("{} Registered mod commands as an event listener.", KCMod.MOD_NAME);
    }

    /**
     * Register all events
     */
    private void registerEvents() {
        // Oops, I had this disabled in the file, moved to @EventBusSubscriber in EventHandler.
        //
        //---
//        NeoForge.EVENT_BUS.register(EventHandler.class);

    }


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public KCMod(IEventBus modEventBus, ModContainer modContainer) {
        //---
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        //---

        //---
        // Register commands
        registerCommands();

        //---
        // Register events
        registerEvents();



        //---
        // Register data gen
        modEventBus.register(DataGenerators.class);

        // Since the rest of these are only one item being registered for now, I'll leave them in this main function.

        //---
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        //---
        NeoForge.EVENT_BUS.register(this);

        //---
        // Register creative mode tabs
        //---
        ModCreativeModeTabs.register(modEventBus);

        //---
        // Register items
        //---
        ModItems.register(modEventBus);

        //---
        // Register blocks
        ModBlocks.register(modEventBus);
        //---

        //---
        // Register the item to a creative tab
        //---
        modEventBus.addListener(this::addCreative);

        //---
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        //---
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
//        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // TODO Move this into another file.
    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.BISMUTH);
            event.accept(ModItems.RAW_BISMUTH);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.BISMUTH_BLOCK);
            event.accept(ModBlocks.BISMUTH_ORE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
//        LOGGER.info("HELLO from server starting");
//        LOGGER.info(Messages.modName + " KelsonCraft mod server started, Mod Version: {}", NeoForgeTest.MOD_VERSION);
//        NeoForgeTest.LOGGER.info("{} Server test started. Version: {}, Description: {}", NeoForgeTest.MOD_NAME, NeoForgeTest.MOD_VERSION, NeoForgeTest.MOD_DESCRIPTION);
        KCMod.LOGGER.info("{} Server test started. Version: {}", KCMod.MOD_NAME, KCMod.MOD_VERSION);
    }
}
