package com.hana.esg.dto.response;

import java.util.List;

public record LoanRecommendationResponse(
        String riskGrade,
        String interestAdjustment,
        String ltvLimit,
        String recommendation,
        List<String> conditions
) {
}
