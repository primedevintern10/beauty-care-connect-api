package com.beauty.api.service;

import com.beauty.api.collection.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    Service save(Service service);

    List<Service> getAllServices();

    Optional<Service> getServiceById(String serviceId);

    boolean delete(String serviceId);

    Service update(Service service, String serviceId);
}
