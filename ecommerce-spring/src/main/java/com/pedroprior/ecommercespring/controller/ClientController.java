package com.pedroprior.ecommercespring.controller;

import com.pedroprior.ecommercespring.dto.ClientDto;
import com.pedroprior.ecommercespring.entities.Client;
import com.pedroprior.ecommercespring.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/clients")
public class ClientController {


    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody ClientDto clientDto) {
        Client client = clientService.save(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> alterClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        Client client = clientService.update(id, clientDto);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client was deleted.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> getClientById(@PathVariable Long id) {
        Optional<Client> getClient = clientService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(getClient);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clientList = clientService.getAllClients();
        return ResponseEntity.status(HttpStatus.OK).body(clientList);
    }


}
