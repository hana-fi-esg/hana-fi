package com.hana.esg.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetName;
    private String assetType;
    private Double floorArea;
    private Double vacancyRate;
    private Integer footTraffic;
    private Double energyUsage;
    private String transitAccess;
    private String greenCertification;
    private LocalDateTime createdAt;

    protected Asset() {
    }

    public Asset(
            String assetName,
            String assetType,
            Double floorArea,
            Double vacancyRate,
            Integer footTraffic,
            Double energyUsage,
            String transitAccess,
            String greenCertification
    ) {
        this.assetName = assetName;
        this.assetType = assetType;
        this.floorArea = floorArea;
        this.vacancyRate = vacancyRate;
        this.footTraffic = footTraffic;
        this.energyUsage = energyUsage;
        this.transitAccess = transitAccess;
        this.greenCertification = greenCertification;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getAssetName() {
        return assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public Double getFloorArea() {
        return floorArea;
    }

    public Double getVacancyRate() {
        return vacancyRate;
    }

    public Integer getFootTraffic() {
        return footTraffic;
    }

    public Double getEnergyUsage() {
        return energyUsage;
    }

    public String getTransitAccess() {
        return transitAccess;
    }

    public String getGreenCertification() {
        return greenCertification;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
