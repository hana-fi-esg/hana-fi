package com.hana.esg.service;

import com.hana.esg.dto.response.EsgResultResponse;
import com.hana.esg.dto.response.LoanRecommendationResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LoanRecommendationService {

    public LoanRecommendationResponse recommend(EsgResultResponse esg) {
        return switch (esg.grade()) {
            case "A" -> new LoanRecommendationResponse(
                    "LOW",
                    "-0.40%",
                    "75%",
                    "우대 승인",
                    List.of("친환경 보고서 연 1회 제출", "에너지 사용량 모니터링 유지")
            );
            case "B" -> new LoanRecommendationResponse(
                    "MEDIUM",
                    "-0.20%",
                    "70%",
                    "조건부 승인",
                    List.of("6개월 내 ESG 개선 계획 제출")
            );
            case "C" -> new LoanRecommendationResponse(
                    "MEDIUM-HIGH",
                    "0.00%",
                    "65%",
                    "보통 심사",
                    List.of("추가 담보 검토", "운영 개선 계획 필요")
            );
            default -> new LoanRecommendationResponse(
                    "HIGH",
                    "+0.30%",
                    "55%",
                    "보수 심사",
                    List.of("리스크 개선 후 재심사 권고")
            );
        };
    }
}
