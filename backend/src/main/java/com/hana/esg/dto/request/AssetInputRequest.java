package com.hana.esg.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssetInputRequest(
        @NotBlank String assetName,
        @NotBlank String assetType,
        @NotNull @Min(1) Double floorArea,
        @NotNull @Min(0) Double vacancyRate,
        @NotNull @Min(0) Integer footTraffic,
        @NotNull @Min(1) Double energyUsage,
        @NotBlank String transitAccess,
        @NotBlank String greenCertification
) {
}
