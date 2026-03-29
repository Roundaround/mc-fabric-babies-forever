package me.roundaround.babiesforever.mixin;

import me.roundaround.babiesforever.BabiesForeverMod;
import net.minecraft.world.entity.AgeableMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AgeableMob.class)
public abstract class AgeableMobMixin {
  @Shadow
  protected int age;

  @Inject(method = "setAge", at = @At(value = "HEAD"), cancellable = true)
  public void setAge(int age, CallbackInfo info) {
    AgeableMob self = ((AgeableMob) (Object) this);
    if (self.getAge() < 0 && self.hasCustomName() && !BabiesForeverMod.isNameBypass(self) && age >= 0) {
      this.age = -1;
      info.cancel();
    }
  }
}
