package com.beauty.api.service;

import com.beauty.api.collection.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    String save(Client client);

    List<Client> getAllClients();

    Optional<Client> getClientById(String clientId);

    void delete(String clientId);

    Client update(Client client, String clientId);
}
