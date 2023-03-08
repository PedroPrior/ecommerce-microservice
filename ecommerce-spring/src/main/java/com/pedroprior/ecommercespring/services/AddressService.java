package com.pedroprior.ecommercespring.services;

import com.pedroprior.ecommercespring.dto.AddressDto;
import com.pedroprior.ecommercespring.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Address addAddress(AddressDto addressDto);
    Address alterAddress(Long id, AddressDto addressDto);
    void removeAddress(Long id);
    Optional<Address> getAddressById(Long id);
    List<Address> getAllAddresses();
}
