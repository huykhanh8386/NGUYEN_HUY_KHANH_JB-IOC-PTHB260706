package com.example.building_manager.Models.DTO.Rep;

import com.example.building_manager.Models.Entities.BuildingDB;

import java.time.LocalDate;

public record BuildingRes(
        Long id,
        String buildingName,
        Double buildingArea,
        String areaUnit,
        LocalDate startDate,
        Integer time,
        String timeUnit,
        String design,
        String content,
        Short status
) {
    public static BuildingRes from(BuildingDB buildingDB) {
        return new BuildingRes(
                buildingDB.getId(),
                buildingDB.getBuildingName(),
                buildingDB.getBuildingArea(),
                buildingDB.getAreaUnit(),
                buildingDB.getStartDate(),
                buildingDB.getTime(),
                buildingDB.getTimeUnit(),
                buildingDB.getDesign(),
                buildingDB.getContent(),
                (short) buildingDB.getStatus().ordinal()
        );
    }
}
