package com.example.building_manager.Models.Respositories;

import com.example.building_manager.Models.Entities.BuildingDB;
import com.example.building_manager.Models.Entities.BuildingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BuildingRepository extends JpaRepository<BuildingDB, Long> {
    boolean existsByBuildingNameIgnoreCase(String buildingName);
    boolean existsByBuildingNameIgnoreCaseAndIdNot(String buildingName, Long id);
    Page<BuildingDB> findByBuildingNameContainingIgnoreCase(String name, Pageable pageable);
    Page<BuildingDB> findByStatus(BuildingStatus status, Pageable pageable);
    Page<BuildingDB> findByBuildingNameContainingIgnoreCaseAndStatus(String name, BuildingStatus status, Pageable pageable);
}
