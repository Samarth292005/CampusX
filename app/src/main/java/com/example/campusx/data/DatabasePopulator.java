package com.example.campusx.data;

import android.util.Log;

import com.example.campusx.model.Item;
import com.example.campusx.model.ItemCategory;

import java.util.Arrays;
import java.util.UUID;

/**
 * Database Populator - Automatically adds sample items to Firestore
 * This runs once when the app first launches
 */
public class DatabasePopulator {
    private static final String TAG = "DatabasePopulator";
    private static final String PREFS_NAME = "CampusXPrefs";
    private static final String KEY_DB_POPULATED = "database_populated";
    
    private final FirebaseRepository firebaseRepo;
    
    public DatabasePopulator(FirebaseRepository firebaseRepo) {
        this.firebaseRepo = firebaseRepo;
    }
    
    /**
     * Populate Firestore with sample items
     * Uses real product images from Unsplash
     */
    public void populateIfNeeded(android.content.Context context) {
        // Check if already populated
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE);
        boolean isPopulated = prefs.getBoolean(KEY_DB_POPULATED, false);
        
        if (isPopulated) {
            Log.d(TAG, "Database already populated, skipping...");
            return;
        }
        
        // Check if user is authenticated
        if (firebaseRepo.getCurrentFirebaseUser() == null) {
            Log.d(TAG, "User not authenticated, skipping database population");
            return;
        }
        
        Log.d(TAG, "Starting database population...");
        
        // Create sample items with real images
        Item[] items = createSampleItems();
        
        // Add items to Firestore
        int successCount = 0;
        for (Item item : items) {
            firebaseRepo.createItem(item)
                    .addOnSuccessListener(aVoid -> {
                        Log.d(TAG, "✅ Added: " + item.getTitle());
                    })
                    .addOnFailureListener(e -> {
                        Log.e(TAG, "❌ Failed to add: " + item.getTitle(), e);
                    });
            successCount++;
        }
        
        // Mark as populated
        prefs.edit().putBoolean(KEY_DB_POPULATED, true).apply();
        Log.d(TAG, "🎉 Database population complete! Added " + successCount + " items");
    }
    
    /**
     * Force re-populate database (for testing)
     */
    public void forcePopulate(android.content.Context context) {
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE);
        prefs.edit().putBoolean(KEY_DB_POPULATED, false).apply();
        populateIfNeeded(context);
    }
    
    /**
     * Create sample items with real images from Unsplash
     */
    private Item[] createSampleItems() {
        long now = System.currentTimeMillis();
        
        return new Item[]{
                // Electronics
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_1",
                        "Jane Smith",
                        4.8,
                        "MacBook Pro 2021",
                        "14-inch MacBook Pro with M1 chip, 16GB RAM, 512GB SSD. Perfect for coding, design work, and video editing. Includes charger and protective case.",
                        ItemCategory.ELECTRONICS,
                        500.0,
                        Arrays.asList("https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=800&h=600&fit=crop"),
                        "Library Building",
                        true,
                        now,
                        now
                ),
                
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_1",
                        "Jane Smith",
                        4.8,
                        "iPad Air with Apple Pencil",
                        "Latest iPad Air (5th gen) with Apple Pencil (2nd gen). Perfect for note-taking, digital art, and studying. 256GB storage, WiFi + Cellular.",
                        ItemCategory.ELECTRONICS,
                        300.0,
                        Arrays.asList("https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=800&h=600&fit=crop"),
                        "Library Building",
                        true,
                        now,
                        now
                ),
                
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_2",
                        "John Doe",
                        4.5,
                        "Canon DSLR Camera",
                        "Canon EOS 90D with 18-55mm lens. Perfect for photography projects, events, and content creation. Includes memory card, battery, and camera bag.",
                        ItemCategory.ELECTRONICS,
                        400.0,
                        Arrays.asList("https://images.unsplash.com/photo-1606980707986-8f6e1f0d1e0f?w=800&h=600&fit=crop"),
                        "Media Center",
                        true,
                        now,
                        now
                ),
                
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_1",
                        "Jane Smith",
                        4.8,
                        "Wireless Headphones",
                        "Sony WH-1000XM4 noise-cancelling headphones. Perfect for studying, music, and calls. 30-hour battery life with quick charging.",
                        ItemCategory.ELECTRONICS,
                        150.0,
                        Arrays.asList("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800&h=600&fit=crop"),
                        "Library Building",
                        true,
                        now,
                        now
                ),
                
                // Study Gear
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_1",
                        "Jane Smith",
                        4.8,
                        "Scientific Calculator",
                        "Casio FX-991EX ClassWiz. Essential for engineering and science courses. All functions working perfectly, includes manual.",
                        ItemCategory.STUDY_GEAR,
                        50.0,
                        Arrays.asList("https://images.unsplash.com/photo-1611348524140-53c9a25263d6?w=800&h=600&fit=crop"),
                        "Main Campus",
                        true,
                        now,
                        now
                ),
                
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_2",
                        "John Doe",
                        4.5,
                        "Engineering Textbooks Set",
                        "Complete set of 5 core engineering textbooks: Calculus, Physics, Chemistry, Engineering Mechanics, and Thermodynamics. All in excellent condition.",
                        ItemCategory.STUDY_GEAR,
                        100.0,
                        Arrays.asList("https://images.unsplash.com/photo-1495446815901-a7297e633e8d?w=800&h=600&fit=crop"),
                        "Main Campus",
                        true,
                        now,
                        now
                ),
                
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_1",
                        "Jane Smith",
                        4.8,
                        "Graphing Calculator TI-84",
                        "TI-84 Plus CE graphing calculator. Essential for advanced math and statistics courses. Color display, rechargeable battery.",
                        ItemCategory.STUDY_GEAR,
                        75.0,
                        Arrays.asList("https://images.unsplash.com/photo-1587825140708-dfaf72ae4b04?w=800&h=600&fit=crop"),
                        "Library Building",
                        true,
                        now,
                        now
                ),
                
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_2",
                        "John Doe",
                        4.5,
                        "Study Desk Lamp",
                        "LED desk lamp with adjustable brightness and color temperature. Perfect for late-night study sessions. USB charging port included.",
                        ItemCategory.STUDY_GEAR,
                        30.0,
                        Arrays.asList("https://images.unsplash.com/photo-1507473885765-e6ed057f782c?w=800&h=600&fit=crop"),
                        "Main Campus",
                        true,
                        now,
                        now
                ),
                
                // Lifestyle
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_2",
                        "John Doe",
                        4.5,
                        "Camping Tent (4-Person)",
                        "Spacious 4-person camping tent. Perfect for weekend trips and outdoor adventures. Waterproof, easy setup, includes carrying bag.",
                        ItemCategory.LIFESTYLE,
                        200.0,
                        Arrays.asList("https://images.unsplash.com/photo-1504280390367-361c6d9f38f4?w=800&h=600&fit=crop"),
                        "Sports Complex",
                        true,
                        now,
                        now
                ),
                
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_2",
                        "John Doe",
                        4.5,
                        "Mountain Bicycle",
                        "21-speed mountain bike in excellent condition. Perfect for campus commute and weekend trails. Includes helmet and lock.",
                        ItemCategory.LIFESTYLE,
                        150.0,
                        Arrays.asList("https://images.unsplash.com/photo-1576435728678-68d0fbf94e91?w=800&h=600&fit=crop"),
                        "Sports Complex",
                        true,
                        now,
                        now
                ),
                
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_1",
                        "Jane Smith",
                        4.8,
                        "Yoga Mat & Accessories",
                        "Premium yoga mat with carrying strap, blocks, and resistance bands. Perfect for fitness and wellness. Non-slip, eco-friendly material.",
                        ItemCategory.LIFESTYLE,
                        40.0,
                        Arrays.asList("https://images.unsplash.com/photo-1601925260368-ae2f83cf8b7f?w=800&h=600&fit=crop"),
                        "Sports Complex",
                        true,
                        now,
                        now
                ),
                
                new Item(
                        UUID.randomUUID().toString(),
                        "sample_owner_2",
                        "John Doe",
                        4.5,
                        "Portable Bluetooth Speaker",
                        "JBL Flip 5 waterproof Bluetooth speaker. Perfect for parties, outdoor activities, and dorm rooms. 12-hour battery life.",
                        ItemCategory.LIFESTYLE,
                        80.0,
                        Arrays.asList("https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=800&h=600&fit=crop"),
                        "Main Campus",
                        true,
                        now,
                        now
                )
        };
    }
}
