package com.hana.esg.dto.response;

public record BlockItemResponse(
        Long blockIndex,
        String timestamp,
        String assetName,
        Long assetId,
        int totalScore,
        String grade,
        String dataHash,
        String dataHashFull,
        String prevHash,
        String prevHashFull,
        String blockHash,
        String blockHashFull,
        long nonce
) {
}
