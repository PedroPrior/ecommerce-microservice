package com.pedroprior.ecommercespring.component;


import com.pedroprior.ecommercespring.entities.Client;
import com.pedroprior.ecommercespring.entities.UserEntity;
import com.pedroprior.ecommercespring.repositories.ClientRepository;
import com.pedroprior.ecommercespring.repositories.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuthenticated {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public UserAuthenticated(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    public Optional<Client> getClientAuthenticated() {

        Authentication authentication = returnAuth();
        String username = authentication.getName();


        Optional<UserEntity> userOptional = userRepository.findByUsername(username);

        UserEntity user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new RuntimeException("User not found");
        }

        return clientRepository.findByUserId(user.getId());
    }

    public Authentication returnAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }



}
