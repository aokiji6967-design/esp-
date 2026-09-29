package com.nonamemods.esp.screen;
import com.nonamemods.esp.config.EspConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
public final class EspScreen extends Screen {
 private final Screen parent; private TextFieldWidget entitySearch,blockSearch;
 public EspScreen(Screen p){super(Text.literal("ESP Configuration"));parent=p;}
 protected void init(){
  EspConfig c=EspConfig.get(); int l=width/2-180,r=width/2+10,y=48;
  addDrawableChild(CheckboxWidget.builder(Text.literal("ESP enabled"),textRenderer).pos(l,y).checked(c.enabled).callback((w,v)->{c.enabled=v;EspConfig.save();}).build());
  addDrawableChild(CheckboxWidget.builder(Text.literal("Player ESP"),textRenderer).pos(l,y+28).checked(c.players).callback((w,v)->{c.players=v;EspConfig.save();}).build());
  addDrawableChild(CheckboxWidget.builder(Text.literal("Entity ESP"),textRenderer).pos(l,y+56).checked(c.entities).callback((w,v)->{c.entities=v;EspConfig.save();}).build());
  addDrawableChild(CheckboxWidget.builder(Text.literal("Block ESP"),textRenderer).pos(l,y+84).checked(c.blocks).callback((w,v)->{c.blocks=v;EspConfig.save();}).build());
  addDrawableChild(CheckboxWidget.builder(Text.literal("Boxes / outlines"),textRenderer).pos(l,y+112).checked(c.boxes).callback((w,v)->{c.boxes=v;EspConfig.save();}).build());
  addDrawableChild(CheckboxWidget.builder(Text.literal("Tracers"),textRenderer).pos(l,y+140).checked(c.tracers).callback((w,v)->{c.tracers=v;EspConfig.save();}).build());
  addDrawableChild(CheckboxWidget.builder(Text.literal("Directional beacons"),textRenderer).pos(l,y+168).checked(c.beacons).callback((w,v)->{c.beacons=v;EspConfig.save();}).build());
  entitySearch=new TextFieldWidget(textRenderer,r,48,170,20,Text.literal("Entity registry id")); addDrawableChild(entitySearch);
  blockSearch=new TextFieldWidget(textRenderer,r,132,170,20,Text.literal("Block registry id")); addDrawableChild(blockSearch);
  addDrawableChild(ButtonWidget.builder(Text.literal("Add entity"),b->addEntity()).dimensions(r,74,170,20).build());
  addDrawableChild(ButtonWidget.builder(Text.literal("Add block"),b->addBlock()).dimensions(r,158,170,20).build());
  addDrawableChild(ButtonWidget.builder(Text.literal("Clear selections"),b->{c.entityTypes.clear();c.blockTypes.clear();EspConfig.save();}).dimensions(r,190,170,20).build());
  addDrawableChild(ButtonWidget.builder(Text.literal("Save & close"),b->close()).dimensions(width/2-80,height-40,160,20).build());
 }
 private void addEntity(){String q=entitySearch.getText().trim().toLowerCase();if(q.isEmpty())return;for(var e:Registries.ENTITY_TYPE.getEntrySet()){String id=Registries.ENTITY_TYPE.getId(e.getValue()).toString();if(id.contains(q)){EspConfig.get().entityTypes.add(id);break;}}EspConfig.save();}
 private void addBlock(){String q=blockSearch.getText().trim().toLowerCase();if(q.isEmpty())return;for(var e:Registries.BLOCK.getEntrySet()){String id=Registries.BLOCK.getId(e.getValue()).toString();if(id.contains(q)){EspConfig.get().blockTypes.add(id);break;}}EspConfig.save();}
 private void closeScreen(){EspConfig.save();MinecraftClient.getInstance().setScreen(parent);}
 public void close(){closeScreen();}
 public void render(DrawContext ctx,int mx,int my,float d){renderBackground(ctx,mx,my,d);ctx.drawCenteredTextWithShadow(textRenderer,title,width/2,18,0xFFFFFF);ctx.drawTextWithShadow(textRenderer,Text.literal("Selected entities: "+EspConfig.get().entityTypes.size()),width/2+10,218,0xBBBBBB);ctx.drawTextWithShadow(textRenderer,Text.literal("Selected blocks: "+EspConfig.get().blockTypes.size()),width/2+10,234,0xBBBBBB);super.render(ctx,mx,my,d);}
}
