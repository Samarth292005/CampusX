package com.example.campusx.model;

public class Booking {
    private String id;
    private String itemId;
    private String itemTitle;
    private String itemImage;
    private String renterId;
    private String renterName;
    private String listerId;
    private String listerName;
    private long startDate;
    private long endDate;
    private double totalPrice;
    private BookingStatus status;
    private String otp;
    private String pickupLocation;
    private long createdAt;
    private long updatedAt;

    public Booking() {
    }

    public Booking(String id, String itemId, String itemTitle, String itemImage,
                   String renterId, String renterName, String listerId, String listerName,
                   long startDate, long endDate, double totalPrice, BookingStatus status,
                   String otp, String pickupLocation, long createdAt, long updatedAt) {
        this.id = id;
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemImage = itemImage;
        this.renterId = renterId;
        this.renterName = renterName;
        this.listerId = listerId;
        this.listerName = listerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.otp = otp;
        this.pickupLocation = pickupLocation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getItemTitle() { return itemTitle; }
    public void setItemTitle(String itemTitle) { this.itemTitle = itemTitle; }

    public String getItemImage() { return itemImage; }
    public void setItemImage(String itemImage) { this.itemImage = itemImage; }

    public String getRenterId() { return renterId; }
    public void setRenterId(String renterId) { this.renterId = renterId; }

    public String getRenterName() { return renterName; }
    public void setRenterName(String renterName) { this.renterName = renterName; }

    public String getListerId() { return listerId; }
    public void setListerId(String listerId) { this.listerId = listerId; }

    public String getListerName() { return listerName; }
    public void setListerName(String listerName) { this.listerName = listerName; }

    public long getStartDate() { return startDate; }
    public void setStartDate(long startDate) { this.startDate = startDate; }

    public long getEndDate() { return endDate; }
    public void setEndDate(long endDate) { this.endDate = endDate; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public long getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(long updatedAt) { this.updatedAt = updatedAt; }
}
