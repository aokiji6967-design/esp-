#!/bin/sh
set -eu
GRADLE_VERSION=8.14.3
DIST="$HOME/.gradle/esp-wrapper/gradle-${GRADLE_VERSION}-bin.zip"
DIR="$HOME/.gradle/esp-wrapper/gradle-${GRADLE_VERSION}"
if [ ! -x "$DIR/bin/gradle" ]; then
  mkdir -p "$HOME/.gradle/esp-wrapper"
  if [ ! -f "$DIST" ]; then curl -fsSL "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" -o "$DIST"; fi
  rm -rf "$DIR.tmp"; mkdir -p "$DIR.tmp"; unzip -q "$DIST" -d "$DIR.tmp"; mv "$DIR.tmp/gradle-${GRADLE_VERSION}" "$DIR"; rmdir "$DIR.tmp"
fi
exec "$DIR/bin/gradle" "$@"
