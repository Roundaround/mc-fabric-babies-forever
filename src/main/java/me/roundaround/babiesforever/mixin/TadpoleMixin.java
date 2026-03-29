package me.roundaround.babiesforever.mixin;

import me.roundaround.babiesforever.BabiesForeverMod;
import net.minecraft.world.entity.animal.frog.Tadpole;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Tadpole.class)
public abstract class TadpoleMixin {

  @Unique
  private int tadpoleAge;
  @Unique
  private int getTadpoleAge() {
    return 0;
  }

  @Inject(method = "setAge", at = @At(value = "HEAD"), cancellable = true)
  public void setBreedingAge(int age, CallbackInfo info) {
    Tadpole self = ((Tadpole) (Object) this);
    if (self.hasCustomName() && !BabiesForeverMod.isNameBypass(self) &&
        age >= Tadpole.ticksToBeFrog) {
      this.tadpoleAge = Tadpole.ticksToBeFrog - 1;
      info.cancel();
    }
  }
}
