package net.kelsoncraft.kcmod.item.custom;

import com.blakebr0.cucumber.item.tool.BasePickaxeItem;
import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.util.RandomUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.Unbreakable;

// Credit to Mystical Agriculture for some of this code
// https://github.com/BlakeBr0/MysticalAgriculture/blob/1.21/src/main/java/com/blakebr0/mysticalagriculture/item/tool/EssencePickaxeItem.java

public class CustomPickaxeItem extends BasePickaxeItem {

    RandomUtil randomUtil = new RandomUtil();
    // If this is enabled, you can instantly kill mobs
    boolean instantKillMobs = false;
    // If this is true, it makes it to where this will randomly kill the player for hitting mobs.
    boolean killPlayerRandomly = false;

    /**
     * Make a custom pickaxe tier using the Cucumber library.
     * @param tier The tier to set
     * @param unbreakable If the pickaxe is unbreakable
     */
    public CustomPickaxeItem(Tier tier, boolean unbreakable) {
        super(tier,p -> {

            // This actually makes the tool unbreakable.
            var uses = tier.getUses();
            if(unbreakable) {
                p.component(DataComponents.UNBREAKABLE, new Unbreakable(true));
            }

            p.durability(uses);
            return p;
        });
    }


    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        var success = super.hurtEnemy(stack, target, attacker);

        if(instantKillMobs) {
            // Make this item instantly kill anything it hits.
            target.kill();
        }

        if(killPlayerRandomly) {
            // As an added bonus, make this randomly kill the player.
            // This is pretty much a dice roll, you have a one in 6 chance of dying.
            // I need to randomize this a bit more.
            int randomNumber = randomUtil.generateRandomNumber(6);
            if(randomNumber == 3) {
                attacker.kill();
            }

            KCMod.LOGGER.info("The random number for pickaxe kill level was: {}", randomNumber);
        }

        return success;
    }

}
