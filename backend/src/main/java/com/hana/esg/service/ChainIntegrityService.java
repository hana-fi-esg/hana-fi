package com.hana.esg.service;

import com.hana.esg.domain.BlockRecord;
import com.hana.esg.repository.BlockRecordRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChainIntegrityService {

    private final BlockRecordRepository blockRecordRepository;

    public ChainIntegrityService(BlockRecordRepository blockRecordRepository) {
        this.blockRecordRepository = blockRecordRepository;
    }

    @Transactional(readOnly = true)
    public IntegrityCheckResult evaluate() {
        List<BlockRecord> blocks = blockRecordRepository.findAllByOrderByBlockIndexAsc();
        if (blocks.isEmpty()) {
            return new IntegrityCheckResult(true, 0, null, "검사 대상 블록이 없습니다.");
        }

        String expectedPrev = sha256("GENESIS");
        for (BlockRecord block : blocks) {
            if (!expectedPrev.equals(block.getPrevHashFull())) {
                return new IntegrityCheckResult(
                        false,
                        blocks.size(),
                        block.getBlockIndex(),
                        "prev_hash 불일치"
                );
            }
            expectedPrev = block.getBlockHashFull();
        }

        return new IntegrityCheckResult(true, blocks.size(), null, "체인 무결성 정상");
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

    public record IntegrityCheckResult(
            boolean valid,
            int totalBlocks,
            Long mismatchBlockIndex,
            String message
    ) {
    }
}
