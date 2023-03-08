package com.pedroprior.ecommercespring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "tb_address")
@Data
public class Address  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cep;
    private Integer houseNumber;
    private String complement;
    private String reference;

    @ManyToOne
    @JsonIgnore
    private Client client;


}
