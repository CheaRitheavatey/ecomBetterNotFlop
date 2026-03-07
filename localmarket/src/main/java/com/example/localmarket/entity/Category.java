package com.example.localmarket.entity;

public enum Category {
    ALL_CATEGORY("All Categories"),
    FOOD_AND_BEVERAGE("Food & Beverage"),
    CLOTHES("Clothes"),
    GIFTS("Gifts & Crafts"),
    ElECTRONICS("Electronics"),
    HOME_AND_LIVING("Home & Living"),
    HEALTH_AND_BEAUTY("Health & Beauty"),
    AGRICULTURE("Agriculture");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
