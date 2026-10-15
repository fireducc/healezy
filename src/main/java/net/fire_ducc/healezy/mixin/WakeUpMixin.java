package net.fire_ducc.healezy.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class WakeUpMixin {

    @Inject(method = "stopSleeping", at = @At("TAIL"))
    private void onWakeUp(CallbackInfo ci) {
        Player player = (Player) (Object) this;

        // Only apply the effect on the server side
        if (!player.level().isClientSide()) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.INSTANT_HEALTH,
                    1,
                    0
            ));
        }
    }
}