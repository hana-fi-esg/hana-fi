package com.hana.esg.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "batch_integrity_run")
public class BatchIntegrityRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private Integer totalBlocks;
    private Boolean chainValid;
    private Long mismatchBlockIndex;

    @Column(length = 500)
    private String message;

    protected BatchIntegrityRun() {
    }

    public BatchIntegrityRun(
            LocalDateTime startedAt,
            LocalDateTime finishedAt,
            Integer totalBlocks,
            Boolean chainValid,
            Long mismatchBlockIndex,
            String message
    ) {
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.totalBlocks = totalBlocks;
        this.chainValid = chainValid;
        this.mismatchBlockIndex = mismatchBlockIndex;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public Integer getTotalBlocks() {
        return totalBlocks;
    }

    public Boolean getChainValid() {
        return chainValid;
    }

    public Long getMismatchBlockIndex() {
        return mismatchBlockIndex;
    }

    public String getMessage() {
        return message;
    }
}
