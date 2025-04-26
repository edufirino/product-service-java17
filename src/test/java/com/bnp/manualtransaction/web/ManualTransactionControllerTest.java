package com.bnp.manualtransaction.web;

import com.bnp.manualtransaction.application.service.ManualTransactionService;
import com.bnp.manualtransaction.domain.dto.ManualTransactionDTO;
import com.bnp.manualtransaction.domain.entity.ManualTransactionEntity;
import com.bnp.manualtransaction.domain.entity.ManualTransactionId;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@SpringBootTest
@EntityScan(basePackages = "com.bnp")
@AutoConfigureMockMvc
@Transactional
class ManualTransactionControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ManualTransactionService manualTransactionService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void shouldCreateManualTransaction() throws Exception {
                ManualTransactionDTO dto = new ManualTransactionDTO(4, 2025, 1,
                                "P001", "COSIF123456", "Investment Fund Transaction",
                                LocalDateTime.of(2025, 4, 20, 12, 0, 0, 0),
                                "TEST", new BigDecimal("1000.0"));

                ManualTransactionId id = new ManualTransactionId(dto.getMonth(), dto.getYear(), dto.getEntryNumber());

                ManualTransactionEntity entity = new ManualTransactionEntity(
                                id, dto.getProductCode(), dto.getCosifCode(),
                                dto.getDescription(), dto.getTransactionDate(),
                                dto.getUserCode(), dto.getAmount());

                mockMvc.perform(post("/api/manual-transactions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.productCode").value("P001"))
                                .andExpect(jsonPath("$.cosifCode").value("COSIF123456"))
                                .andExpect(jsonPath("$.description").value("Investment Fund Transaction"))
                                .andExpect(jsonPath("$.transactionDate").value("2025-04-20T12:00:00"))
                                .andExpect(jsonPath("$.userCode").value("TEST"))
                                .andExpect(jsonPath("$.amount").value("1000.0"));
        }

}
