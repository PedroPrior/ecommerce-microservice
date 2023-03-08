package com.pedroprior.ecommercespring.services;

import com.pedroprior.ecommercespring.dto.ClientDto;
import com.pedroprior.ecommercespring.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client save(ClientDto clientDto);
    Client update(Long id, ClientDto clientDto);
    void delete(Long id);
    Optional<Client> getById(Long id);
    List<Client> getAllClients();




}
