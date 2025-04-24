package com.bnp.productcosif.web;

import com.bnp.productcosif.application.service.ProductCosifService;
import com.bnp.productcosif.domain.dto.ProductCosifDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@SpringBootTest
@EntityScan(basePackages = "com.bnp")
@AutoConfigureMockMvc
class ProductCosifControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductCosifService service;

    @Test
    void shouldReturnAllProductCosifs() throws Exception {

        List<ProductCosifDTO> dtoList = service.getAllProductCosifs();

        mockMvc.perform(get("/api/products-cosif"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].cosifCode").value(dtoList.get(0).getCosifCode()))
                .andExpect(jsonPath("$[0].productCode").value(dtoList.get(0).getProductCode()))
                .andExpect(jsonPath("$[0].classificationCode").value(dtoList.get(0).getClassificationCode()))
                .andExpect(jsonPath("$[0].status").value(dtoList.get(0).getStatus()));
    }
}
