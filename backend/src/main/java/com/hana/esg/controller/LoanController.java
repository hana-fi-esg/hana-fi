package com.hana.esg.controller;

import com.hana.esg.dto.request.AssetInputRequest;
import com.hana.esg.dto.response.EsgResultResponse;
import com.hana.esg.dto.response.LoanRecommendationResponse;
import com.hana.esg.service.EsgScoringService;
import com.hana.esg.service.LoanRecommendationService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    private final EsgScoringService esgScoringService;
    private final LoanRecommendationService loanRecommendationService;

    public LoanController(EsgScoringService esgScoringService, LoanRecommendationService loanRecommendationService) {
        this.esgScoringService = esgScoringService;
        this.loanRecommendationService = loanRecommendationService;
    }

    @PostMapping("/loan/recommendation")
    public Map<String, Object> recommendation(@RequestBody @Valid AssetInputRequest request) {
        EsgResultResponse esgResult = esgScoringService.calculate(request);
        LoanRecommendationResponse loanResult = loanRecommendationService.recommend(esgResult);
        return Map.of(
                "success", true,
                "assetName", request.assetName(),
                "esgResult", esgResult,
                "loanResult", loanResult
        );
    }
}
