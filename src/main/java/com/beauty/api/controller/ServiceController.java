package com.beauty.api.controller;

import com.beauty.api.collection.Service;
import com.beauty.api.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
@Tag(name = "Services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @Operation(summary = "Create a Service")
    @PostMapping
    public String save(@RequestBody Service service) {
        return serviceService.save(service);
    }

    @Operation(summary = "Get All Services")
    @GetMapping
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @Operation(summary = "Get Service by ID")
    @GetMapping("/{id}")
    public Optional<Service> getServiceById(@PathVariable("id") String serviceId) {
        return serviceService.getServiceById(serviceId);
    }

    @Operation(summary = "Update a Service")
    @PutMapping("/{id}")
    public Service update(@PathVariable("id") String serviceId, @RequestBody Service service) {
        return serviceService.update(service, serviceId);
    }

    @Operation(summary = "Remove a Service")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String serviceId) {
        serviceService.delete(serviceId);
        return "Deleted Successfully";
    }
}
