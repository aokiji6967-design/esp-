# ESP — Minecraft 1.21.11 standalone backport

Standalone Fabric client mod targeting Minecraft 1.21.11 / Java 21.

Removed: PlayerAPI, Gemstone ESP, Mineshaft detector, Safari ESP, and Hypixel bestiary/Mob Type ESP.

Included: searchable block/entity selection, player ESP, boxes/outlines, tracers, directional beacons, JSON config, native 1.21.11 GizmoDrawing hook, and `/esp` config commands.

Build on Windows with `.\\gradlew.bat build`, or Linux/macOS with `./gradlew build`.

The compiled remapped JAR is under `build/libs/`.
