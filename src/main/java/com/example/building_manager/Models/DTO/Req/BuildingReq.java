
package com.example.building_manager.Models.DTO.Req;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record BuildingReq(
        @NotBlank(message = "Tên tòa nhà không được để trống")
        @Size(max = 100)
        String buildingName,
        @NotNull(message = "Diện tích không được để trống")
        @Positive
        Double buildingArea,
        @NotBlank(message = "Đơn vị diện tích không được để trống")
        @Size(max = 10)
        String areaUnit,
        @NotNull(message = "Ngày khởi công không được để trống")
        LocalDate startDate,
        @NotNull(message = "Thời gian không được để trống")
        @Positive
        Integer time,
        @NotBlank(message = "Đơn vị thời gian không được để trống")
        @Size(max = 10)
        String timeUnit,
        @NotBlank(message = "Đường dẫn thiết kế không được để trống")
        @Size(max = 255)
        String design,
        @NotBlank(message = "Nội dung không được để trống")
        @Size(max = 255)
        String content

) {
}
