package com.nonamemods.esp.render;
import com.nonamemods.esp.config.EspConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.DrawStyle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.*;
import net.minecraft.world.debug.gizmo.GizmoDrawing;
public final class EspRenderer {
 private EspRenderer(){}
 public static void collect(){
  EspConfig c=EspConfig.get(); MinecraftClient mc=MinecraftClient.getInstance(); if(!c.enabled||mc.world==null||mc.player==null)return;
  Vec3d cam=mc.gameRenderer.getCamera().getPos(); double max=(double)c.entityRadius*c.entityRadius;
  if(c.entities||c.players) for(Entity e:mc.world.getEntities()){
   if(e==mc.player||e.isRemoved()||e.squaredDistanceTo(cam)>max)continue;
   String id=Registries.ENTITY_TYPE.getId(e.getType()).toString();
   boolean ok=e instanceof PlayerEntity?c.players:(c.entities&&(c.entityTypes.isEmpty()||c.entityTypes.contains(id))); if(!ok)continue;
   DrawStyle s=DrawStyle.filledAndStroked(c.color,1.5f,(c.color&0x00FFFFFF)|((int)(c.opacity*255)<<24)); GizmoDrawing.box(e.getBoundingBox(),s).ignoreOcclusion();
   if(c.tracers)GizmoDrawing.line(cam,e.getPos().add(0,e.getHeight()*0.5,0),c.color,1.5f).ignoreOcclusion();
   if(c.beacons)GizmoDrawing.line(e.getPos(),e.getPos().add(0,96,0),c.color,2f).ignoreOcclusion();
  }
  if(c.blocks&&!c.blockTypes.isEmpty()){
   int r=Math.min(c.blockRadius,48); BlockPos p=mc.player.getBlockPos();
   for(BlockPos b:BlockPos.iterate(p.add(-r,-r,-r),p.add(r,r,r))) if(c.blockTypes.contains(Registries.BLOCK.getId(mc.world.getBlockState(b).getBlock()).toString())){
    DrawStyle s=DrawStyle.filledAndStroked(c.blockColor,1.2f,(c.blockColor&0x00FFFFFF)|((int)(c.opacity*255)<<24)); GizmoDrawing.box(b,s).ignoreOcclusion();
    if(c.tracers)GizmoDrawing.line(cam,Vec3d.ofCenter(b),c.blockColor,1.2f).ignoreOcclusion();
    if(c.beacons)GizmoDrawing.line(Vec3d.ofCenter(b),Vec3d.ofCenter(b).add(0,96,0),c.blockColor,2f).ignoreOcclusion();
   }
  }
 }
}
