package com.example.building_manager.Models.DTO.Req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ChangeStatusReq(
        @NotNull(message = "Status không được để trống")
        @Min(0)
        @Max(2)
        Short status
) {
}
