# 🔥 Populate Firebase Database - Complete Guide

## 🎯 Goal
Add sample items to your Firestore database so your app has data to display.

---

## 📋 Prerequisites

✅ Firebase Authentication enabled  
✅ Firestore Database created (test mode)  
✅ Firebase project: campus-43f73

---

## 🚀 Method 1: Manual (Easiest - 5 minutes)

### Step 1: Open Firestore Console
https://console.firebase.google.com/project/campus-43f73/firestore

### Step 2: Create Collection
1. Click **"Start collection"**
2. Collection ID: `items`
3. Click **"Next"**

### Step 3: Add First Item
**Document ID**: `item1`

**Fields** (click "Add field" for each):

| Field | Type | Value |
|-------|------|-------|
| id | string | item1 |
| ownerId | string | sample_owner_1 |
| ownerName | string | Jane Smith |
| ownerRating | number | 4.8 |
| title | string | MacBook Pro 2021 |
| description | string | 14-inch MacBook Pro with M1 chip, perfect for coding |
| category | string | ELECTRONICS |
| pricePerDay | number | 500 |
| images | array | (click array, add string: `https://via.placeholder.com/400x300/4A90E2/FFFFFF?text=MacBook+Pro`) |
| pickupLocation | string | Library Building |
| isAvailable | boolean | true |
| createdAt | number | 1746489600000 |
| updatedAt | number | 1746489600000 |

4. Click **"Save"**

### Step 4: Add More Items (Repeat for each)

**Item 2 - Calculator**:
```
Document ID: item2
id: item2
ownerId: sample_owner_1
ownerName: Jane Smith
ownerRating: 4.8
title: Scientific Calculator
description: Casio FX-991EX, great for engineering
category: STUDY_GEAR
pricePerDay: 50
images: ["https://via.placeholder.com/400x300/50C878/FFFFFF?text=Calculator"]
pickupLocation: Main Campus
isAvailable: true
createdAt: 1746489600000
updatedAt: 1746489600000
```

**Item 3 - Tent**:
```
Document ID: item3
id: item3
ownerId: sample_owner_2
ownerName: John Doe
ownerRating: 4.5
title: Camping Tent
description: 4-person camping tent, perfect for trips
category: LIFESTYLE
pricePerDay: 200
images: ["https://via.placeholder.com/400x300/FF6B6B/FFFFFF?text=Camping+Tent"]
pickupLocation: Sports Complex
isAvailable: true
createdAt: 1746489600000
updatedAt: 1746489600000
```

**Item 4 - iPad**:
```
Document ID: item4
id: item4
ownerId: sample_owner_1
ownerName: Jane Smith
ownerRating: 4.8
title: iPad Air
description: Latest iPad Air with Apple Pencil
category: ELECTRONICS
pricePerDay: 300
images: ["https://via.placeholder.com/400x300/9B59B6/FFFFFF?text=iPad+Air"]
pickupLocation: Library Building
isAvailable: true
createdAt: 1746489600000
updatedAt: 1746489600000
```

**Item 5 - Textbooks**:
```
Document ID: item5
id: item5
ownerId: sample_owner_2
ownerName: John Doe
ownerRating: 4.5
title: Engineering Textbooks
description: Set of 5 core engineering textbooks
category: STUDY_GEAR
pricePerDay: 100
images: ["https://via.placeholder.com/400x300/F39C12/FFFFFF?text=Textbooks"]
pickupLocation: Main Campus
isAvailable: true
createdAt: 1746489600000
updatedAt: 1746489600000
```

---

## 🚀 Method 2: Using Python Script (Faster - 2 minutes)

### Prerequisites:
- Python 3 installed
- pip installed

### Step 1: Install Firebase Admin SDK
```bash
pip install firebase-admin
```

### Step 2: Download Service Account Key

1. Go to: https://console.firebase.google.com/project/campus-43f73/settings/serviceaccounts/adminsdk
2. Click **"Generate new private key"**
3. Click **"Generate key"** (downloads JSON file)
4. Save the file as `serviceAccountKey.json` in your project root

### Step 3: Run the Script
```bash
python3 populate_firebase.py
```

You should see:
```
✅ Firebase initialized successfully!

🔥 Starting Firebase population...

✅ Added: MacBook Pro 2021 (ELECTRONICS)
✅ Added: Scientific Calculator (STUDY_GEAR)
✅ Added: Camping Tent (LIFESTYLE)
✅ Added: iPad Air (ELECTRONICS)
✅ Added: Engineering Textbooks (STUDY_GEAR)
✅ Added: DSLR Camera (ELECTRONICS)
✅ Added: Graphing Calculator (STUDY_GEAR)
✅ Added: Bicycle (LIFESTYLE)

🎉 Successfully added all items to Firestore!
📊 Total items: 8
```

---

## 🚀 Method 3: Using Node.js Script (Alternative)

### Prerequisites:
- Node.js installed
- npm installed

### Step 1: Install Dependencies
```bash
npm install firebase-admin
```

### Step 2: Download Service Account Key
(Same as Python method above)

### Step 3: Run the Script
```bash
node populate_firebase.js
```

---

## ✅ Verify Data

### Check Firestore Console:
https://console.firebase.google.com/project/campus-43f73/firestore

You should see:
```
Collections
└── items (8 documents)
    ├── item1 (MacBook Pro 2021)
    ├── item2 (Scientific Calculator)
    ├── item3 (Camping Tent)
    ├── item4 (iPad Air)
    ├── item5 (Engineering Textbooks)
    ├── item6 (DSLR Camera)
    ├── item7 (Graphing Calculator)
    └── item8 (Bicycle)
```

---

## 📱 Test Your App

### Build and Run:
```bash
./gradlew clean build
./gradlew :app:installDebug
```

### Test Flow:
1. **Register**: Enter `test@bml.edu.in`
2. **OTP**: Enter any 6 digits (e.g., `123456`)
3. **Browse**: You should see 8 items in the feed!
4. **Filter**: Try category filters (All, Electronics, Study Gear, Lifestyle)
5. **Details**: Click an item to view details
6. **Book**: Select dates and create a booking
7. **Rentals**: View your booking in "My Rentals" tab

---

## 🎨 Sample Data Overview

### Electronics (3 items):
- MacBook Pro 2021 - ₹500/day
- iPad Air - ₹300/day
- DSLR Camera - ₹400/day

### Study Gear (3 items):
- Scientific Calculator - ₹50/day
- Engineering Textbooks - ₹100/day
- Graphing Calculator - ₹75/day

### Lifestyle (2 items):
- Camping Tent - ₹200/day
- Bicycle - ₹150/day

---

## 🔧 Troubleshooting

### "Permission denied" error
**Solution**: Make sure Firestore is in **test mode**
1. Go to Firestore → Rules
2. Should see:
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

### "Collection not found" in app
**Solution**: 
1. Check Firestore console - collection should be named `items` (lowercase)
2. Rebuild app: `./gradlew clean build`

### Python script error: "No module named 'firebase_admin'"
**Solution**: 
```bash
pip install firebase-admin
```

### "serviceAccountKey.json not found"
**Solution**: Download the key from Firebase Console (see Method 2, Step 2)

---

## 🎉 Success!

Once you see items in Firestore:
- ✅ Your database is populated
- ✅ Your app will load real data
- ✅ You can browse, filter, and book items
- ✅ All Firebase features are working

---

## 📊 What's Next?

### Immediate:
- ✅ Test all app features
- ✅ Create bookings
- ✅ View rentals

### Optional:
- Add more items manually
- Customize item details
- Add real images (requires Storage)
- Update prices and descriptions

---

## 📞 Quick Links

- **Firestore Console**: https://console.firebase.google.com/project/campus-43f73/firestore
- **Authentication**: https://console.firebase.google.com/project/campus-43f73/authentication
- **Project Settings**: https://console.firebase.google.com/project/campus-43f73/settings/general

---

**🎊 Your database is ready! Build and test your app now!**
