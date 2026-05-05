#!/bin/bash

echo "🔥 CampusX - Build and Run Script"
echo "=================================="
echo ""

# Colors
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Step 1: Clean build
echo -e "${BLUE}Step 1: Cleaning project...${NC}"
./gradlew clean
if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Clean failed!${NC}"
    exit 1
fi
echo -e "${GREEN}✅ Clean successful${NC}"
echo ""

# Step 2: Build
echo -e "${BLUE}Step 2: Building project...${NC}"
./gradlew :app:assembleDebug
if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Build failed!${NC}"
    exit 1
fi
echo -e "${GREEN}✅ Build successful${NC}"
echo ""

# Step 3: Check for connected devices
echo -e "${BLUE}Step 3: Checking for connected devices...${NC}"
adb devices
echo ""

# Step 4: Install
echo -e "${BLUE}Step 4: Installing app...${NC}"
./gradlew :app:installDebug
if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Installation failed!${NC}"
    echo "Make sure your device is connected and USB debugging is enabled"
    exit 1
fi
echo -e "${GREEN}✅ Installation successful${NC}"
echo ""

# Step 5: Launch app
echo -e "${BLUE}Step 5: Launching app...${NC}"
adb shell am start -n com.example.campusx/.ui.SplashActivity
if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Launch failed!${NC}"
    exit 1
fi
echo -e "${GREEN}✅ App launched${NC}"
echo ""

echo -e "${GREEN}🎉 Success! CampusX is now running on your device${NC}"
echo ""
echo "📱 To view logs, run:"
echo "   adb logcat | grep CampusX"
echo ""
echo "🔥 Firebase Console:"
echo "   https://console.firebase.google.com/project/campus-43f73"
