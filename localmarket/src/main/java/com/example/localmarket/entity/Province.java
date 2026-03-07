package com.example.localmarket.entity;

public enum Province {
    TAKEO("Takeo"),
    KAMPOT("Kampot"),
    SIEM_REAP("Siem Reap"),
    PHNOM_PENH("Phnom Penh");

    private final String name;

    Province(String name) {
        this.name = name;
    }

    // show province name
    public String getName() {
        return name;
    }
}
