package me.roundaround.babiesforever.mixin;

import me.roundaround.babiesforever.BabiesForeverMod;
import net.minecraft.entity.passive.PassiveEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PassiveEntity.class)
public abstract class PassiveEntityMixin {
  @Shadow
  protected int breedingAge;

  @Inject(method = "setBreedingAge", at = @At(value = "HEAD"), cancellable = true)
  public void setBreedingAge(int age, CallbackInfo info) {
    PassiveEntity self = ((PassiveEntity) (Object) this);
    if (self.getBreedingAge() < 0 && self.hasCustomName() && !BabiesForeverMod.isNameBypass(self) &&
        age >= 0) {
      this.breedingAge = -1;
      info.cancel();
    }
  }
}
