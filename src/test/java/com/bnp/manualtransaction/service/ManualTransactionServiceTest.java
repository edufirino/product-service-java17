package com.bnp.manualtransaction.service;

import com.bnp.manualtransaction.application.service.ManualTransactionService;
import com.bnp.manualtransaction.domain.entity.ManualTransactionEntity;
import com.bnp.manualtransaction.domain.entity.ManualTransactionId;
import com.bnp.manualtransaction.domain.repository.ManualTransactionRepository;
import com.bnp.productcosif.domain.repository.ProductCosifRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Transactional
class ManualTransactionServiceTest {

    private ManualTransactionRepository repository;
    private ProductCosifRepository productCosifRepository;
    private ManualTransactionService service;

    @BeforeEach
    void setUp() {
        repository = mock(ManualTransactionRepository.class);
        productCosifRepository = mock(ProductCosifRepository.class);
        service = new ManualTransactionService(repository, productCosifRepository);
    }

    @Test
    void saveManualTransaction_shouldSaveAndReturnEntity() {
        String productCode = "123";
        String cosifCode = "456";
        String description = "Test transaction";
        String userCode = "admin";
        BigDecimal amount = new BigDecimal("99.99");

        ManualTransactionEntity dtoFromClient = new ManualTransactionEntity(
                new ManualTransactionId(1, 2025, 1), // dummy ID
                productCode,
                cosifCode,
                description,
                null,
                userCode,
                amount
        );

        ManualTransactionEntity savedEntity = new ManualTransactionEntity(
                new ManualTransactionId(1, 2025, 1),
                productCode,
                cosifCode,
                description,
                LocalDateTime.now(),
                userCode,
                amount
        );

        when(productCosifRepository.existsByProductCodeAndCosifCode(productCode, cosifCode)).thenReturn(true);
        when(repository.findMaxEntryNumberByMonthAndYear(1, 2025)).thenReturn(Optional.empty());
        when(repository.save(any(ManualTransactionEntity.class))).thenReturn(savedEntity);

        ManualTransactionEntity result = service.save(dtoFromClient);

        assertNotNull(result);
        assertEquals("123", result.getProductCode());
        assertEquals("456", result.getCosifCode());
        assertEquals("Test transaction", result.getDescription());
        verify(repository, times(1)).save(any(ManualTransactionEntity.class));
    }

    @Test
    void saveManualTransaction_shouldThrowExceptionIfProductCosifNotFound() {
        String productCode = "123";
        String cosifCode = "456";

        ManualTransactionEntity dtoFromClient = new ManualTransactionEntity(
                new ManualTransactionId(1, 2025, 1),
                productCode,
                cosifCode,
                "Test transaction",
                null,
                "admin",
                new BigDecimal("99.99")
        );

        when(productCosifRepository.existsByProductCodeAndCosifCode(productCode, cosifCode)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.save(dtoFromClient));
        assertEquals("ProductCosif does not exist for the given productCode and cosifCode", exception.getMessage());
    }
}
