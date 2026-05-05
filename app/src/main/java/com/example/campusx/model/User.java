package com.example.campusx.model;

public class User {
    private String id;
    private String email;
    private String name;
    private String profileImageUrl;
    private String bio;
    private double rating;
    private int ratingCount;
    private int listingCount;
    private int rentalCount;
    private boolean isAdmin;
    private long createdAt;
    private long lastActive;

    public User() {
    }

    public User(String id, String email, String name, String profileImageUrl, String bio,
                double rating, int ratingCount, int listingCount, int rentalCount,
                boolean isAdmin, long createdAt, long lastActive) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.bio = bio;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.listingCount = listingCount;
        this.rentalCount = rentalCount;
        this.isAdmin = isAdmin;
        this.createdAt = createdAt;
        this.lastActive = lastActive;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getRatingCount() { return ratingCount; }
    public void setRatingCount(int ratingCount) { this.ratingCount = ratingCount; }

    public int getListingCount() { return listingCount; }
    public void setListingCount(int listingCount) { this.listingCount = listingCount; }

    public int getRentalCount() { return rentalCount; }
    public void setRentalCount(int rentalCount) { this.rentalCount = rentalCount; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public long getLastActive() { return lastActive; }
    public void setLastActive(long lastActive) { this.lastActive = lastActive; }
}
