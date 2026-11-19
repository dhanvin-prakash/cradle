#!/usr/bin/env bash
# Installs the APK on the running emulator and screenshots every tab in light and dark mode.
set -euo pipefail

APK="$1"
OUT="$2"
PACKAGE="com.example.cradle"

mkdir -p "$OUT"
adb install -r "$APK"

# Clean, consistent status bar.
adb shell settings put global sysui_demo_allowed 1
adb shell am broadcast -a com.android.systemui.demo -e command enter
adb shell am broadcast -a com.android.systemui.demo -e command clock -e hhmm 0941
adb shell am broadcast -a com.android.systemui.demo -e command battery -e level 100 -e plugged false
adb shell am broadcast -a com.android.systemui.demo -e command network -e wifi show -e level 4
adb shell am broadcast -a com.android.systemui.demo -e command notifications -e visible false

for mode in light dark; do
  if [ "$mode" = dark ]; then adb shell cmd uimode night yes; else adb shell cmd uimode night no; fi
  sleep 2
  for tab in status updates visit careteam; do
    adb shell am force-stop "$PACKAGE"
    adb shell am start -W -n "$PACKAGE/.MainActivity" --es initialTab "$tab"
    sleep 6
    adb exec-out screencap -p > "$OUT/${mode}-${tab}.png"
  done
done

# Fail loudly if the app crashed at any point.
if adb logcat -d -b crash | grep -q "$PACKAGE"; then
  adb logcat -d -b crash
  exit 1
fi

ls -la "$OUT"
