package com.beauty.api.serviceImple;

import com.beauty.api.collection.Service;
import com.beauty.api.repository.ServiceRepository;
import com.beauty.api.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public String save(Service service) {
        return serviceRepository.save(service).getName();
    }

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<Service> getServiceById(String serviceId) {
        return serviceRepository.findById(serviceId);
    }

    @Override
    public void delete(String serviceId) {
        serviceRepository.deleteById(serviceId);
    }

    @Override
    public Service update(Service service, String serviceId) {
        Service existingServiceData = serviceRepository.findById(serviceId).orElse(null);

        if (existingServiceData != null) {
            existingServiceData.setName(service.getName());
            existingServiceData.setRequiredTime(service.getRequiredTime());
            existingServiceData.setIsEnabled(service.getIsEnabled());
            existingServiceData.setServiceCategory(service.getServiceCategory());

            return serviceRepository.save(existingServiceData);
        } else {
            return null;
        }
    }
}
