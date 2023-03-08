package com.pedroprior.ecommercespring.impl;

import com.pedroprior.ecommercespring.dto.ProductDto;
import com.pedroprior.ecommercespring.entities.Product;
import com.pedroprior.ecommercespring.repositories.ProductRepository;
import com.pedroprior.ecommercespring.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Product addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        log.info("POST - Add product");
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }

        Product product = optionalProduct.get();

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setValidity(productDto.getValidity());

        log.info("PUT - Product id: {}", product.getId().toString());
        return productRepository.save(product);
    }

    @Override
    public void removeProduct(Long id) {
        log.info("DELETE - Product id: {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {

        Optional<Product> getProduct = productRepository.findById(id);

        if (getProduct.isEmpty()) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }

        log.info("GET - Product id: {}", id);
        return getProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        log.info("GET - All products");
        return productRepository.findAll();
    }
}
