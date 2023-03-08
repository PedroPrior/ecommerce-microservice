package com.pedroprior.ecommercespring.config.security;

import com.pedroprior.ecommercespring.entities.UserEntity;
import com.pedroprior.ecommercespring.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Client Not Found with username: " + username));
        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true, userEntity.getAuthorities());

    }


}
