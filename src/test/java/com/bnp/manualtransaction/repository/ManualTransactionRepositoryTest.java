package com.bnp.manualtransaction.repository;

import com.bnp.manualtransaction.domain.repository.ManualTransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@SpringBootTest
@EntityScan(basePackages = "com.bnp")
@Transactional
class ManualTransactionRepositoryTest {

    @Autowired
    private ManualTransactionRepository manualTransactionRepository;

    @Test
    void testFindMaxEntryNumberByMonthAndYear() {
        Integer testMonth = 4;
        Integer testYear = 2025;

        Optional<Integer> maxEntryNumber = manualTransactionRepository.findMaxEntryNumberByMonthAndYear(testMonth, testYear);

        assertTrue(maxEntryNumber.isPresent(), "Max entry number should be present.");
        Integer expectedMaxEntryNumber = 2;
        assertEquals(expectedMaxEntryNumber, maxEntryNumber.get(), "Max entry number should match the expected value.");
    }
}
