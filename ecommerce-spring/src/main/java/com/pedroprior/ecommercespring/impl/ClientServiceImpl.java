package com.pedroprior.ecommercespring.impl;

import com.pedroprior.ecommercespring.dto.ClientDto;
import com.pedroprior.ecommercespring.entities.Client;
import com.pedroprior.ecommercespring.exceptions.ClientNotFoundException;
import com.pedroprior.ecommercespring.repositories.ClientRepository;
import com.pedroprior.ecommercespring.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Client save(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        log.info("POST - Add client");
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, ClientDto clientDto) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if(optionalClient.isEmpty()) {
            throw new ClientNotFoundException();
        }

        Client client = optionalClient.get();

        client.setCpf(clientDto.getCpf());
        client.setPhoneNumber(clientDto.getPhoneNumber());

        modelMapper.map(clientDto, client);
        log.info("PUT - Alter client: {}", client.getId().toString());
        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        log.info("DELETE - Client: {}", id);
        clientRepository.deleteById(id);
    }

    @Override
    public Optional<Client> getById(Long id) {
        log.info("GET - Client: {}", id);
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAllClients() {
        log.info("GET - All clients");
        return clientRepository.findAll();
    }
}
