package com.bnp.manualtransaction.web;

import com.bnp.manualtransaction.application.service.ManualTransactionService;
import com.bnp.manualtransaction.domain.dto.ManualTransactionDTO;
import com.bnp.manualtransaction.domain.dto.ManualTransactionResponse;
import com.bnp.manualtransaction.domain.entity.ManualTransactionEntity;
import com.bnp.manualtransaction.mapper.ManualTransactionMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manual-transactions")
@CrossOrigin(origins = "*")
public class ManualTransactionController {

    private final ManualTransactionService service;

    public ManualTransactionController(ManualTransactionService service) {

        this.service = service;
    }

    @GetMapping
    public List<ManualTransactionResponse> getAll() {
        return service.findAllWithDescription();

    }

    @PostMapping
    public ResponseEntity<ManualTransactionDTO> create(@Valid @RequestBody ManualTransactionDTO dto) {
        ManualTransactionEntity entity = ManualTransactionMapper.toEntity(dto);
        ManualTransactionEntity saved = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(ManualTransactionMapper.toDTO(saved));
    }

}
