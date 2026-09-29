package com.nonamemods.esp.mixin;
import com.nonamemods.esp.render.EspRenderer;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
 @Inject(method="collectGizmos",at=@At("HEAD")) private void esp$collect(CallbackInfo ci){EspRenderer.collect();}
}
