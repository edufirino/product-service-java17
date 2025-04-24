package com.bnp.productcosif.application.service;

import com.bnp.productcosif.domain.dto.ProductCosifDTO;
import com.bnp.productcosif.mapper.ProductCosifMapper;
import com.bnp.productcosif.domain.repository.ProductCosifRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCosifService {

    private final ProductCosifRepository repository;

    public ProductCosifService(ProductCosifRepository repository) {
        this.repository = repository;
    }

    public List<ProductCosifDTO> getAllProductCosifs() {
        return repository.findAll().stream()
                .map(ProductCosifMapper::toDTO)
                .collect(Collectors.toList());
    }
}
