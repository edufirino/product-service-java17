package com.bnp.manualtransaction.service;

import com.bnp.manualtransaction.application.service.ManualTransactionService;
import com.bnp.manualtransaction.domain.entity.ManualTransactionEntity;
import com.bnp.manualtransaction.domain.entity.ManualTransactionId;
import com.bnp.manualtransaction.domain.repository.ManualTransactionRepository;
import com.bnp.productcosif.domain.repository.ProductCosifRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class ManualTransactionServiceTest {

    @Autowired
    private ManualTransactionService service;

    @Autowired
    private ManualTransactionRepository repository;

    @Autowired
    private ProductCosifRepository productCosifRepository;

    @Test
    void saveManualTransaction_shouldSaveAndReturnEntity() {
        String productCode = "P001";
        String cosifCode = "COSIF123456";
        String description = "Integration test transaction";
        String userCode = "TEST";
        BigDecimal amount = new BigDecimal("123.45");

        assertTrue(productCosifRepository.existsByProductCodeAndCosifCode(productCode, cosifCode));

        ManualTransactionEntity dtoFromClient = new ManualTransactionEntity(
                new ManualTransactionId(4, 2025, 1),
                productCode,
                cosifCode,
                description,
                null,
                userCode,
                amount);

        ManualTransactionEntity result = service.save(dtoFromClient);

        assertNotNull(result);
        assertEquals(productCode, result.getProductCode());
        assertEquals(cosifCode, result.getCosifCode());
        assertEquals(description, result.getDescription());
        assertNotNull(result.getTransactionDate());
        assertTrue(result.getAmount().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void saveManualTransaction_shouldThrowExceptionIfProductCosifNotFound() {
        ManualTransactionEntity dtoFromClient = new ManualTransactionEntity(
                new ManualTransactionId(1, 2025, 1),
                "NONEXISTENT",
                "INVALID",
                "Test failure case",
                null,
                "TEST",
                new BigDecimal("10.00"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.save(dtoFromClient));
        assertEquals("Invalid ProductCosif combination", exception.getMessage());
    }
}
