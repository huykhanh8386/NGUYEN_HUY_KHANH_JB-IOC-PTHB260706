package com.example.building_manager.Models.DTO.Rep;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageRes<T>(
        List<T> items,
        int currentPage,
        int totalPages,
        long totalElements,
        int pageSize
) {
    public static <T> PageRes<T> from(Page<T> page) {
        return new PageRes<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize()
        );
    }
}
