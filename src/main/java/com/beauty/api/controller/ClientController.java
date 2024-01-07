package com.beauty.api.controller;

import com.beauty.api.collection.Client;
import com.beauty.api.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@Tag(name = "Clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Operation(summary = "Create a Client")
    @PostMapping
    public String save(@RequestBody Client client) {
        return clientService.save(client);
    }

    @Operation(summary = "Get All Clients")
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @Operation(summary = "Get Client by ID")
    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable("id") String clientId) {
        return clientService.getClientById(clientId);
    }

    @Operation(summary = "Update a Client")
    @PutMapping("/{id}")
    public Client update(@PathVariable("id") String clientId, @RequestBody Client client) {
        return clientService.update(client, clientId);
    }

    @Operation(summary = "Remove a Client")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String clientId) {
        clientService.delete(clientId);
        return "Deleted Successfully";
    }
}
