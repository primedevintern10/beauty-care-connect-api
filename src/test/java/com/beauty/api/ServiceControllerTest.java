package com.beauty.api;

import com.beauty.api.collection.Service;
import com.beauty.api.controller.ServiceController;
import com.beauty.api.service.ServiceService;
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

public class ServiceControllerTest {

    @Mock
    private ServiceService serviceService;

    @InjectMocks
    private ServiceController serviceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllServices() {
        Service service1 = Service.builder()._id("1").name("Service 1").build();
        Service service2 = Service.builder()._id("2").name("Service 2").build();
        List<Service> services = Arrays.asList(service1, service2);

        when(serviceService.getAllServices()).thenReturn(services);

        ResponseEntity<List<Service>> response = serviceController.getAllServices();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(services, response.getBody());
    }

    @Test
    void testGetServiceById() {
        String serviceId = "1";
        Service service = Service.builder()._id(serviceId).name("Service 1").build();

        when(serviceService.getServiceById(serviceId)).thenReturn(Optional.of(service));

        ResponseEntity<Service> response = serviceController.getServiceById(serviceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(service, response.getBody());
    }

    @Test
    void testGetServiceByIdNotFound() {
        String serviceId = "1";

        when(serviceService.getServiceById(serviceId)).thenReturn(Optional.empty());

        ResponseEntity<Service> response = serviceController.getServiceById(serviceId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

