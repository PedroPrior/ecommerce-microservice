package com.pedroprior.ecommercespring.impl;

import com.pedroprior.ecommercespring.dto.AddressDto;
import com.pedroprior.ecommercespring.entities.Address;
import com.pedroprior.ecommercespring.exceptions.AddressNotFoundException;
import com.pedroprior.ecommercespring.repositories.AddressRepository;
import com.pedroprior.ecommercespring.services.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Address addAddress(AddressDto addressDto) {

        Address address = modelMapper.map(addressDto, Address.class);
        log.info("POST - Add address");
        return addressRepository.save(address);
    }

    @Override
    public Address alterAddress(Long id, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty()) {
            throw new AddressNotFoundException(id);
        }

        Address address = optionalAddress.get();

        address.setCep(address.getCep());
        address.setComplement(address.getComplement());
        address.setReference(address.getReference());
        address.setHouseNumber(address.getHouseNumber());

        modelMapper.map(addressDto, address);
        log.info("PUT - Alter address id: {}", id);
        return addressRepository.save(address);
    }

    @Override
    public void removeAddress(Long id) {
        log.info("DELETE - Remove address id: {}", id);
        addressRepository.deleteById(id);

    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        log.info("GET - Address id: {}", id);
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> getAllAddresses() {
        log.info("GET - All addresses");
        return addressRepository.findAll();
    }
}
