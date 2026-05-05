# 🔥 Enable Firebase Services - Quick Guide

## ✅ Your Firebase Project

- **Project ID**: campus-43f73
- **Project Number**: 1074843112321
- **Console**: https://console.firebase.google.com/project/campus-43f73

---

## 🚀 Enable Services (3 Steps - 5 Minutes)

### Step 1: Enable Authentication (2 minutes)

1. **Go to Authentication**:
   https://console.firebase.google.com/project/campus-43f73/authentication/providers

2. **Click "Get started"** (if first time)

3. **Click "Email/Password"** row

4. **Toggle "Enable" switch** to ON

5. **Click "Save"**

✅ **Done!** Authentication is now enabled.

---

### Step 2: Enable Firestore Database (2 minutes)

1. **Go to Firestore**:
   https://console.firebase.google.com/project/campus-43f73/firestore

2. **Click "Create database"**

3. **Select "Start in test mode"**
   - This allows read/write access for development
   - We'll update rules later for production

4. **Click "Next"**

5. **Choose location**: Select **us-central** (or closest to you)

6. **Click "Enable"**

7. **Wait 30-60 seconds** for database creation

✅ **Done!** Firestore is now enabled.

---

### Step 3: Enable Firebase Storage (1 minute)

1. **Go to Storage**:
   https://console.firebase.google.com/project/campus-43f73/storage

2. **Click "Get started"**

3. **Select "Start in test mode"**
   - This allows read/write access for development

4. **Click "Next"**

5. **Click "Done"**

✅ **Done!** Storage is now enabled.

---

## 🎯 Verify Setup

After enabling all services, you should see:

### Authentication Tab:
- Status: **Enabled**
- Sign-in methods: **Email/Password** (Enabled)

### Firestore Tab:
- Status: **Enabled**
- Mode: **Test mode**
- Collections: (empty - will populate when you use the app)

### Storage Tab:
- Status: **Enabled**
- Mode: **Test mode**
- Files: (empty - will populate when you upload images)

---

## 🔒 Security Rules (Already Set)

Your services are in **test mode** which is perfect for development.

### Current Firestore Rules:
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      allow read, write: if request.time < timestamp.date(2026, 12, 31);
    }
  }
}
```

### Current Storage Rules:
```javascript
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /{allPaths=**} {
      allow read, write: if request.time < timestamp.date(2026, 12, 31);
    }
  }
}
```

### ⚠️ Before Production:

Update rules to secure your data. Copy these production rules:

**Firestore Production Rules**:
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

**Storage Production Rules**:
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

## 🏗️ Build and Test

Once all services are enabled:

```bash
# Build the app
./gradlew clean build

# Install on device
./gradlew :app:installDebug

# Or use the script
./build_and_run.sh
```

---

## 🧪 Test Firebase Integration

1. **Open the app**
2. **Swipe through onboarding** (3 pages)
3. **Enter email**: `test@bml.edu.in`
4. **Enter OTP**: `123456` (any 6 digits)
5. **Check Firebase Console**:
   - Go to Authentication → Users
   - You should see: `test@bml.edu.in`
6. **Browse items** in the feed
7. **Check Firestore**:
   - Go to Firestore → Data
   - You should see: `users` collection with your user

---

## 📊 What You'll See

### After First Login:

**Authentication**:
```
Users
└── test@bml.edu.in
    UID: abc123...
    Created: Just now
```

**Firestore**:
```
Collections
└── users
    └── abc123
        ├── email: "test@bml.edu.in"
        ├── name: "test"
        ├── isVerified: true
        └── createdAt: 1234567890
```

### After Creating Items:

**Firestore**:
```
Collections
├── users (1 document)
└── items (new!)
    ├── item1
    │   ├── title: "MacBook Pro"
    │   ├── price: 500
    │   └── ownerId: "abc123"
    └── item2
        ├── title: "Calculator"
        ├── price: 50
        └── ownerId: "abc123"
```

### After Uploading Images:

**Storage**:
```
Files
├── profiles/
│   └── abc123/
│       └── profile.jpg
└── items/
    ├── item1/
    │   └── image_0.jpg
    └── item2/
        └── image_0.jpg
```

---

## 🆘 Troubleshooting

### "Permission denied" in Firestore
**Solution**: Make sure you selected "Start in test mode" when creating the database

### "Permission denied" in Storage
**Solution**: Make sure you selected "Start in test mode" when enabling storage

### "User not created" after registration
**Solution**: 
1. Check Authentication is enabled
2. Check Email/Password provider is enabled
3. Check app logs: `adb logcat | grep CampusX`

### "Build failed"
**Solution**:
```bash
./gradlew clean build
```

---

## ✅ Checklist

- [ ] Authentication enabled (Email/Password)
- [ ] Firestore Database created (test mode)
- [ ] Firebase Storage enabled (test mode)
- [ ] App builds successfully
- [ ] App runs on device
- [ ] User can register with @bml.edu.in email
- [ ] User appears in Firebase Console → Authentication
- [ ] User profile created in Firestore

---

## 🎉 You're Done!

Once all three services are enabled, your app is fully connected to Firebase!

**Next**: Build and run the app with `./build_and_run.sh`

---

## 📞 Quick Links

- **Firebase Console**: https://console.firebase.google.com/project/campus-43f73
- **Authentication**: https://console.firebase.google.com/project/campus-43f73/authentication
- **Firestore**: https://console.firebase.google.com/project/campus-43f73/firestore
- **Storage**: https://console.firebase.google.com/project/campus-43f73/storage
- **Project Settings**: https://console.firebase.google.com/project/campus-43f73/settings/general
