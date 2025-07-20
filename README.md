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
* Heal: `/heal` - Give yourself max health and food.

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

I'm on part 12
* [NeoForge Modding Tutorial - Minecraft 1.21: Non-Block Blocks | #12](https://www.youtube.com/watch?v=0famOskqo24&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=12)

* [NeoForge 1.21 Mixin Setup](https://www.youtube.com/watch?v=Q5041hErnvA)

---

NeoForge Info:
===========
Thanks to the YouTube playlist guide from [here](https://www.youtube.com/watch?v=yG-oJPR_40w&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=1) 
for a lot of the info that I am using to learn more NeoForge coding.

Every block needs a block states json file, a block model json file, and a block item json file.
Also, it needs a block texture file.

---

**To add crafting recipes:**
* Create a .json file in `resources/data/kcneoforgetest/recipe/`

<details>

<summary> Example for bismuth.json (Make a bismuth block into bismuth ingot) </summary>

This is a shapeless recipe
```json
{
  "type": "minecraft:crafting_shapeless",
  "category": "misc",
  "ingredients": [
    {
      "item": "kcneoforgetest:bismuth_block"
    }
  ],
  "result": {
    "count": 9,
    "id": "kcneoforgetest:bismuth"
  }
}
```
</details>

<details>
<summary> Example for bismuth_block.json (Make 9 bismuth into a bismuth block) </summary>

This is a shaped recipe, it requires a certain setup below, the 'B' in the pattern is where the items go in the crafting grid.
```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "B": {
      "item": "kcneoforgetest:bismuth"
    }
  },
  "pattern": [
    "BBB",
    "BBB",
    "BBB"
  ],
  "result": {
    "count": 1,
    "id": "kcneoforgetest:bismuth_block"
  }
}

```
</details>

---

**To make a block mineable and be able to drop**
* Add the block into the `resources/data/minecraft/tags/block/mineable/` folder

These can be added to a pickaxe.json, axe.json, hoe.json, shovel.json and possibly others.

<details>
<summary> Example for pickaxe.json (For mining blocks with pickaxes and getting drops) </summary>

```json
{
  "replace": false,
  "values": [
    "kcneoforgetest:bismuth_block",
    "kcneoforgetest:bismuth_ore",
    "kcneoforgetest:bismuth_deepslate_ore"
  ]
}
```

</details>

---

**To make a specific tool required for mineable items**

In this folder `resources/data/minecraft/tags/block/`

* Create the files named `needs_diamond_tool.json`, `needs_iron_tool.json`, and `needs_stone_tool.json` .

<details>
<summary> Example for needs_diamond_tool.json </summary>

```json
{
  "replace": false,
  "values": [
    "kcneoforgetest:bismuth_deepslate_ore"
  ]
}
```

</details>

All of these not placed in a needs_tool json above, should be mineable with a wooden pickaxe.

---
**Adding blocks to loot tables for drops**

Check out the examples in `data/kcneoforgetest/loot_table_blocks` 

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
