package com.pedroprior.ecommercespring.repositories;

import com.pedroprior.ecommercespring.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {




}
