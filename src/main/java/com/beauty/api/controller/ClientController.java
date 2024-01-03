package com.beauty.api.controller;

import com.beauty.api.collection.Client;
import com.beauty.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public String save(@RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable("id") String clientId) {
        return clientService.getClientById(clientId);
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable("id") String clientId, @RequestBody Client client) {
        return clientService.update(client, clientId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String clientId) {
        clientService.delete(clientId);
        return "Deleted Successfully";
    }
}
