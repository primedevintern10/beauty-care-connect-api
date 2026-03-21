package com.beauty.api;

import com.beauty.api.collection.Client;
import com.beauty.api.controller.ClientController;
import com.beauty.api.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllClients() {
        Client client1 = Client.builder()._id("65915d83a9d7434601cd28b2").firstName("John Doe").build();
        Client client2 = Client.builder()._id("65c1146883e5c150c1480e6a").firstName("Jane Doe").build();
        List<Client> clients = Arrays.asList(client1, client2);

        when(clientService.getAllClients()).thenReturn(clients);

        List<Client> response = clientController.getAllClients();

        assertEquals(clients, response);
    }

    @Test
    void testGetClientById() {
        String clientId = "65915d83a9d7434601cd28b2";
        Client client = Client.builder()._id(clientId).firstName("John Doe").build();

        when(clientService.getClientById(clientId)).thenReturn(Optional.of(client));

        ResponseEntity<Client> response = clientController.getClientById(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(client, response.getBody());
    }

    @Test
    void testGetClientByIdNotFound() {
        String clientId = "65c1146883e5c150c1480e6a";

        when(clientService.getClientById(clientId)).thenReturn(Optional.empty());

        ResponseEntity<Client> response = clientController.getClientById(clientId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

