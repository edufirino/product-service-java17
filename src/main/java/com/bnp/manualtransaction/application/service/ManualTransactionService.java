package com.bnp.manualtransaction.application.service;

import com.bnp.manualtransaction.domain.entity.ManualTransactionEntity;
import com.bnp.manualtransaction.domain.entity.ManualTransactionId;
import com.bnp.manualtransaction.domain.repository.ManualTransactionRepository;
import com.bnp.productcosif.domain.repository.ProductCosifRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManualTransactionService {

    private final ManualTransactionRepository repository;
    private final ProductCosifRepository productCosifRepository;

    public ManualTransactionService(ManualTransactionRepository repository,
                                    ProductCosifRepository productCosifRepository) {
        this.repository = repository;
        this.productCosifRepository = productCosifRepository;
    }

    public List<ManualTransactionEntity> findAll() {
        return repository.findAll();
    }

    @Transactional
    public ManualTransactionEntity save(@Valid ManualTransactionEntity dtoFromClient) {
        validateProductCosif(dtoFromClient.getProductCode(), dtoFromClient.getCosifCode());

        ManualTransactionId id = createTransactionId(
                dtoFromClient.getId().getMonth(),
                dtoFromClient.getId().getYear()
        );

        return repository.save(buildTransactionEntity(dtoFromClient, id));
    }

    private void validateProductCosif(String productCode, String cosifCode) {
        if (!productCosifRepository.existsByProductCodeAndCosifCode(productCode, cosifCode)) {
            throw new IllegalArgumentException("Invalid ProductCosif combination");
        }
    }

    private ManualTransactionId createTransactionId(Integer month, Integer year) {
        Integer nextEntry = repository.findMaxEntryNumberByMonthAndYear(month, year)
                .map(n -> n + 1)
                .orElse(1);

        return new ManualTransactionId(month, year, nextEntry);
    }

    private ManualTransactionEntity buildTransactionEntity(ManualTransactionEntity dto, ManualTransactionId id) {
        return new ManualTransactionEntity(
                id,
                dto.getProductCode(),
                dto.getCosifCode(),
                dto.getDescription(),
                dto.getTransactionDate() != null ? dto.getTransactionDate() : LocalDateTime.now(),
                dto.getUserCode(),
                dto.getAmount()
        );
    }
}