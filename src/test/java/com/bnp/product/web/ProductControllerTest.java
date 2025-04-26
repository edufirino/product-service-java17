package com.bnp.product.web;

import com.bnp.product.application.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@SpringBootTest
@EntityScan(basePackages = "com.bnp")
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getAllProducts_shouldReturnJsonArray() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].productCode").value("P001"))
                .andExpect(jsonPath("$[0].productDescription").value("Investment Fund"))
                .andExpect(jsonPath("$[0].status").value("A"))
                .andExpect(jsonPath("$[1].productCode").value("P002"))
                .andExpect(jsonPath("$[1].productDescription").value("Savings Account"))
                .andExpect(jsonPath("$[1].status").value("A"))
                .andExpect(jsonPath("$[2].productCode").value("P003"))
                .andExpect(jsonPath("$[2].productDescription").value("Mortgage Loan"))
                .andExpect(jsonPath("$[2].status").value("I"))
                .andExpect(jsonPath("$[3].productCode").value("P004"))
                .andExpect(jsonPath("$[3].productDescription").value("Auto Consortium"))
                .andExpect(jsonPath("$[3].status").value("I"));
    }

    @Test
    void getProductByCode_shouldReturnProduct_whenFound() throws Exception {
        mockMvc.perform(get("/api/products/P001"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productCode").value("P001"))
                .andExpect(jsonPath("$.productDescription").value("Investment Fund"))
                .andExpect(jsonPath("$.status").value("A"));
    }

    @Test
    void getProductByCode_shouldReturnNotFound_whenMissing() throws Exception {
        mockMvc.perform(get("/api/products/PR99"))
                .andExpect(status().isNotFound());
    }

}
