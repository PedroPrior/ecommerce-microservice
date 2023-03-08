package com.pedroprior.ecommercespring.services;

import com.pedroprior.ecommercespring.dto.ProductDto;
import com.pedroprior.ecommercespring.entities.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductDto productDto);
    Product updateProduct(Long id, ProductDto productDto);
    void removeProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();

}
