package net.fire_ducc.healezy.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public abstract class HealthMixin {

    @Shadow
    private int foodLevel;

    @Unique
    private int healezy$regenTick = 0;

    @Inject(method = "tick", at = @At("TAIL"))
    private void passiveRegeneration(ServerPlayer player, CallbackInfo ci) {
        if (player == null) {
            return;
        }

        if (!player.level().getGameRules().get(GameRules.NATURAL_HEALTH_REGENERATION)) {
            return;
        }

        float health = player.getHealth();
        float maxHealth = player.getMaxHealth();

        if (health >= maxHealth) {
            return;
        }

        if (health > maxHealth / 1.665F
                || health < maxHealth / 2.0F) {
            healezy$regenTick++;

            if (healezy$regenTick > (this.foodLevel > 10 ? 100 : 200)) {
                healezy$regenTick = 0;
                player.heal(1.0F);
            }
        }
    }
}
