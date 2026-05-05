#!/bin/bash
# Script to capture crash logs from Android device

echo "=== Clearing old logs ==="
adb logcat -c

echo ""
echo "=== Waiting for crash (run your app now) ==="
echo "Press Ctrl+C after the crash occurs"
echo ""

# Capture logs with filters for crashes
adb logcat -v time AndroidRuntime:E *:E | grep -A 50 "FATAL EXCEPTION"
