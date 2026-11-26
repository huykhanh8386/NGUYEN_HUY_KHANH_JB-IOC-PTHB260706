
package com.example.building_manager.Models.Services.Impl;

import com.example.building_manager.Exception.BadRequestException;
import com.example.building_manager.Exception.ResourceNotFoundException;

import com.example.building_manager.Models.DTO.Req.BuildingReq;
import com.example.building_manager.Models.DTO.Rep.BuildingRes;
import com.example.building_manager.Models.DTO.Rep.PageRes;

import com.example.building_manager.Models.Entities.BuildingDB;
import com.example.building_manager.Models.Entities.BuildingStatus;

import com.example.building_manager.Models.Respositories.BuildingRepository;
import com.example.building_manager.Models.Services.BuildingServices;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BuildingServicesImpl implements BuildingServices {

    private final BuildingRepository buildingRepository;
    private static final int PAGE_SIZE = 5;
    @Override
    public PageRes<BuildingRes> findAll(int page) {
        Pageable pageable = getPageable(page);
        Page<BuildingDB> buildings = buildingRepository.findAll(pageable);
        Page<BuildingRes> result = buildings.map(BuildingRes::from);
        return PageRes.from(result);
    }

    @Override
    public BuildingRes findById(Long id) {
        BuildingDB building = getBuilding(id);
        return BuildingRes.from(building);
    }
    @Override
    public BuildingRes create(BuildingReq req) {
        String name = req.buildingName().trim();
        if (buildingRepository.existsByBuildingNameIgnoreCase(name)) {
            throw new BadRequestException("Tên tòa nhà đã tồn tại");
        }

        BuildingDB building = BuildingDB.builder()
                .buildingName(name)
                .buildingArea(req.buildingArea())
                .areaUnit(req.areaUnit().trim())
                .startDate(req.startDate())
                .time(req.time())
                .timeUnit(req.timeUnit().trim())
                .design(req.design().trim())
                .content(req.content().trim())
                .status(BuildingStatus.IN_PROGRESS)
                .build();

        BuildingDB saved = buildingRepository.saveAndFlush(building);
        return BuildingRes.from(saved);
    }

    @Override
    public BuildingRes update(Long id, BuildingReq req) {
        BuildingDB building = getBuilding(id);
        String name = req.buildingName().trim();
        if (buildingRepository.existsByBuildingNameIgnoreCaseAndIdNot(name, id)) {
            throw new BadRequestException("Tên tòa nhà đã tồn tại ở tòa nhà khác");
        }
        building.setBuildingName(name);
        building.setBuildingArea(req.buildingArea());
        building.setAreaUnit(req.areaUnit().trim());
        building.setStartDate(req.startDate());
        building.setTime(req.time());
        building.setTimeUnit(req.timeUnit().trim());
        building.setDesign(req.design().trim());
        building.setContent(req.content().trim());
        BuildingDB saved = buildingRepository.saveAndFlush(building);

        return BuildingRes.from(saved);
    }
    @Override
    public BuildingRes changeStatus(Long id, Short status) {
        BuildingDB building = getBuilding(id);
        if (building.getStatus() == BuildingStatus.COMPLETED) {
            throw new BadRequestException("Tòa nhà đã hoàn thành, không thể thay đổi trạng thái");
        }
        if (status == null || status < 0 || status > 2) {
            throw new BadRequestException("Trạng thái chỉ được nhận 0, 1 hoặc 2");
        }
        BuildingStatus newStatus = BuildingStatus.fromValue(status);
        building.setStatus(newStatus);
        BuildingDB saved = buildingRepository.saveAndFlush(building);
        return BuildingRes.from(saved);
    }

    @Override
    public PageRes<BuildingRes> search(String name, Short status, int page) {
        Pageable pageable = getPageable(page);
        if (status != null && (status < 0 || status > 2)) {
            throw new BadRequestException("Trạng thái tìm kiếm không hợp lệ");
        }
        boolean hasName = name != null && !name.isBlank();
        Page<BuildingDB> result;
        if (hasName && status != null) {
            result = buildingRepository.findByBuildingNameContainingIgnoreCaseAndStatus(name.trim(), BuildingStatus.fromValue(status), pageable);
        }
        else if (hasName) {
            result = buildingRepository.findByBuildingNameContainingIgnoreCase(name.trim(), pageable);

        }
        else if (status != null) {
            result = buildingRepository.findByStatus(BuildingStatus.fromValue(status), pageable);
        }
        else {
            result = buildingRepository.findAll(pageable);
        }
        Page<BuildingRes> dtoPage = result.map(BuildingRes::from);
        return PageRes.from(dtoPage);
    }
    private BuildingDB getBuilding(Long id) {
        return buildingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tòa nhà với ID: " + id));
    }
    private Pageable getPageable(int page) {
        if (page < 0) {
            throw new BadRequestException("Số trang phải lớn hơn hoặc bằng 0");
        }
        return PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.ASC, "id"));
    }
}
