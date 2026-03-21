package com.beauty.api.serviceImple;

import com.beauty.api.collection.Client;
import com.beauty.api.repository.ClientRepository;
import com.beauty.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(String clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    public boolean delete(String clientId) {
        if (!clientRepository.existsById(clientId)) {
            return false;
        }

        clientRepository.deleteById(clientId);
        return true;
    }

    @Override
    public Client update(Client client, String clientId) {
        Client existingClientData = clientRepository.findById(clientId).orElse(null);

        if (existingClientData != null) {
            existingClientData.setFirstName(client.getFirstName());
            existingClientData.setLastName(client.getLastName());
            existingClientData.setPhoneNumber(client.getPhoneNumber());
            existingClientData.setEmail(client.getEmail());
            existingClientData.setIsAnonymous(client.getIsAnonymous());
            existingClientData.setAddress(client.getAddress());

            return clientRepository.save(existingClientData);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public long getTotalClientCount() {
        return clientRepository.count();
    }
}
