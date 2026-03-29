package me.roundaround.babiesforever;

import me.roundaround.gradle.api.annotation.Entrypoint;
import net.fabricmc.api.ModInitializer;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.Entity;

import java.util.Locale;

@Entrypoint(Entrypoint.SERVER)
public final class BabiesForeverMod implements ModInitializer {
  public static final String MOD_ID = "babiesforever";

  @Override
  public void onInitialize() {
  }

  public static boolean isNameBypass(Entity entity) {
    if (!entity.hasCustomName()) {
      return false;
    }
    String name = ChatFormatting.stripFormatting(entity.getName().getString()).toLowerCase(Locale.ROOT);
    return "growup".equals(name) || "grow up".equals(name) || "grow_up".equals(name);
  }
}
