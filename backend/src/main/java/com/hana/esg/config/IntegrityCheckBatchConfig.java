package com.hana.esg.config;

import com.hana.esg.domain.BatchIntegrityRun;
import com.hana.esg.repository.BatchIntegrityRunRepository;
import com.hana.esg.service.ChainIntegrityService;
import java.time.LocalDateTime;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class IntegrityCheckBatchConfig {

    @Bean
    public Job integrityCheckJob(JobRepository jobRepository, Step integrityCheckStep) {
        return new JobBuilder("integrityCheckJob", jobRepository)
                .start(integrityCheckStep)
                .build();
    }

    @Bean
    public Step integrityCheckStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ChainIntegrityService chainIntegrityService,
            BatchIntegrityRunRepository batchIntegrityRunRepository
    ) {
        return new StepBuilder("integrityCheckStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    LocalDateTime startedAt = LocalDateTime.now();
                    ChainIntegrityService.IntegrityCheckResult result = chainIntegrityService.evaluate();
                    LocalDateTime finishedAt = LocalDateTime.now();

                    batchIntegrityRunRepository.save(new BatchIntegrityRun(
                            startedAt,
                            finishedAt,
                            result.totalBlocks(),
                            result.valid(),
                            result.mismatchBlockIndex(),
                            result.message()
                    ));
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
