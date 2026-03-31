package com.hana.esg.controller;

import com.hana.esg.domain.BatchIntegrityRun;
import com.hana.esg.repository.BatchIntegrityRunRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job integrityCheckJob;
    private final BatchIntegrityRunRepository batchIntegrityRunRepository;

    public BatchController(
            JobLauncher jobLauncher,
            Job integrityCheckJob,
            BatchIntegrityRunRepository batchIntegrityRunRepository
    ) {
        this.jobLauncher = jobLauncher;
        this.integrityCheckJob = integrityCheckJob;
        this.batchIntegrityRunRepository = batchIntegrityRunRepository;
    }

    @PostMapping("/integrity/run")
    public Map<String, Object> runIntegrityCheck() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLocalDateTime("requestedAt", LocalDateTime.now())
                .toJobParameters();

        JobExecution execution = jobLauncher.run(integrityCheckJob, params);
        return Map.of(
                "success", true,
                "jobExecutionId", execution.getId(),
                "status", execution.getStatus().toString()
        );
    }

    @GetMapping("/integrity/runs")
    public Map<String, Object> listIntegrityRuns() {
        List<BatchIntegrityRun> runs = batchIntegrityRunRepository.findTop20ByOrderByIdDesc();
        return Map.of(
                "success", true,
                "total", runs.size(),
                "runs", runs
        );
    }
}
