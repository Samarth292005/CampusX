// Firebase Usage Examples for CampusX

// ==================== Authentication Examples ====================

// 1. Sign Up with Email
FirebaseRepository repo = FirebaseRepository.getInstance();
String email = "john@bml.edu.in";
String password = "password123";

repo.createUserWithEmail(email, password)
    .addOnSuccessListener(authResult -> {
        // User created successfully
        String userId = authResult.getUser().getUid();
        
        // Create user profile in Firestore
        User user = new User(
            userId,
            email,
            "John Doe",
            null,
            "Engineering student",
            0.0,
            0,
            0,
            0,
            false,
            System.currentTimeMillis(),
            System.currentTimeMillis()
        );
        
        repo.createUser(user)
            .addOnSuccessListener(v -> {
                // Navigate to main app
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            })
            .addOnFailureListener(e -> {
                Log.e(TAG, "Error creating user profile", e);
            });
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error creating account", e);
        Toast.makeText(this, "Sign up failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
    });

// 2. Sign In with Email
repo.signInWithEmail(email, password)
    .addOnSuccessListener(authResult -> {
        // User signed in successfully
        String userId = authResult.getUser().getUid();
        
        // Fetch user profile
        repo.getUser(userId)
            .addOnSuccessListener(document -> {
                if (document.exists()) {
                    User user = document.toObject(User.class);
                    // Navigate to main app
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error signing in", e);
        Toast.makeText(this, "Sign in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
    });

// 3. Check if User is Logged In
FirebaseUser currentUser = repo.getCurrentFirebaseUser();
if (currentUser != null) {
    // User is signed in
    String userId = currentUser.getUid();
    String email = currentUser.getEmail();
} else {
    // User is not signed in
    // Navigate to login screen
}

// 4. Sign Out
repo.signOut();
Intent intent = new Intent(this, OnboardingActivity.class);
intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
startActivity(intent);
finish();

// ==================== Item Operations ====================

// 1. Create New Item
Item item = new Item(
    UUID.randomUUID().toString(),
    repo.getCurrentUserId(),
    "John Doe",
    4.5,
    "MacBook Pro 2021",
    "14-inch MacBook Pro with M1 chip",
    ItemCategory.ELECTRONICS,
    500.0,
    new ArrayList<>(), // Images will be added after upload
    "Library Building",
    true,
    System.currentTimeMillis(),
    System.currentTimeMillis()
);

repo.createItem(item)
    .addOnSuccessListener(v -> {
        Toast.makeText(this, "Item listed successfully!", Toast.LENGTH_SHORT).show();
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error creating item", e);
        Toast.makeText(this, "Failed to list item", Toast.LENGTH_SHORT).show();
    });

// 2. Get All Items
repo.getAllItems()
    .addOnSuccessListener(querySnapshot -> {
        List<Item> items = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot) {
            Item item = document.toObject(Item.class);
            items.add(item);
        }
        
        // Update RecyclerView adapter
        adapter.setItems(items);
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error fetching items", e);
    });

// 3. Get Items by Category
repo.getItemsByCategory(ItemCategory.ELECTRONICS.name())
    .addOnSuccessListener(querySnapshot -> {
        List<Item> items = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot) {
            Item item = document.toObject(Item.class);
            items.add(item);
        }
        adapter.setItems(items);
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error fetching items by category", e);
    });

// 4. Search Items
String searchQuery = "MacBook";
repo.searchItems(searchQuery)
    .addOnSuccessListener(querySnapshot -> {
        List<Item> results = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot) {
            Item item = document.toObject(Item.class);
            results.add(item);
        }
        adapter.setItems(results);
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error searching items", e);
    });

// 5. Update Item Availability
String itemId = "item123";
Map<String, Object> updates = new HashMap<>();
updates.put("isAvailable", false);

repo.updateItem(itemId, updates)
    .addOnSuccessListener(v -> {
        Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error updating item", e);
    });

// 6. Delete Item
repo.deleteItem(itemId)
    .addOnSuccessListener(v -> {
        Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error deleting item", e);
    });

// ==================== Booking Operations ====================

// 1. Create Booking
String otp = String.format("%06d", new Random().nextInt(999999));

Booking booking = new Booking(
    UUID.randomUUID().toString(),
    "item123",
    "MacBook Pro 2021",
    "https://...",
    repo.getCurrentUserId(),
    "owner123",
    startDate,
    endDate,
    1500.0,
    BookingStatus.PENDING,
    otp,
    System.currentTimeMillis(),
    System.currentTimeMillis()
);

repo.createBooking(booking)
    .addOnSuccessListener(v -> {
        // Navigate to confirmation screen
        Intent intent = new Intent(this, BookingConfirmationActivity.class);
        intent.putExtra("booking_id", booking.getId());
        intent.putExtra("otp", otp);
        startActivity(intent);
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error creating booking", e);
        Toast.makeText(this, "Booking failed", Toast.LENGTH_SHORT).show();
    });

// 2. Get User's Bookings
repo.getBookingsByRenter(repo.getCurrentUserId())
    .addOnSuccessListener(querySnapshot -> {
        List<Booking> bookings = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot) {
            Booking booking = document.toObject(Booking.class);
            bookings.add(booking);
        }
        adapter.setBookings(bookings);
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error fetching bookings", e);
    });

// 3. Update Booking Status
String bookingId = "booking123";
repo.updateBookingStatus(bookingId, BookingStatus.CONFIRMED.name())
    .addOnSuccessListener(v -> {
        Toast.makeText(this, "Booking confirmed!", Toast.LENGTH_SHORT).show();
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error updating booking", e);
    });

// ==================== Storage Operations ====================

// 1. Upload Item Image
Uri imageUri = data.getData(); // From image picker
String itemId = "item123";
int imageIndex = 0;

repo.uploadItemImage(itemId, imageUri, imageIndex)
    .addOnSuccessListener(taskSnapshot -> {
        // Get download URL
        repo.getItemImageUrl(itemId, imageIndex)
            .addOnSuccessListener(downloadUri -> {
                // Update item with image URL
                Map<String, Object> updates = new HashMap<>();
                List<String> images = new ArrayList<>();
                images.add(downloadUri.toString());
                updates.put("images", images);
                
                repo.updateItem(itemId, updates)
                    .addOnSuccessListener(v -> {
                        Toast.makeText(this, "Image uploaded!", Toast.LENGTH_SHORT).show();
                    });
            });
    })
    .addOnProgressListener(snapshot -> {
        double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
        progressBar.setProgress((int) progress);
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error uploading image", e);
        Toast.makeText(this, "Upload failed", Toast.LENGTH_SHORT).show();
    });

// 2. Upload Profile Image
Uri profileImageUri = data.getData();
String userId = repo.getCurrentUserId();

repo.uploadProfileImage(userId, profileImageUri)
    .addOnSuccessListener(taskSnapshot -> {
        repo.getProfileImageUrl(userId)
            .addOnSuccessListener(downloadUri -> {
                // Update user profile
                Map<String, Object> updates = new HashMap<>();
                updates.put("profileImageUrl", downloadUri.toString());
                
                repo.updateUser(userId, updates)
                    .addOnSuccessListener(v -> {
                        // Load image with Glide
                        Glide.with(this)
                            .load(downloadUri)
                            .into(profileImageView);
                    });
            });
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Error uploading profile image", e);
    });

// 3. Load Image with Glide
String imageUrl = "https://firebasestorage.googleapis.com/...";
Glide.with(context)
    .load(imageUrl)
    .placeholder(R.drawable.ic_launcher_background)
    .error(R.drawable.ic_launcher_background)
    .into(imageView);

// ==================== Real-time Updates ====================

// Listen to item changes
FirebaseFirestore db = FirebaseFirestore.getInstance();
db.collection("items")
    .document(itemId)
    .addSnapshotListener((snapshot, error) -> {
        if (error != null) {
            Log.e(TAG, "Listen failed", error);
            return;
        }
        
        if (snapshot != null && snapshot.exists()) {
            Item item = snapshot.toObject(Item.class);
            // Update UI with new data
            updateUI(item);
        }
    });

// Listen to all items in a category
db.collection("items")
    .whereEqualTo("category", "ELECTRONICS")
    .whereEqualTo("isAvailable", true)
    .addSnapshotListener((snapshots, error) -> {
        if (error != null) {
            Log.e(TAG, "Listen failed", error);
            return;
        }
        
        List<Item> items = new ArrayList<>();
        for (DocumentSnapshot doc : snapshots) {
            Item item = doc.toObject(Item.class);
            items.add(item);
        }
        adapter.setItems(items);
    });

// ==================== Error Handling ====================

// Comprehensive error handling
repo.createItem(item)
    .addOnSuccessListener(v -> {
        // Success
        Toast.makeText(this, "Item created!", Toast.LENGTH_SHORT).show();
    })
    .addOnFailureListener(e -> {
        // Handle different error types
        if (e instanceof FirebaseAuthException) {
            Toast.makeText(this, "Authentication error", Toast.LENGTH_SHORT).show();
        } else if (e instanceof FirebaseNetworkException) {
            Toast.makeText(this, "Network error. Check your connection.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Log.e(TAG, "Error creating item", e);
    })
    .addOnCompleteListener(task -> {
        // Always executed, regardless of success or failure
        progressBar.setVisibility(View.GONE);
    });

// ==================== Batch Operations ====================

// Batch write (atomic operations)
FirebaseFirestore db = FirebaseFirestore.getInstance();
WriteBatch batch = db.batch();

// Update multiple items
DocumentReference item1Ref = db.collection("items").document("item1");
batch.update(item1Ref, "isAvailable", false);

DocumentReference item2Ref = db.collection("items").document("item2");
batch.update(item2Ref, "isAvailable", false);

// Commit batch
batch.commit()
    .addOnSuccessListener(v -> {
        Toast.makeText(this, "Batch update successful", Toast.LENGTH_SHORT).show();
    })
    .addOnFailureListener(e -> {
        Log.e(TAG, "Batch update failed", e);
    });

// ==================== Pagination ====================

// Paginate items (load 20 at a time)
Query query = db.collection("items")
    .whereEqualTo("isAvailable", true)
    .orderBy("createdAt", Query.Direction.DESCENDING)
    .limit(20);

query.get()
    .addOnSuccessListener(querySnapshot -> {
        List<Item> items = new ArrayList<>();
        DocumentSnapshot lastVisible = null;
        
        for (DocumentSnapshot document : querySnapshot) {
            Item item = document.toObject(Item.class);
            items.add(item);
            lastVisible = document;
        }
        
        adapter.setItems(items);
        
        // Load next page
        if (lastVisible != null) {
            Query nextQuery = db.collection("items")
                .whereEqualTo("isAvailable", true)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .startAfter(lastVisible)
                .limit(20);
            
            // Execute nextQuery when user scrolls to bottom
        }
    });
