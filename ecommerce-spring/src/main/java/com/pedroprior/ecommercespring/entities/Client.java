package com.pedroprior.ecommercespring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tb_client")

public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JsonIgnore
    private UserEntity user;
    private String cpf;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @JsonIgnore
    @OneToMany
    private List<Address> addressList;

    public Client() {

    }

}
