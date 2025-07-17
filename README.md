# KCNeoForge Test Mod

This is really just a test mod on Minecraft for me to mess around with.
It is in an alpha state currently, there isn't much in here so far.

Version info (Running on NeoForge 1.21.1):
* I have other mods and modpacks that work on 1.21.1 so that's
why I decided to use that version.

---

Features:
========

* So far I just have a custom block and item for testing.
* There is mixin testing setup in this project, so far it just has a test that makes your flying speed
faster if you right-click a block.


---

YouTube Tutorials:
==========

YouTube Playlist
* [NeoForge Modding Tutorial - Minecraft 1.21: Getting Started | #1](https://www.youtube.com/watch?v=yG-oJPR_40w&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=1)

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
