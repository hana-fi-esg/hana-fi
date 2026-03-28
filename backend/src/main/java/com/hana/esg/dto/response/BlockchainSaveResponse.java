package com.hana.esg.dto.response;

public record BlockchainSaveResponse(
        boolean success,
        Long assetId,
        EsgResultResponse esgResult,
        LoanRecommendationResponse loanResult,
        BlockItemResponse block
) {
}
