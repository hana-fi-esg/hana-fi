package com.hana.esg.service;

import com.hana.esg.domain.Asset;
import com.hana.esg.domain.BlockRecord;
import com.hana.esg.dto.request.AssetInputRequest;
import com.hana.esg.dto.response.*;
import com.hana.esg.repository.AssetRepository;
import com.hana.esg.repository.BlockRecordRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlockchainService {

    private final AssetRepository assetRepository;
    private final BlockRecordRepository blockRecordRepository;
    private final EsgScoringService esgScoringService;
    private final LoanRecommendationService loanRecommendationService;

    public BlockchainService(
            AssetRepository assetRepository,
            BlockRecordRepository blockRecordRepository,
            EsgScoringService esgScoringService,
            LoanRecommendationService loanRecommendationService
    ) {
        this.assetRepository = assetRepository;
        this.blockRecordRepository = blockRecordRepository;
        this.esgScoringService = esgScoringService;
        this.loanRecommendationService = loanRecommendationService;
    }

    @Transactional
    public BlockchainSaveResponse save(AssetInputRequest request) {
        Asset asset = assetRepository.save(new Asset(
                request.assetName(),
                request.assetType(),
                request.floorArea(),
                request.vacancyRate(),
                request.footTraffic(),
                request.energyUsage(),
                request.transitAccess(),
                request.greenCertification()
        ));

        EsgResultResponse esg = esgScoringService.calculate(request);
        LoanRecommendationResponse loan = loanRecommendationService.recommend(esg);

        BlockRecord last = blockRecordRepository.findTopByOrderByBlockIndexDesc();
        long nextIndex = last == null ? 1L : last.getBlockIndex() + 1L;
        String prevHashFull = last == null ? sha256("GENESIS") : last.getBlockHashFull();
        String dataHashFull = sha256(canonicalData(asset, esg));
        long nonce = 0L;
        String blockHashFull = sha256(nextIndex + "|" + prevHashFull + "|" + dataHashFull + "|" + nonce);

        BlockRecord savedBlock = blockRecordRepository.save(new BlockRecord(
                nextIndex,
                asset.getId(),
                asset.getAssetName(),
                esg.totalScore(),
                esg.grade(),
                dataHashFull,
                prevHashFull,
                blockHashFull,
                nonce
        ));

        return new BlockchainSaveResponse(
                true,
                asset.getId(),
                esg,
                loan,
                toBlockItem(savedBlock)
        );
    }

    @Transactional(readOnly = true)
    public BlockchainHistoryResponse history() {
        List<BlockRecord> blocks = blockRecordRepository.findAllByOrderByBlockIndexDesc();
        boolean integrity = isChainIntegrityValid(blocks);
        List<BlockItemResponse> history = blocks.stream().map(this::toBlockItem).toList();
        return new BlockchainHistoryResponse(true, integrity, history.size(), history);
    }

    private boolean isChainIntegrityValid(List<BlockRecord> blocks) {
        if (blocks.isEmpty()) {
            return true;
        }

        List<BlockRecord> ordered = blocks.stream()
                .sorted((a, b) -> Long.compare(a.getBlockIndex(), b.getBlockIndex()))
                .toList();

        String expectedPrev = sha256("GENESIS");
        for (BlockRecord block : ordered) {
            if (!expectedPrev.equals(block.getPrevHashFull())) {
                return false;
            }
            expectedPrev = block.getBlockHashFull();
        }
        return true;
    }

    private String canonicalData(Asset asset, EsgResultResponse esg) {
        return asset.getAssetName() + "|" +
                asset.getAssetType() + "|" +
                asset.getFloorArea() + "|" +
                asset.getVacancyRate() + "|" +
                asset.getFootTraffic() + "|" +
                asset.getEnergyUsage() + "|" +
                asset.getTransitAccess() + "|" +
                asset.getGreenCertification() + "|" +
                esg.totalScore() + "|" +
                esg.grade() + "|" +
                LocalDateTime.now();
    }

    private BlockItemResponse toBlockItem(BlockRecord block) {
        return new BlockItemResponse(
                block.getBlockIndex(),
                block.getCreatedAt().toString(),
                block.getAssetName(),
                block.getAssetId(),
                block.getTotalScore(),
                block.getGrade(),
                shortHash(block.getDataHashFull()),
                block.getDataHashFull(),
                shortHash(block.getPrevHashFull()),
                block.getPrevHashFull(),
                shortHash(block.getBlockHashFull()),
                block.getBlockHashFull(),
                block.getNonce()
        );
    }

    private String shortHash(String full) {
        if (full == null || full.length() < 16) {
            return full;
        }
        return full.substring(0, 16) + "...";
    }

    private String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}
