package com.pedroprior.ecommercespring.controller;

import com.pedroprior.ecommercespring.dto.UserDto;
import com.pedroprior.ecommercespring.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/auth")
    public ResponseEntity<UserDto> getClientAuthenticated() {

        UserDto userDto = userService.getClientAuthenticated();

        return ResponseEntity.ok().body(userDto);
    }
}
