package com.example.campusx.model;

public enum BookingStatus {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    ACTIVE("Active"),
    COMPLETED("Completed"),
    DECLINED("Declined"),
    CANCELLED("Cancelled");

    private final String displayName;

    BookingStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static BookingStatus fromString(String text) {
        for (BookingStatus status : BookingStatus.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return status;
            }
        }
        return PENDING;
    }
}
