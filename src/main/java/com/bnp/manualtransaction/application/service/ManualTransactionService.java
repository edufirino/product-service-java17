package com.bnp.manualtransaction.application.service;

import com.bnp.manualtransaction.domain.dto.ManualTransactionResponse;
import com.bnp.manualtransaction.domain.entity.ManualTransactionEntity;
import com.bnp.manualtransaction.domain.entity.ManualTransactionId;
import com.bnp.manualtransaction.domain.repository.ManualTransactionRepository;
import com.bnp.manualtransaction.mapper.ManualTransactionMapper;
import com.bnp.product.domain.entity.ProductEntity;
import com.bnp.product.domain.repository.ProductRepository;
import com.bnp.productcosif.domain.repository.ProductCosifRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManualTransactionService {

    private final ManualTransactionRepository repository;
    private final ProductCosifRepository productCosifRepository;
    private final ProductRepository productRepository;

    public ManualTransactionService(ManualTransactionRepository repository,
                                    ProductCosifRepository productCosifRepository,
                                    ProductRepository productRepository) {
        this.repository = repository;
        this.productCosifRepository = productCosifRepository;
        this.productRepository = productRepository;
    }

    public List<ManualTransactionResponse> findAllWithDescription() {
        return repository.findAll().stream()
                .map(entity -> {
                    String productDescription = productRepository.findById(entity.getProductCode())
                            .map(ProductEntity::getProductDescription)
                            .orElse(null);
                    return ManualTransactionMapper.toResponseDTO(entity, productDescription);
                })
                .collect(Collectors.toList());
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