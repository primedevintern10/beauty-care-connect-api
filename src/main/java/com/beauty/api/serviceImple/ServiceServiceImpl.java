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
    public Service save(Service service) {
        return serviceRepository.save(service);
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
    public boolean delete(String serviceId) {
        serviceRepository.deleteById(serviceId);
        return false;
    }

    @Override
    public Service update(Service service, String serviceId) {
        Service existingServiceData = serviceRepository.findById(serviceId).orElse(null);

        if (existingServiceData != null) {
            existingServiceData.setName(service.getName());
            existingServiceData.setRequiredTime(service.getRequiredTime());
            existingServiceData.setIsEnabled(service.getIsEnabled());
            existingServiceData.setServiceCategory(service.getServiceCategory());
            existingServiceData.setBranch(service.getBranch());

            return serviceRepository.save(existingServiceData);
        } else {
            return null;
        }
    }

    @Override
    public List<Service> getServicesByCategoryId(String categoryId) {
        return serviceRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Service> getServicesByBranchId(String branchId) {
        return serviceRepository.findByBranchId(branchId);
    }

}
