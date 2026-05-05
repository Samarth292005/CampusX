# 🔥 Set Up Firebase Yourself (5 Minutes)

## ⚠️ Current Status

I've created a **MOCK** `google-services.json` file so your app can compile and run. However, Firebase features **won't work** until you replace it with a real configuration.

**What works now**:
- ✅ App compiles and runs
- ✅ Mock data still works
- ❌ Firebase Authentication won't work
- ❌ Firestore Database won't work
- ❌ Firebase Storage won't work

---

## 🚀 Quick Setup (5 Minutes)

### Step 1: Go to Firebase Console (1 minute)
1. Open: https://console.firebase.google.com/
2. Sign in with your Google account
3. Click **"Add project"** or **"Create a project"**

### Step 2: Create Project (1 minute)
1. **Project name**: Enter `CampusX` (or any name you like)
2. Click **Continue**
3. **Google Analytics**: Toggle OFF (optional, you can enable later)
4. Click **Create project**
5. Wait 30 seconds for project creation
6. Click **Continue**

### Step 3: Add Android App (2 minutes)
1. On the project overview page, click the **Android icon** (</> symbol)
2. **Android package name**: Enter exactly `com.example.campusx`
3. **App nickname**: Enter `CampusX Android` (optional)
4. **Debug signing certificate SHA-1**: Leave blank for now (optional)
5. Click **Register app**

### Step 4: Download google-services.json (30 seconds)
1. Click **Download google-services.json**
2. **IMPORTANT**: Replace the mock file I created
   - Delete: `app/google-services.json` (the mock one)
   - Copy your downloaded file to: `app/google-services.json`
3. Click **Next** → **Next** → **Continue to console**

### Step 5: Enable Firebase Services (1 minute)

#### Enable Authentication:
1. In Firebase Console, click **Authentication** (left sidebar)
2. Click **Get started**
3. Click **Email/Password** tab
4. Toggle **Enable** switch
5. Click **Save**

#### Enable Firestore:
1. Click **Firestore Database** (left sidebar)
2. Click **Create database**
3. Select **Start in test mode** (for development)
4. Click **Next**
5. Choose location: **us-central** (or closest to you)
6. Click **Enable**

#### Enable Storage:
1. Click **Storage** (left sidebar)
2. Click **Get started**
3. Select **Start in test mode**
4. Click **Next**
5. Click **Done**

---

## ✅ Verify Setup

### Build and Run:
```bash
# Sync Gradle
./gradlew clean build

# Install on device
./gradlew :app:installDebug
```

### Test Firebase:
1. Open app
2. Go through onboarding
3. Enter email: `test@bml.edu.in`
4. Enter any 6-digit OTP
5. Check Firebase Console → Authentication → Users (should see new user)

---

## 🎥 Video Tutorial (If You Need Help)

If you get stuck, watch this official Firebase tutorial:
- **Add Firebase to Android**: https://firebase.google.com/docs/android/setup
- **Video**: https://www.youtube.com/watch?v=6LCZmgVadDI

---

## 🆘 Troubleshooting

### "google-services.json not found"
**Solution**: Make sure the file is in `app/` directory (same level as `app/build.gradle`)

### "Default FirebaseApp is not initialized"
**Solution**: 
1. Check file location: `app/google-services.json`
2. Sync Gradle: File → Sync Project with Gradle Files
3. Clean build: Build → Clean Project → Rebuild Project

### "Package name mismatch"
**Solution**: Make sure package name in Firebase Console matches `com.example.campusx`

### "Permission denied" errors
**Solution**: You're in test mode, this shouldn't happen. Check Firestore/Storage rules.

---

## 🔒 Security Rules (Already Set in Test Mode)

Your Firebase is currently in **test mode** which allows anyone to read/write. This is fine for development but **MUST be updated before production**.

### Update Later (Before Production):

**Firestore Rules**:
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /users/{userId} {
      allow read: if true;
      allow write: if request.auth != null && request.auth.uid == userId;
    }
    match /items/{itemId} {
      allow read: if true;
      allow create: if request.auth != null;
      allow update, delete: if request.auth != null && 
                              request.auth.uid == resource.data.ownerId;
    }
    match /bookings/{bookingId} {
      allow read: if request.auth != null && 
                    (request.auth.uid == resource.data.renterId || 
                     request.auth.uid == resource.data.ownerId);
      allow create: if request.auth != null;
      allow update: if request.auth != null;
    }
  }
}
```

**Storage Rules**:
```javascript
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /profiles/{userId}/{allPaths=**} {
      allow read: if true;
      allow write: if request.auth != null && request.auth.uid == userId;
    }
    match /items/{itemId}/{allPaths=**} {
      allow read: if true;
      allow write: if request.auth != null;
    }
  }
}
```

---

## 📊 What You'll See in Firebase Console

After using the app, you'll see:

### Authentication Tab:
```
Users
├── test@bml.edu.in (UID: abc123...)
├── john@bml.edu.in (UID: def456...)
└── jane@bml.edu.in (UID: ghi789...)
```

### Firestore Tab:
```
Collections
├── users
│   ├── abc123 (John's profile)
│   └── def456 (Jane's profile)
├── items
│   ├── item1 (MacBook Pro)
│   ├── item2 (Calculator)
│   └── item3 (Tent)
└── bookings
    ├── booking1 (John rents MacBook)
    └── booking2 (Jane rents Tent)
```

### Storage Tab:
```
Files
├── profiles/
│   ├── abc123/profile.jpg
│   └── def456/profile.jpg
└── items/
    ├── item1/image_0.jpg
    ├── item2/image_0.jpg
    └── item3/image_0.jpg
```

---

## 🎯 Quick Checklist

- [ ] Go to Firebase Console
- [ ] Create project "CampusX"
- [ ] Add Android app (package: com.example.campusx)
- [ ] Download google-services.json
- [ ] Replace mock file with real file
- [ ] Enable Authentication (Email/Password)
- [ ] Enable Firestore (test mode)
- [ ] Enable Storage (test mode)
- [ ] Sync Gradle
- [ ] Build and run app
- [ ] Test authentication
- [ ] Check Firebase Console for data

---

## 💡 Pro Tips

1. **Keep google-services.json secret**: Add to `.gitignore`
2. **Use test mode only for development**: Update rules before production
3. **Monitor usage**: Check Firebase Console → Usage tab
4. **Set up billing alerts**: Prevent unexpected charges
5. **Enable Analytics**: Track user behavior (optional)

---

## 🎉 You're Almost There!

The app is **fully coded and ready**. Just replace the mock `google-services.json` with your real one and you're done!

**Time required**: 5 minutes
**Difficulty**: Easy (just follow the steps)
**Cost**: Free (Firebase Spark plan)

---

## 📞 Need Help?

If you get stuck:
1. Check the troubleshooting section above
2. Read `FIREBASE_SETUP_GUIDE.md` for detailed instructions
3. Watch the official Firebase video tutorial
4. Check Firebase documentation: https://firebase.google.com/docs

Good luck! 🚀
