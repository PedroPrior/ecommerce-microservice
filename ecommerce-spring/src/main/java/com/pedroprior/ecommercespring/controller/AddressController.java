package com.pedroprior.ecommercespring.controller;

import com.pedroprior.ecommercespring.dto.AddressDto;
import com.pedroprior.ecommercespring.entities.Address;
import com.pedroprior.ecommercespring.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/add")
    public ResponseEntity<Address> addAddress(@RequestBody AddressDto addressDto) {
        Address address = addressService.addAddress(addressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Address> alterAddress(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        Address address = addressService.alterAddress(id, addressDto);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAddress(@PathVariable Long id) {
        addressService.removeAddress(id);
        return ResponseEntity.status(HttpStatus.OK).body("Address was deleted.");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Address>> getAddressById(@PathVariable Long id) {
        Optional<Address> getAddress = addressService.getAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(getAddress);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<Address>> getAllCategories() {
        List<Address> AddressList = addressService.getAllAddresses();
        return ResponseEntity.status(HttpStatus.OK).body(AddressList);
    }

}
