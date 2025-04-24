package com.bnp.productcosif.web;

import com.bnp.productcosif.application.service.ProductCosifService;
import com.bnp.productcosif.domain.dto.ProductCosifDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products-cosif")
@CrossOrigin
public class ProductCosifController {

    private final ProductCosifService service;

    public ProductCosifController(ProductCosifService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductCosifDTO> getAll() {
        return service.getAllProductCosifs();
    }
}
