package net.kelsoncraft.neoforgetest.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public class VillagerMixin {

    // Misc villager testing with Mixins.
    // TODO Test this later, not sure if they'll work.


    // I wonder what this would do?
//    @Inject(method = "setUnhappy", at = @At("HEAD"), cancellable = true)
//    public void setVillagerUnhappy(CallbackInfo ci) {
//        ci.cancel();
//    }
//
//    // This should make the villagers auto restock their trades.
//    @Inject(method = "shouldRestock", at = @At("HEAD"), cancellable = true)
//    public void shouldVillagerRestock(CallbackInfoReturnable<Boolean> cir) {
//        if(cir.getReturnValue()) {
//            cir.setReturnValue(true);
//        }
//    }

    // Disable the Villager turning into a witch when hit with lightning
    // TODO Fix this to work

    @Inject(method = "thunderHit", at = @At("HEAD"))
    public void disableVillagerWitchLightning(ServerLevel level, LightningBolt lightning, CallbackInfo ci) {

        // Well these work, this may work on other mobs also.
//        lightning.setDamage(200.0f);
//        lightning.kill();


            // Well they just cant die with this, I guess I fully canceled it out, oops.
//        if(!ci.isCancelled()) {
//            ci.cancel();
//        }
//
//        // Hmm, I wonder if this will fix it?
        // This doesn't work if I cancel it.
//        lightning.thunderHit(level, lightning);
    }

//    @Inject(method = "increaseMerchantCareer", at = @At("HEAD"))
//    public void increaseMerchantCareer(CallbackInfo ci){
//
//
//    }

}
