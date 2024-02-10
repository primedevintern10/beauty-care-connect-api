package com.beauty.api.controller;

import com.beauty.api.collection.Client;
import com.beauty.api.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Client> save(@RequestBody Client client) {
        Client savedClient = clientService.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Clients")
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @Operation(summary = "Get Client by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") String clientId) {
        Optional<Client> client = clientService.getClientById(clientId);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get Client by Email")
    @GetMapping("/by-email/{email}")
    public ResponseEntity<Client> getClientByEmail(@PathVariable("email") String email) {
        Optional<Client> client = clientService.getClientByEmail(email);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update a Client")
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") String clientId, @RequestBody Client client) {
        Client updatedClient = clientService.update(client, clientId);
        if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remove a Client")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String clientId) {
        boolean isDeleted = clientService.delete(clientId);
        if (isDeleted) {
            return new ResponseEntity<>("Client deleted successfully", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
    }
}
