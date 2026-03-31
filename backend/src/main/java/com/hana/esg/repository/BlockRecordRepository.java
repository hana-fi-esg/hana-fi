package com.hana.esg.repository;

import com.hana.esg.domain.BlockRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRecordRepository extends JpaRepository<BlockRecord, Long> {
    BlockRecord findTopByOrderByBlockIndexDesc();

    List<BlockRecord> findAllByOrderByBlockIndexAsc();

    List<BlockRecord> findAllByOrderByBlockIndexDesc();
}
