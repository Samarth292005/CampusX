# 🔧 Fix Theme Crash - Quick Solution

## Error:
```
Error inflating class com.google.android.material.button.MaterialButton
The style on this component requires your app theme to be Theme.MaterialComponents
```

## ✅ Quick Fix (In Android Studio)

### Step 1: Clean Project
1. In Android Studio, go to: **Build → Clean Project**
2. Wait for it to finish

### Step 2: Rebuild Project
1. Go to: **Build → Rebuild Project**
2. Wait for it to finish

### Step 3: Invalidate Caches
1. Go to: **File → Invalidate Caches / Restart**
2. Click **"Invalidate and Restart"**
3. Android Studio will restart

### Step 4: Run App
1. Click the **Run** button (green play icon)
2. App should work now!

---

## 🔍 Why This Happens

The error occurs because:
- Android Studio cached old build files
- Theme changes weren't applied properly
- Material Components need Material theme

**Solution**: Clean rebuild forces Android Studio to use the correct theme.

---

## ✅ Verification

After rebuilding, your AndroidManifest.xml should have:

```xml
<!-- Onboarding Activity -->
<activity
    android:name=".ui.onboarding.OnboardingActivity"
    android:theme="@style/Theme.MaterialComponents.DayNight.NoActionBar" />
```

This is **correct** ✅

---

## 🚀 Alternative: Command Line (If Android Studio Doesn't Work)

If you have Java installed:

```bash
# Clean
./gradlew clean

# Rebuild
./gradlew assembleDebug

# Install
./gradlew installDebug
```

---

## 💡 If Still Crashing

### Check themes.xml:

File: `app/src/main/res/values/themes.xml`

Should have:
```xml
<style name="Theme.CampusX" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
```

**NOT**:
```xml
<style name="Theme.CampusX" parent="Theme.AppCompat.Light.DarkActionBar">
```

---

## ✅ Summary

1. **Build → Clean Project**
2. **Build → Rebuild Project**
3. **File → Invalidate Caches / Restart**
4. **Run app**

This should fix the crash! 🎉
