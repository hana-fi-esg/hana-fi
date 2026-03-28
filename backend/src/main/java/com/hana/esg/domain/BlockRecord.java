package com.hana.esg.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "block_record")
public class BlockRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long blockIndex;
    private Long assetId;
    private String assetName;
    private Integer totalScore;
    private String grade;

    @Column(length = 128)
    private String dataHashFull;

    @Column(length = 128)
    private String prevHashFull;

    @Column(length = 128)
    private String blockHashFull;

    private Long nonce;
    private LocalDateTime createdAt;

    protected BlockRecord() {
    }

    public BlockRecord(
            Long blockIndex,
            Long assetId,
            String assetName,
            Integer totalScore,
            String grade,
            String dataHashFull,
            String prevHashFull,
            String blockHashFull,
            Long nonce
    ) {
        this.blockIndex = blockIndex;
        this.assetId = assetId;
        this.assetName = assetName;
        this.totalScore = totalScore;
        this.grade = grade;
        this.dataHashFull = dataHashFull;
        this.prevHashFull = prevHashFull;
        this.blockHashFull = blockHashFull;
        this.nonce = nonce;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getBlockIndex() {
        return blockIndex;
    }

    public Long getAssetId() {
        return assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public String getGrade() {
        return grade;
    }

    public String getDataHashFull() {
        return dataHashFull;
    }

    public String getPrevHashFull() {
        return prevHashFull;
    }

    public String getBlockHashFull() {
        return blockHashFull;
    }

    public Long getNonce() {
        return nonce;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
