package com.nonamemods.esp.config;
import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;
import java.io.*;
import java.nio.file.*;
import java.util.*;
public final class EspConfig {
 public boolean enabled=true, players=true, entities=true, blocks=true, boxes=true, outlines=true, tracers=false, beacons=false;
 public int color=0xFFFF3333, blockColor=0xFF33CCFF, blockRadius=24, entityRadius=64;
 public float opacity=0.28f;
 public final Set<String> entityTypes=new LinkedHashSet<>(), blockTypes=new LinkedHashSet<>();
 private static final Gson GSON=new GsonBuilder().setPrettyPrinting().create();
 private static final Path PATH=FabricLoader.getInstance().getConfigDir().resolve("esp.json");
 private static EspConfig INSTANCE;
 public static EspConfig get(){ if(INSTANCE==null) INSTANCE=load(); return INSTANCE; }
 public static void save(){ if(INSTANCE==null)return; try{Files.createDirectories(PATH.getParent()); try(Writer w=Files.newBufferedWriter(PATH)){GSON.toJson(INSTANCE,w);}}catch(Exception ignored){} }
 private static EspConfig load(){ if(!Files.exists(PATH))return new EspConfig(); try(Reader r=Files.newBufferedReader(PATH)){EspConfig c=GSON.fromJson(r,EspConfig.class);return c==null?new EspConfig():c;}catch(Exception e){return new EspConfig();} }
}
