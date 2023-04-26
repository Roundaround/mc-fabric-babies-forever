package me.roundaround.babiesforever.mixin;

import me.roundaround.babiesforever.BabiesForeverMod;
import net.minecraft.entity.passive.TadpoleEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TadpoleEntity.class)
public abstract class TadpoleEntityMixin {
  @Shadow
  private int tadpoleAge;

  @Shadow
  private int getTadpoleAge() {
    return 0;
  }

  @Inject(method = "setTadpoleAge", at = @At(value = "HEAD"), cancellable = true)
  public void setBreedingAge(int age, CallbackInfo info) {
    TadpoleEntity self = ((TadpoleEntity) (Object) this);
    if (self.hasCustomName() && !BabiesForeverMod.isNameBypass(self) &&
        age >= TadpoleEntity.MAX_TADPOLE_AGE) {
      this.tadpoleAge = TadpoleEntity.MAX_TADPOLE_AGE - 1;
      info.cancel();
    }
  }
}
