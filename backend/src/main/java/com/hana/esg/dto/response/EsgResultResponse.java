package com.hana.esg.dto.response;

public record EsgResultResponse(
        int totalScore,
        int totalMax,
        String grade,
        int eScore,
        int eMax,
        int sScore,
        int sMax,
        int gScore,
        int gMax
) {
}
