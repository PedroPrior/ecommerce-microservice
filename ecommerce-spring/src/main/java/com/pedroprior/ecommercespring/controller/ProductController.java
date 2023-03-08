package com.pedroprior.ecommercespring.controller;

import com.pedroprior.ecommercespring.dto.ProductDto;
import com.pedroprior.ecommercespring.entities.Product;
import com.pedroprior.ecommercespring.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
        Product product = productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> alterProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = productService.updateProduct(id, productDto);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable Long id) {
        productService.removeProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("product was deleted.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok().body(productList);
    }



}
