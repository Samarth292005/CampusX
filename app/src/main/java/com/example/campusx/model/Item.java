package com.example.campusx.model;

import java.util.List;

public class Item {
    private String id;
    private String ownerId;
    private String ownerName;
    private double ownerRating;
    private String title;
    private String description;
    private ItemCategory category;
    private double pricePerDay;
    private List<String> images;
    private String pickupLocation;
    private boolean isActive;
    private long createdAt;
    private long updatedAt;

    public Item() {
    }

    public Item(String id, String ownerId, String ownerName, double ownerRating,
                String title, String description, ItemCategory category, double pricePerDay,
                List<String> images, String pickupLocation, boolean isActive,
                long createdAt, long updatedAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.ownerRating = ownerRating;
        this.title = title;
        this.description = description;
        this.category = category;
        this.pricePerDay = pricePerDay;
        this.images = images;
        this.pickupLocation = pickupLocation;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public double getOwnerRating() { return ownerRating; }
    public void setOwnerRating(double ownerRating) { this.ownerRating = ownerRating; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ItemCategory getCategory() { return category; }
    public void setCategory(ItemCategory category) { this.category = category; }

    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public long getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(long updatedAt) { this.updatedAt = updatedAt; }
}
