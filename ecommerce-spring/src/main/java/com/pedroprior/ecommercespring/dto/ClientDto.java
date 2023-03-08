package com.pedroprior.ecommercespring.dto;

import lombok.Data;


@Data
public class ClientDto {

    private Long id;
    private String name;
    private String cpf;
    private String phoneNumber;

}
