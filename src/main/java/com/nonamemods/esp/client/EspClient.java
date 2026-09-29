package com.nonamemods.esp.client;
import com.nonamemods.esp.config.EspConfig;
import com.nonamemods.esp.screen.EspScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.*;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
public final class EspClient implements ClientModInitializer {
 public void onInitializeClient(){
  EspConfig.get(); ClientLifecycleEvents.CLIENT_STOPPING.register(c->EspConfig.save());
  ClientCommandRegistrationCallback.EVENT.register((d,r)->d.register(ClientCommandManager.literal("esp").executes(c->open()).then(ClientCommandManager.literal("config").executes(c->open())).then(ClientCommandManager.literal("toggle").executes(c->{EspConfig.get().enabled=!EspConfig.get().enabled;EspConfig.save();return 1;}))));
 }
 private static int open(){MinecraftClient c=MinecraftClient.getInstance();c.execute(()->c.setScreen(new EspScreen(c.currentScreen)));return 1;}
}
