package com.bnp.product.web;

import com.bnp.product.application.service.ProductService;
import com.bnp.product.domain.dto.ProductDTO;
import com.bnp.product.mapper.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;
    private final DataSource dataSource;


    public ProductController(ProductService productService,DataSource dataSource) {
        this.productService = productService;
        this.dataSource = dataSource;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<ProductDTO> getProductByCode(@PathVariable String productCode) {
        return productService.findByProductCode(productCode)
                .map(ProductMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
