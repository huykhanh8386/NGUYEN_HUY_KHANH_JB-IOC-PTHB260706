package com.example.building_manager.Models.Services;

import com.example.building_manager.Models.DTO.Rep.BuildingRes;
import com.example.building_manager.Models.DTO.Rep.PageRes;
import com.example.building_manager.Models.DTO.Req.BuildingReq;
import com.example.building_manager.Models.Entities.BuildingDB;

public interface BuildingServices {
    PageRes<BuildingRes> findAll(int page);
    BuildingRes findById(Long id);
    BuildingRes create(BuildingReq req);
    BuildingRes update(Long id, BuildingReq req);
    BuildingRes changeStatus(Long id, Short status);
    PageRes<BuildingRes> search(String name, Short status, int page);
}
