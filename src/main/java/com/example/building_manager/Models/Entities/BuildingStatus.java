package com.example.building_manager.Models.Entities;

public enum BuildingStatus {
    CANCELLED,
    IN_PROGRESS,
    COMPLETED;
    public static BuildingStatus fromValue(Short value) {
        if (value == null || value < 0 || value > 2) {
            throw new IllegalArgumentException("Trạng thái chỉ được nhận 0, 1 hoặc 2");
        }
        return values()[value];
    }
}
