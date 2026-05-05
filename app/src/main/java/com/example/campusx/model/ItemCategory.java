package com.example.campusx.model;

public enum ItemCategory {
    ALL_ITEMS("All Items"),
    ELECTRONICS("Electronics"),
    STUDY_GEAR("Study Gear"),
    LIFESTYLE("Lifestyle");

    private final String displayName;

    ItemCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ItemCategory fromString(String text) {
        for (ItemCategory category : ItemCategory.values()) {
            if (category.displayName.equalsIgnoreCase(text)) {
                return category;
            }
        }
        return ALL_ITEMS;
    }
}
