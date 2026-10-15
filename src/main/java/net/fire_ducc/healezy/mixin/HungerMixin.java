package net.fire_ducc.healezy.mixin;

import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.world.food.FoodData")
public abstract class HungerMixin {

    @Redirect(
            method = "tick(Lnet/minecraft/server/level/ServerPlayer;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerPlayer;heal(F)V",
                    ordinal = 0
            )
    )
    private void disableFastFoodRegen(ServerPlayer player, float amount) {}

    @Redirect(
            method = "tick(Lnet/minecraft/server/level/ServerPlayer;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerPlayer;heal(F)V",
                    ordinal = 1
            )
    )
    private void disableSlowFoodRegen(ServerPlayer player, float amount) {}
}
