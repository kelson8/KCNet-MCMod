# KCNeoForge Test Mod

This is really just a test mod on Minecraft for me to mess around with.
It is in an alpha state currently, there isn't much in here so far.

I have added a few commands and some libraries for this mod.

Version info (Running on NeoForge 1.21.1):
* I have other mods and modpacks that work on 1.21.1 so that's
why I decided to use that version.


---



Features:
========

* I have a custom block and item for testing.
* There is mixin testing setup in this project, so far it just has a test that makes your flying speed
faster if you right-click a block.

---

**Commands:**

**Home commands** (So far these cannot be removed and only one can be set):
* `/home set` - Set your home
* `/home return` - Return back to your home

--

* `/kc pos [x] [y] [z]` - Teleport you to the position specified.
* `/kc version` - Show the mod name, mod version, and mod description in chat.
* `/kc getxp` - Show your current xp levels as a popup on screen.
* `/kc toast` - Show a test toast on screen.
* `/kc popup` - Show a test popup on screen.

--

**Essentials like commands**

**Gamemode commands**

These can be turned off in the mod config, and it should save the data.
To apply changes if in single-player, save and quit then reload the world, 
you don't have to exit the game, or at least I didn't during my testing.
* Creative: `/gmc`, `/creative`, `/gm c`
* Survival: `/gms`, `/survival, /gm s`
* Adventure: `/gma`, `/adventure, /gm a`
* Spectator: `/gmsp`, `/spectator`, `/gm sp`

--

**Other Commands**
* Heal (Give yourself max health and food): `/heal`
* Teleport to world spawn: `/spawn`
* Strike lightning where you are looking: `/lightning`, `/smite`
---

**Test Commands**
* `/summon_mob` - So far just spawns a tamed wolf for the player.

---

Files:
========

This is just a small list of files for the project, I will add other useful files into here later.

**Mixins:**
* `FlySpeedMixin.java` - This is a work in progress for setting the players fly speed, it works, but it cannot be turned off and doesn't set properly.
* `MiscTestMixin.java` - Blank mixin for testing.
* `PutPlayerInSkyMixin.java` - Put the player into the sky (+50 blocks up) if right-clicking with most items, this is disabled.
* `TestMixin.java` - Another mixin just for testing.
* `VillagerMixin.java` - This file is for testing with villagers and mixins.

**Util**
* `MessageUtil.java` - Contains some useful message utilities for my mod.

* `XPUtilities.java` - Get the players xp, level, and add to players XP. Here is the original code for it: [Utilities.java](https://github.com/legobmw99/BetterThanMending/blob/1.21.1/common/src/main/java/com/legobmw99/BetterThanMending/core/util/Utilities.java)

---

YouTube Tutorials:
==========

YouTube Playlist
* [NeoForge Modding Tutorial - Minecraft 1.21: Getting Started | #1](https://www.youtube.com/watch?v=yG-oJPR_40w&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=1)

I'm on part 15
* [NeoForge Modding Tutorial - Minecraft 1.21: Custom Tools | #15](https://www.youtube.com/watch?v=QMIk1k67pnw&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=15)

--
* [NeoForge 1.21 Mixin Setup](https://www.youtube.com/watch?v=Q5041hErnvA)

---

NeoForge Info:
===========
Thanks to the YouTube playlist guide from [here](https://www.youtube.com/watch?v=yG-oJPR_40w&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=1) 
for a lot of the info that I am using to learn more NeoForge coding.

Every block needs a block states json file, a block model json file, and a block item json file.
Also, it needs a block texture file.

---

## Editing datagen

**Generating these files**

* To generate the data gen files, I use IntelliJ and run the gradle task `runData`

**Adding more data generators**

* To add more data generators to this, create one in the `datagen` folder, and add it into `DataGenerators.java`

**Making a block mineable and be able to drop**

* Add the block into `ModBlockTagProvier.java`, under a specific tool such as pickaxe, axe, sword, or hoe.

**To add tags to items**

* Add the item into `ModItemTagProvier.java`

**Adding recipes**

* Add the recipe into `ModRecipeProvider.java`

**Adding foods**

* I need to figure this one out.

**Adding furnace fuels**

Add these into `ModDataMapProvider.java`

**Adding loot tables for blocks and items (For dropping the correct item)**

* Add items/blocks into `ModBlockLootTableProvider.java`

**Adding item models for all blocks and items**

* Add these into `ModItemModelProvider.java`, this is where all items get registered for the inventory menu, shows the texture.

---



Project build info:
=========
If at any point you are missing libraries in your IDE, or you've run into problems you can
run `gradlew --refresh-dependencies` to refresh the local cache. `gradlew clean` to reset everything
{this does not affect your code} and then start the process again.


Additional Resources:
==========


Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/

---

Libraries used:
==========

These below are required for this mod to work, currently they are not in the neoforge.mods.toml so the game may crash without them.
I need to fix that later.
* [Immersive Messages API](https://modrinth.com/mod/immersive-messages-api)
* [TxniLib](https://modrinth.com/mod/txnilib)
* [Sinytra Connector](https://modrinth.com/mod/connector)

---

Credits:
===========

**TheTurkeyDev on GitHub**

I am using some utility classes and files from the ChanceCubes project.

* https://github.com/TheTurkeyDev/ChanceCubes/

Files with code that has been used: `CommandUtil.java`, `EntityUtil.java`, and `SummonMobCommand.java`.

---

**FTB Essentials on GitHub**

I have used the utility class from here [BlockUtil.java](https://github.com/FTBTeam/FTB-Essentials/blob/main/common/src/main/java/dev/ftb/mods/ftbessentials/util/BlockUtil.java).

**legobmw99 on GitHub**

I have used the [Utilities.java](https://github.com/legobmw99/BetterThanMending/blob/1.21.1/common/src/main/java/com/legobmw99/BetterThanMending/core/util/Utilities.java) from their code, it has been renamed to XPUtil.java in my util folder.

---

**Darkhax-Minecraft on GitHub:**

I have used some utility code from the Dark Utilities mod.
* https://github.com/Darkhax-Minecraft/Dark-Utilities

Usages:
* `InvTest.java` - Used some code from here [DarkUtilsCommon.java](https://github.com/Darkhax-Minecraft/Dark-Utilities/blob/1.20.4/common/src/main/java/net/darkhax/darkutilities/DarkUtilsCommon.java)

---

**Textures**
---

**Futureazoo on GitHub:**

Some textures in my textures folder are from this GitHub repo, such as the new foods.
* https://github.com/Futureazoo/TextureRepository

These are licensed under a [Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License](https://creativecommons.org/licenses/by-nc-sa/4.0/).

---

**Tutorials-By-Kaupenjoe on GitHub**

Most main textures in my textures folder are from this GitHub repo
* https://github.com/Tutorials-By-Kaupenjoe/NeoForge-Tutorial-1.21.X/tree/main/src/main/resources/assets/tutorialmod/textures
