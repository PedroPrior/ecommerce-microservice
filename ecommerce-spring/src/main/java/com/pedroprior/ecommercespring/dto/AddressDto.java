package com.pedroprior.ecommercespring.dto;

import com.pedroprior.ecommercespring.entities.Client;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AddressDto {

    private Long id;
    private String cep;
    private Integer houseNumber;
    private String complement;
    private String reference;
    private Client client;
}
