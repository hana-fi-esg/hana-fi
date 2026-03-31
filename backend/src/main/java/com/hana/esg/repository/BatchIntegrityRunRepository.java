package com.hana.esg.repository;

import com.hana.esg.domain.BatchIntegrityRun;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchIntegrityRunRepository extends JpaRepository<BatchIntegrityRun, Long> {
    List<BatchIntegrityRun> findTop20ByOrderByIdDesc();
}
