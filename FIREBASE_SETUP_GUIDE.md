# Firebase Setup Guide for CampusX

## Step 1: Create Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click "Add project"
3. Enter project name: **CampusX**
4. Enable Google Analytics (optional)
5. Click "Create project"

## Step 2: Add Android App to Firebase

1. In Firebase Console, click the Android icon
2. Enter package name: `com.example.campusx`
3. Enter app nickname: **CampusX Android**
4. Enter SHA-1 certificate (optional for now, required for Google Sign-In later)
5. Click "Register app"

### Get SHA-1 Certificate (Optional)

```bash
# For debug keystore
keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android

# For release keystore (when you create one)
keytool -list -v -keystore /path/to/your/keystore.jks -alias your-alias
```

## Step 3: Download google-services.json

1. Download the `google-services.json` file
2. Place it in the `app/` directory (same level as `build.gradle`)
3. **IMPORTANT**: Add `google-services.json` to `.gitignore` to keep credentials secure

```bash
# Add to .gitignore
echo "app/google-services.json" >> .gitignore
```

## Step 4: Enable Firebase Services

### 4.1 Enable Authentication

1. In Firebase Console, go to **Authentication**
2. Click "Get started"
3. Enable **Email/Password** sign-in method
4. Click "Save"

### 4.2 Enable Cloud Firestore

1. In Firebase Console, go to **Firestore Database**
2. Click "Create database"
3. Select **Start in test mode** (for development)
4. Choose a location (e.g., `us-central`)
5. Click "Enable"

**Security Rules (Test Mode - Update before production!):**
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

**Production Security Rules (Update later):**
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Users can read all user profiles
    match /users/{userId} {
      allow read: if true;
      allow write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Items can be read by anyone, written by owner
    match /items/{itemId} {
      allow read: if true;
      allow create: if request.auth != null;
      allow update, delete: if request.auth != null && 
                              request.auth.uid == resource.data.ownerId;
    }
    
    // Bookings can be read by renter or owner
    match /bookings/{bookingId} {
      allow read: if request.auth != null && 
                    (request.auth.uid == resource.data.renterId || 
                     request.auth.uid == resource.data.ownerId);
      allow create: if request.auth != null;
      allow update: if request.auth != null && 
                      (request.auth.uid == resource.data.renterId || 
                       request.auth.uid == resource.data.ownerId);
    }
  }
}
```

### 4.3 Enable Firebase Storage

1. In Firebase Console, go to **Storage**
2. Click "Get started"
3. Select **Start in test mode**
4. Click "Next" and "Done"

**Storage Rules (Test Mode):**
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

**Production Storage Rules (Update later):**
```javascript
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    // Profile images
    match /profiles/{userId}/{allPaths=**} {
      allow read: if true;
      allow write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Item images
    match /items/{itemId}/{allPaths=**} {
      allow read: if true;
      allow write: if request.auth != null;
    }
  }
}
```

## Step 5: Firestore Database Structure

### Collections:

#### 1. **users** Collection
```json
{
  "id": "user123",
  "email": "john@bml.edu.in",
  "name": "John Doe",
  "profileImageUrl": "https://...",
  "bio": "Engineering student",
  "rating": 4.5,
  "totalListings": 10,
  "totalRentals": 3,
  "totalReviews": 5,
  "isVerified": true,
  "createdAt": 1234567890,
  "updatedAt": 1234567890
}
```

#### 2. **items** Collection
```json
{
  "id": "item123",
  "ownerId": "user123",
  "ownerName": "John Doe",
  "ownerRating": 4.5,
  "title": "MacBook Pro 2021",
  "description": "14-inch MacBook Pro with M1 chip",
  "category": "ELECTRONICS",
  "pricePerDay": 500.0,
  "images": ["https://...", "https://..."],
  "pickupLocation": "Library Building",
  "isAvailable": true,
  "createdAt": 1234567890,
  "updatedAt": 1234567890
}
```

#### 3. **bookings** Collection
```json
{
  "id": "booking123",
  "itemId": "item123",
  "itemTitle": "MacBook Pro 2021",
  "itemImage": "https://...",
  "renterId": "user456",
  "ownerId": "user123",
  "startDate": 1234567890,
  "endDate": 1234567890,
  "totalPrice": 1500.0,
  "status": "PENDING",
  "otp": "123456",
  "createdAt": 1234567890,
  "updatedAt": 1234567890
}
```

## Step 6: Build and Test

1. Sync Gradle files:
```bash
./gradlew clean build
```

2. Run the app:
```bash
./gradlew :app:installDebug
```

3. Check Firebase Console to see data being created

## Step 7: Testing Firebase Integration

### Test Authentication:
1. Open app and go through onboarding
2. Enter email: `test@bml.edu.in`
3. Enter OTP (any 6 digits for now)
4. Check Firebase Console → Authentication → Users

### Test Firestore:
1. Create a test item in the app
2. Check Firebase Console → Firestore Database → items collection

### Test Storage:
1. Upload an item image
2. Check Firebase Console → Storage → items folder

## Common Issues & Solutions

### Issue 1: "google-services.json not found"
**Solution**: Make sure `google-services.json` is in the `app/` directory

### Issue 2: "Default FirebaseApp is not initialized"
**Solution**: 
- Check that `google-services.json` is in the correct location
- Sync Gradle files
- Clean and rebuild project

### Issue 3: "Permission denied" errors
**Solution**: Update Firestore/Storage security rules to test mode

### Issue 4: SHA-1 certificate error
**Solution**: Add SHA-1 certificate to Firebase project settings

## Next Steps

1. ✅ Firebase dependencies added
2. ✅ FirebaseRepository class created
3. ⏳ Download and add `google-services.json`
4. ⏳ Enable Firebase services in console
5. ⏳ Update authentication flow to use Firebase
6. ⏳ Update data layer to use Firebase instead of mock data
7. ⏳ Test all features with real Firebase backend

## Security Checklist (Before Production)

- [ ] Update Firestore security rules
- [ ] Update Storage security rules
- [ ] Add SHA-1 certificate for release keystore
- [ ] Enable App Check for additional security
- [ ] Set up Firebase Analytics
- [ ] Configure Firebase Crashlytics
- [ ] Add rate limiting for authentication
- [ ] Implement proper error handling
- [ ] Add data validation rules
- [ ] Set up backup and recovery

## Useful Firebase Console Links

- **Authentication**: https://console.firebase.google.com/project/YOUR_PROJECT/authentication
- **Firestore**: https://console.firebase.google.com/project/YOUR_PROJECT/firestore
- **Storage**: https://console.firebase.google.com/project/YOUR_PROJECT/storage
- **Analytics**: https://console.firebase.google.com/project/YOUR_PROJECT/analytics
