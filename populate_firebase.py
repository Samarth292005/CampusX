#!/usr/bin/env python3
"""
Firebase Database Population Script
This script adds sample items to your Firestore database
"""

import firebase_admin
from firebase_admin import credentials, firestore
import time

# Initialize Firebase Admin SDK
# Download your service account key from:
# https://console.firebase.google.com/project/campus-43f73/settings/serviceaccounts/adminsdk
# Save it as 'serviceAccountKey.json' in this directory

try:
    cred = credentials.Certificate('serviceAccountKey.json')
    firebase_admin.initialize_app(cred)
    db = firestore.client()
    print("✅ Firebase initialized successfully!\n")
except Exception as e:
    print(f"❌ Error initializing Firebase: {e}")
    print("\n📝 To fix this:")
    print("   1. Go to: https://console.firebase.google.com/project/campus-43f73/settings/serviceaccounts/adminsdk")
    print("   2. Click 'Generate new private key'")
    print("   3. Save the file as 'serviceAccountKey.json' in this directory")
    print("   4. Run this script again")
    exit(1)

# Sample Items Data
items = [
    {
        'id': 'item1',
        'ownerId': 'sample_owner_1',
        'ownerName': 'Jane Smith',
        'ownerRating': 4.8,
        'title': 'MacBook Pro 2021',
        'description': '14-inch MacBook Pro with M1 chip, perfect for coding and design work. Includes charger and case.',
        'category': 'ELECTRONICS',
        'pricePerDay': 500.0,
        'images': ['https://via.placeholder.com/400x300/4A90E2/FFFFFF?text=MacBook+Pro'],
        'pickupLocation': 'Library Building',
        'isAvailable': True,
        'createdAt': int(time.time() * 1000),
        'updatedAt': int(time.time() * 1000)
    },
    {
        'id': 'item2',
        'ownerId': 'sample_owner_1',
        'ownerName': 'Jane Smith',
        'ownerRating': 4.8,
        'title': 'Scientific Calculator',
        'description': 'Casio FX-991EX, great for engineering calculations. All functions working perfectly.',
        'category': 'STUDY_GEAR',
        'pricePerDay': 50.0,
        'images': ['https://via.placeholder.com/400x300/50C878/FFFFFF?text=Calculator'],
        'pickupLocation': 'Main Campus',
        'isAvailable': True,
        'createdAt': int(time.time() * 1000),
        'updatedAt': int(time.time() * 1000)
    },
    {
        'id': 'item3',
        'ownerId': 'sample_owner_2',
        'ownerName': 'John Doe',
        'ownerRating': 4.5,
        'title': 'Camping Tent',
        'description': '4-person camping tent, perfect for weekend trips. Waterproof and easy to set up.',
        'category': 'LIFESTYLE',
        'pricePerDay': 200.0,
        'images': ['https://via.placeholder.com/400x300/FF6B6B/FFFFFF?text=Camping+Tent'],
        'pickupLocation': 'Sports Complex',
        'isAvailable': True,
        'createdAt': int(time.time() * 1000),
        'updatedAt': int(time.time() * 1000)
    },
    {
        'id': 'item4',
        'ownerId': 'sample_owner_1',
        'ownerName': 'Jane Smith',
        'ownerRating': 4.8,
        'title': 'iPad Air',
        'description': 'Latest iPad Air with Apple Pencil, great for note-taking and digital art.',
        'category': 'ELECTRONICS',
        'pricePerDay': 300.0,
        'images': ['https://via.placeholder.com/400x300/9B59B6/FFFFFF?text=iPad+Air'],
        'pickupLocation': 'Library Building',
        'isAvailable': True,
        'createdAt': int(time.time() * 1000),
        'updatedAt': int(time.time() * 1000)
    },
    {
        'id': 'item5',
        'ownerId': 'sample_owner_2',
        'ownerName': 'John Doe',
        'ownerRating': 4.5,
        'title': 'Engineering Textbooks',
        'description': 'Set of 5 core engineering textbooks. All in excellent condition.',
        'category': 'STUDY_GEAR',
        'pricePerDay': 100.0,
        'images': ['https://via.placeholder.com/400x300/F39C12/FFFFFF?text=Textbooks'],
        'pickupLocation': 'Main Campus',
        'isAvailable': True,
        'createdAt': int(time.time() * 1000),
        'updatedAt': int(time.time() * 1000)
    },
    {
        'id': 'item6',
        'ownerId': 'sample_owner_2',
        'ownerName': 'John Doe',
        'ownerRating': 4.5,
        'title': 'DSLR Camera',
        'description': 'Canon EOS 90D with 18-55mm lens. Perfect for photography projects.',
        'category': 'ELECTRONICS',
        'pricePerDay': 400.0,
        'images': ['https://via.placeholder.com/400x300/E74C3C/FFFFFF?text=DSLR+Camera'],
        'pickupLocation': 'Media Center',
        'isAvailable': True,
        'createdAt': int(time.time() * 1000),
        'updatedAt': int(time.time() * 1000)
    },
    {
        'id': 'item7',
        'ownerId': 'sample_owner_1',
        'ownerName': 'Jane Smith',
        'ownerRating': 4.8,
        'title': 'Graphing Calculator',
        'description': 'TI-84 Plus CE, essential for advanced math courses.',
        'category': 'STUDY_GEAR',
        'pricePerDay': 75.0,
        'images': ['https://via.placeholder.com/400x300/3498DB/FFFFFF?text=TI-84'],
        'pickupLocation': 'Library Building',
        'isAvailable': True,
        'createdAt': int(time.time() * 1000),
        'updatedAt': int(time.time() * 1000)
    },
    {
        'id': 'item8',
        'ownerId': 'sample_owner_2',
        'ownerName': 'John Doe',
        'ownerRating': 4.5,
        'title': 'Bicycle',
        'description': 'Mountain bike in great condition. Perfect for campus commute.',
        'category': 'LIFESTYLE',
        'pricePerDay': 150.0,
        'images': ['https://via.placeholder.com/400x300/27AE60/FFFFFF?text=Bicycle'],
        'pickupLocation': 'Sports Complex',
        'isAvailable': True,
        'createdAt': int(time.time() * 1000),
        'updatedAt': int(time.time() * 1000)
    }
]

def populate_items():
    """Add sample items to Firestore"""
    print('🔥 Starting Firebase population...\n')
    
    try:
        batch = db.batch()
        
        for item in items:
            doc_ref = db.collection('items').document(item['id'])
            batch.set(doc_ref, item)
            print(f"✅ Added: {item['title']} ({item['category']})")
        
        batch.commit()
        
        print(f'\n🎉 Successfully added all items to Firestore!')
        print(f'📊 Total items: {len(items)}')
        print('\n📱 Now you can:')
        print('   1. Build and run your app')
        print('   2. Browse items in the feed')
        print('   3. Create bookings')
        print('\n🔗 View your data:')
        print('   https://console.firebase.google.com/project/campus-43f73/firestore')
        
    except Exception as e:
        print(f'❌ Error populating Firestore: {e}')

if __name__ == '__main__':
    populate_items()
