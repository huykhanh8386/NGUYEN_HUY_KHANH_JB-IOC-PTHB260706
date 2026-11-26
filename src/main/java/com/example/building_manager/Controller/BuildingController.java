package com.example.building_manager.Controller;

import com.example.building_manager.Models.DTO.Rep.BuildingRes;
import com.example.building_manager.Models.DTO.Rep.PageRes;
import com.example.building_manager.Models.DTO.Req.BuildingReq;
import com.example.building_manager.Models.DTO.Req.ChangeStatusReq;
import com.example.building_manager.Models.DTO.Wrapper.ApiResponse;
import com.example.building_manager.Models.Services.BuildingServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/buildings")
public class BuildingController {
    private final BuildingServices buildingServices;
    @GetMapping
    public ResponseEntity<ApiResponse<PageRes<BuildingRes>>> findAll(@RequestParam(defaultValue = "0") int page) {
        PageRes<BuildingRes> result = buildingServices.findAll(page);
        return ResponseEntity.ok(ApiResponse.ok(200, "Lấy danh sách tòa nhà thành công", result));
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageRes<BuildingRes>>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Short status,
            @RequestParam(defaultValue = "0") int page
    ) {
        PageRes<BuildingRes> result = buildingServices.search(name, status, page);
        return ResponseEntity.ok(ApiResponse.ok(200, "Tìm kiếm tòa nhà thành công", result));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BuildingRes>> findById(@Valid @PathVariable Long id) {
        BuildingRes result = buildingServices.findById(id);
        return ResponseEntity.ok(ApiResponse.ok(200, "Lấy chi tiết tòa nhà thành công", result));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<BuildingRes>> create(@Valid @RequestBody BuildingReq req) {
        BuildingRes result = buildingServices.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(201, "Thêm tòa nhà thành công", result));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BuildingRes>> update(@PathVariable Long id, @Valid @RequestBody BuildingReq req) {
        BuildingRes result = buildingServices.update(id, req);
        return ResponseEntity.ok(ApiResponse.ok(200, "Cập nhật tòa nhà thành công", result));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<BuildingRes>> changeStatus(@PathVariable Long id, @Valid @RequestBody ChangeStatusReq req) {
        BuildingRes result = buildingServices.changeStatus(id, req.status());
        return ResponseEntity.ok(ApiResponse.ok(200, "Thay đổi trạng thái thành công", result));
    }
}
