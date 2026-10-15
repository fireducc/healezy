package net.fire_ducc.healezy.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(FoodData.class)
public abstract class HungerMixin {
//    @Overwrite
//    public boolean canFoodHeal() {return false;}
}
