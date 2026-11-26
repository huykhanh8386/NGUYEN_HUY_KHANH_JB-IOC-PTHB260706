package com.example.building_manager.Models.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table (name = "buildings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuildingDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "building_name")
    private  String buildingName;
    @Column(name = "building_area")
    private Double buildingArea;
    @Column(name = "area_unit")
    private String areaUnit;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "time")
    private Integer time;
    @Column(name = "time_unit")
    private String timeUnit;
    @Column(name = "design")
    private String design;
    @Column(name = "content")
    private String content;
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", columnDefinition = "SMALLINT DEFAULT 1")
    private BuildingStatus status = BuildingStatus.IN_PROGRESS;
}
