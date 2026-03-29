package me.roundaround.babiesforever.mixin;

import me.roundaround.babiesforever.BabiesForeverMod;
import net.minecraft.world.entity.animal.frog.Tadpole;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Tadpole.class)
public abstract class TadpoleMixin {
  @Shadow
  private int age;

  @Shadow
  protected abstract int getAge();

  @Inject(method = "setAge", at = @At(value = "HEAD"), cancellable = true)
  public void setAge(int age, CallbackInfo info) {
    Tadpole self = ((Tadpole) (Object) this);
    if (this.getAge() < 0 && self.hasCustomName() && !BabiesForeverMod.isNameBypass(self) && age >= 0) {
      this.age = -1;
      info.cancel();
    }
  }
}
