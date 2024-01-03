package com.beauty.api.controller;

import com.beauty.api.collection.Service;
import com.beauty.api.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @PostMapping
    public String save(@RequestBody Service service) {
        return serviceService.save(service);
    }

    @GetMapping
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public Optional<Service> getServiceById(@PathVariable("id") String serviceId) {
        return serviceService.getServiceById(serviceId);
    }

    @PutMapping("/{id}")
    public Service update(@PathVariable("id") String serviceId, @RequestBody Service service) {
        return serviceService.update(service, serviceId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String serviceId) {
        serviceService.delete(serviceId);
        return "Deleted Successfully";
    }
}
