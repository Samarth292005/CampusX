// Firebase Database Population Script
// This script adds sample items to your Firestore database

const admin = require('firebase-admin');

// Initialize Firebase Admin SDK
// You'll need to download your service account key from:
// https://console.firebase.google.com/project/campus-43f73/settings/serviceaccounts/adminsdk

const serviceAccount = require('./serviceAccountKey.json');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

const db = admin.firestore();

// Sample Items Data
const items = [
  {
    id: 'item1',
    ownerId: 'sample_owner_1',
    ownerName: 'Jane Smith',
    ownerRating: 4.8,
    title: 'MacBook Pro 2021',
    description: '14-inch MacBook Pro with M1 chip, perfect for coding and design work. Includes charger and case.',
    category: 'ELECTRONICS',
    pricePerDay: 500.0,
    images: ['https://via.placeholder.com/400x300/4A90E2/FFFFFF?text=MacBook+Pro'],
    pickupLocation: 'Library Building',
    isAvailable: true,
    createdAt: Date.now(),
    updatedAt: Date.now()
  },
  {
    id: 'item2',
    ownerId: 'sample_owner_1',
    ownerName: 'Jane Smith',
    ownerRating: 4.8,
    title: 'Scientific Calculator',
    description: 'Casio FX-991EX, great for engineering calculations. All functions working perfectly.',
    category: 'STUDY_GEAR',
    pricePerDay: 50.0,
    images: ['https://via.placeholder.com/400x300/50C878/FFFFFF?text=Calculator'],
    pickupLocation: 'Main Campus',
    isAvailable: true,
    createdAt: Date.now(),
    updatedAt: Date.now()
  },
  {
    id: 'item3',
    ownerId: 'sample_owner_2',
    ownerName: 'John Doe',
    ownerRating: 4.5,
    title: 'Camping Tent',
    description: '4-person camping tent, perfect for weekend trips. Waterproof and easy to set up.',
    category: 'LIFESTYLE',
    pricePerDay: 200.0,
    images: ['https://via.placeholder.com/400x300/FF6B6B/FFFFFF?text=Camping+Tent'],
    pickupLocation: 'Sports Complex',
    isAvailable: true,
    createdAt: Date.now(),
    updatedAt: Date.now()
  },
  {
    id: 'item4',
    ownerId: 'sample_owner_1',
    ownerName: 'Jane Smith',
    ownerRating: 4.8,
    title: 'iPad Air',
    description: 'Latest iPad Air with Apple Pencil, great for note-taking and digital art.',
    category: 'ELECTRONICS',
    pricePerDay: 300.0,
    images: ['https://via.placeholder.com/400x300/9B59B6/FFFFFF?text=iPad+Air'],
    pickupLocation: 'Library Building',
    isAvailable: true,
    createdAt: Date.now(),
    updatedAt: Date.now()
  },
  {
    id: 'item5',
    ownerId: 'sample_owner_2',
    ownerName: 'John Doe',
    ownerRating: 4.5,
    title: 'Engineering Textbooks',
    description: 'Set of 5 core engineering textbooks. All in excellent condition.',
    category: 'STUDY_GEAR',
    pricePerDay: 100.0,
    images: ['https://via.placeholder.com/400x300/F39C12/FFFFFF?text=Textbooks'],
    pickupLocation: 'Main Campus',
    isAvailable: true,
    createdAt: Date.now(),
    updatedAt: Date.now()
  },
  {
    id: 'item6',
    ownerId: 'sample_owner_2',
    ownerName: 'John Doe',
    ownerRating: 4.5,
    title: 'DSLR Camera',
    description: 'Canon EOS 90D with 18-55mm lens. Perfect for photography projects.',
    category: 'ELECTRONICS',
    pricePerDay: 400.0,
    images: ['https://via.placeholder.com/400x300/E74C3C/FFFFFF?text=DSLR+Camera'],
    pickupLocation: 'Media Center',
    isAvailable: true,
    createdAt: Date.now(),
    updatedAt: Date.now()
  },
  {
    id: 'item7',
    ownerId: 'sample_owner_1',
    ownerName: 'Jane Smith',
    ownerRating: 4.8,
    title: 'Graphing Calculator',
    description: 'TI-84 Plus CE, essential for advanced math courses.',
    category: 'STUDY_GEAR',
    pricePerDay: 75.0,
    images: ['https://via.placeholder.com/400x300/3498DB/FFFFFF?text=TI-84'],
    pickupLocation: 'Library Building',
    isAvailable: true,
    createdAt: Date.now(),
    updatedAt: Date.now()
  },
  {
    id: 'item8',
    ownerId: 'sample_owner_2',
    ownerName: 'John Doe',
    ownerRating: 4.5,
    title: 'Bicycle',
    description: 'Mountain bike in great condition. Perfect for campus commute.',
    category: 'LIFESTYLE',
    pricePerDay: 150.0,
    images: ['https://via.placeholder.com/400x300/27AE60/FFFFFF?text=Bicycle'],
    pickupLocation: 'Sports Complex',
    isAvailable: true,
    createdAt: Date.now(),
    updatedAt: Date.now()
  }
];

// Function to populate items
async function populateItems() {
  console.log('🔥 Starting Firebase population...\n');
  
  try {
    const batch = db.batch();
    
    items.forEach(item => {
      const docRef = db.collection('items').doc(item.id);
      batch.set(docRef, item);
      console.log(`✅ Added: ${item.title} (${item.category})`);
    });
    
    await batch.commit();
    
    console.log('\n🎉 Successfully added all items to Firestore!');
    console.log(`📊 Total items: ${items.length}`);
    console.log('\n📱 Now you can:');
    console.log('   1. Build and run your app');
    console.log('   2. Browse items in the feed');
    console.log('   3. Create bookings');
    console.log('\n🔗 View your data:');
    console.log('   https://console.firebase.google.com/project/campus-43f73/firestore');
    
  } catch (error) {
    console.error('❌ Error populating Firestore:', error);
  }
  
  process.exit(0);
}

// Run the population
populateItems();
