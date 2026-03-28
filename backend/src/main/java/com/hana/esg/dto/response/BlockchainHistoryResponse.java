package com.hana.esg.dto.response;

import java.util.List;

public record BlockchainHistoryResponse(
        boolean success,
        boolean chainIntegrity,
        int totalBlocks,
        List<BlockItemResponse> history
) {
}
