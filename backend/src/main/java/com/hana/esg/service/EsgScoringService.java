package com.hana.esg.service;

import com.hana.esg.dto.request.AssetInputRequest;
import com.hana.esg.dto.response.EsgResultResponse;
import org.springframework.stereotype.Service;

@Service
public class EsgScoringService {

    public EsgResultResponse calculate(AssetInputRequest request) {
        int eScore = 0;
        int sScore = 0;
        int gScore = 0;

        if (request.energyUsage() <= 100) {
            eScore += 25;
        } else if (request.energyUsage() <= 150) {
            eScore += 18;
        } else {
            eScore += 10;
        }

        if (request.greenCertification().contains("최우수")) {
            eScore += 20;
        } else if (request.greenCertification().contains("우수")) {
            eScore += 14;
        } else {
            eScore += 8;
        }

        if (request.transitAccess().contains("매우")) {
            sScore += 15;
        } else if (request.transitAccess().contains("우수")) {
            sScore += 10;
        } else {
            sScore += 6;
        }

        if (request.footTraffic() >= 100000) {
            sScore += 15;
        } else if (request.footTraffic() >= 50000) {
            sScore += 10;
        } else {
            sScore += 6;
        }

        if (request.vacancyRate() <= 3.0) {
            gScore += 20;
        } else if (request.vacancyRate() <= 7.0) {
            gScore += 14;
        } else {
            gScore += 8;
        }

        if (request.assetType().contains("오피스") || request.assetType().contains("복합")) {
            gScore += 5;
        } else if (request.assetType().contains("리테일")) {
            gScore += 3;
        } else {
            gScore += 2;
        }

        int total = eScore + sScore + gScore;
        String grade;
        if (total >= 80) {
            grade = "A";
        } else if (total >= 65) {
            grade = "B";
        } else if (total >= 50) {
            grade = "C";
        } else {
            grade = "D";
        }

        return new EsgResultResponse(
                total,
                100,
                grade,
                eScore,
                45,
                sScore,
                30,
                gScore,
                25
        );
    }
}
