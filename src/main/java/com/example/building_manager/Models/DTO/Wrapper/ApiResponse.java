package com.example.building_manager.Models.DTO.Wrapper;

import java.util.List;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        List<String> errors,
        int httpStatus
) {
    public static <T> ApiResponse<T> ok(int status, String message, T data) {
        return new ApiResponse<T>(true, message, data, List.of(), status);
    }
    public static ApiResponse<Void> error(int status, String message, List<String> errors) {
        return new ApiResponse<>(false, message, null, errors, status);
    }
}
