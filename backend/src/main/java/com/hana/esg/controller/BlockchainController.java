package com.hana.esg.controller;

import com.hana.esg.dto.request.AssetInputRequest;
import com.hana.esg.dto.response.BlockchainHistoryResponse;
import com.hana.esg.dto.response.BlockchainSaveResponse;
import com.hana.esg.service.BlockchainService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blockchain")
public class BlockchainController {

    private final BlockchainService blockchainService;

    public BlockchainController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @PostMapping("/save")
    public BlockchainSaveResponse save(@RequestBody @Valid AssetInputRequest request) {
        return blockchainService.save(request);
    }

    @GetMapping("/history")
    public BlockchainHistoryResponse history() {
        return blockchainService.history();
    }
}
