#!/bin/bash

# Firebase Services Auto-Setup Script
# This script automatically enables Firebase services for your project

echo "🔥 Firebase Services Auto-Setup"
echo "================================"
echo ""

PROJECT_ID="campus-43f73"

# Colors
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Check if Firebase CLI is installed
if ! command -v firebase &> /dev/null; then
    echo -e "${RED}❌ Firebase CLI is not installed${NC}"
    echo ""
    echo "Install Firebase CLI:"
    echo "  npm install -g firebase-tools"
    echo ""
    echo "Or visit: https://firebase.google.com/docs/cli"
    exit 1
fi

echo -e "${GREEN}✅ Firebase CLI found${NC}"
echo ""

# Login to Firebase
echo -e "${BLUE}Step 1: Logging in to Firebase...${NC}"
firebase login --no-localhost
if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Firebase login failed${NC}"
    exit 1
fi
echo -e "${GREEN}✅ Logged in successfully${NC}"
echo ""

# Set project
echo -e "${BLUE}Step 2: Setting Firebase project...${NC}"
firebase use $PROJECT_ID
if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Failed to set project${NC}"
    exit 1
fi
echo -e "${GREEN}✅ Project set: $PROJECT_ID${NC}"
echo ""

# Enable Authentication
echo -e "${BLUE}Step 3: Enabling Authentication...${NC}"
echo -e "${YELLOW}⚠️  This requires manual setup in Firebase Console${NC}"
echo "   Go to: https://console.firebase.google.com/project/$PROJECT_ID/authentication"
echo "   Enable: Email/Password authentication"
echo ""
read -p "Press Enter after enabling Authentication..."
echo -e "${GREEN}✅ Authentication enabled${NC}"
echo ""

# Deploy Firestore rules
echo -e "${BLUE}Step 4: Deploying Firestore rules...${NC}"
cat > firestore.rules << 'EOF'
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Allow read access to all users
    match /users/{userId} {
      allow read: if true;
      allow write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Allow read access to all items
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
EOF

firebase deploy --only firestore:rules
if [ $? -ne 0 ]; then
    echo -e "${YELLOW}⚠️  Firestore rules deployment failed (may need manual setup)${NC}"
else
    echo -e "${GREEN}✅ Firestore rules deployed${NC}"
fi
echo ""

# Deploy Storage rules
echo -e "${BLUE}Step 5: Deploying Storage rules...${NC}"
cat > storage.rules << 'EOF'
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
EOF

firebase deploy --only storage:rules
if [ $? -ne 0 ]; then
    echo -e "${YELLOW}⚠️  Storage rules deployment failed (may need manual setup)${NC}"
else
    echo -e "${GREEN}✅ Storage rules deployed${NC}"
fi
echo ""

# Create Firestore indexes
echo -e "${BLUE}Step 6: Creating Firestore indexes...${NC}"
cat > firestore.indexes.json << 'EOF'
{
  "indexes": [
    {
      "collectionGroup": "items",
      "queryScope": "COLLECTION",
      "fields": [
        {
          "fieldPath": "category",
          "order": "ASCENDING"
        },
        {
          "fieldPath": "isAvailable",
          "order": "ASCENDING"
        },
        {
          "fieldPath": "createdAt",
          "order": "DESCENDING"
        }
      ]
    },
    {
      "collectionGroup": "bookings",
      "queryScope": "COLLECTION",
      "fields": [
        {
          "fieldPath": "renterId",
          "order": "ASCENDING"
        },
        {
          "fieldPath": "createdAt",
          "order": "DESCENDING"
        }
      ]
    },
    {
      "collectionGroup": "bookings",
      "queryScope": "COLLECTION",
      "fields": [
        {
          "fieldPath": "ownerId",
          "order": "ASCENDING"
        },
        {
          "fieldPath": "createdAt",
          "order": "DESCENDING"
        }
      ]
    }
  ],
  "fieldOverrides": []
}
EOF

firebase deploy --only firestore:indexes
if [ $? -ne 0 ]; then
    echo -e "${YELLOW}⚠️  Firestore indexes deployment failed (may need manual setup)${NC}"
else
    echo -e "${GREEN}✅ Firestore indexes created${NC}"
fi
echo ""

# Clean up temporary files
rm -f firestore.rules storage.rules firestore.indexes.json

echo -e "${GREEN}🎉 Firebase services setup complete!${NC}"
echo ""
echo "Next steps:"
echo "1. Verify services in Firebase Console:"
echo "   https://console.firebase.google.com/project/$PROJECT_ID"
echo ""
echo "2. Build and run your app:"
echo "   ./build_and_run.sh"
echo ""
echo "3. Test authentication with any @bml.edu.in email"
