package com.pedroprior.ecommercespring.impl;

import com.pedroprior.ecommercespring.component.UserAuthenticated;
import com.pedroprior.ecommercespring.dto.UserDto;
import com.pedroprior.ecommercespring.entities.UserEntity;
import com.pedroprior.ecommercespring.repositories.UserRepository;
import com.pedroprior.ecommercespring.services.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    private final UserAuthenticated userAuthenticated;


    public UserServiceImpl(UserRepository userRepository, UserAuthenticated userAuthenticated) {
        this.userRepository = userRepository;
        this.userAuthenticated = userAuthenticated;
    }

    private String getUserRole() {
        Authentication authentication = userAuthenticated.returnAuth();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return "ROLE_ADMIN";
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                return "ROLE_USER";
            } else if (authority.getAuthority().equals("ROLE_SUPPORT")) {
                return "ROLE_SUPPORT";
            }
        }
        return "UNKNOWN_ROLE";
    }


    @Override
    public UserDto getClientAuthenticated() {
        Authentication authentication = userAuthenticated.returnAuth();
        String username = authentication.getName();
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        UserEntity user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new RuntimeException("User not found");
        }
        String roleName = getUserRole();
        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), roleName);
        log.info("Authenticated user id: {}", userDto.getId());
        return userDto;
    }
}
