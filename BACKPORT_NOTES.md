# Backport notes

This package is a standalone refactor rather than a byte-for-byte mechanical downgrade of the upstream source. The upstream ESP repository is currently a Minecraft 26.x client mod requiring PlayerAPI; this package removes that dependency and implements the general ESP feature set directly against Minecraft 1.21.11/Fabric.

The 1.21.11 renderer uses Minecraft's `WorldRenderer.collectGizmos` phase and `GizmoDrawing` APIs. Configuration is stored in the Fabric config directory as `esp.json`.

The environment used to assemble this archive could not reach GitHub/Maven to execute Gradle, so the archive has not been build-verified here. The included GitHub Actions workflow is intended to perform the real dependency resolution/build on GitHub.
