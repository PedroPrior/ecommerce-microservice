package com.pedroprior.ecommercespring.repositories;

import com.pedroprior.ecommercespring.entities.Address;
import com.pedroprior.ecommercespring.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByUserId(Long userId);


}
