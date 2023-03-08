package com.pedroprior.ecommercespring.entities;

import com.pedroprior.ecommercespring.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart implements Serializable {
    private List<Product> productsList = new ArrayList<>();
    private BigDecimal subTotal;

    public void addProduct(List<Product> product) {
        productsList.addAll(product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(productsList);
    }

    public void removeQuantity(ProductRepository productRepository) {
        for (Product product : productsList) {
            product.removeQuantity(product.getQuantity());
            productRepository.save(product);
        }
    }


    public BigDecimal calculateSubtotal() {
        BigDecimal subTotal = BigDecimal.ZERO;
        for (Product product : productsList) {



            BigDecimal quantity = BigDecimal.valueOf(product.getQuantity());

            subTotal = subTotal.add((product.getPrice().multiply(quantity)));
        }
        this.subTotal = subTotal;
        return subTotal;
    }


}
