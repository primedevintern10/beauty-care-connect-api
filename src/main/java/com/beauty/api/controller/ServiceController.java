package com.beauty.api.controller;

import com.beauty.api.collection.Service;
import com.beauty.api.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Service> save(@RequestBody Service service) {
        Service savedService = serviceService.save(service);
        return new ResponseEntity<>(savedService, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Services")
    @GetMapping
    public ResponseEntity<List<Service>> getAllServices() {
        List<Service> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @Operation(summary = "Get Service by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable("id") String serviceId) {
        Optional<Service> service = serviceService.getServiceById(serviceId);
        return service.map(value -> ResponseEntity.ok(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a Service")
    @PutMapping("/{id}")
    public ResponseEntity<Service> update(@PathVariable("id") String serviceId, @RequestBody Service service) {
        Service updatedService = serviceService.update(service, serviceId);
        return (updatedService != null) ?
                ResponseEntity.ok(updatedService) :
                ResponseEntity.notFound().build();
    }

    @Operation(summary = "Remove a Service")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String serviceId) {
        boolean isDeleted = serviceService.delete(serviceId);
        if (isDeleted) {
            return ResponseEntity.ok("Service deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found");
        }
    }

    @Operation(summary = "Get Services by Category Id")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Service>> getServicesByCategory(@PathVariable String categoryId) {
        List<Service> services = serviceService.getServicesByCategoryId(categoryId);
        return ResponseEntity.ok(services);
    }
}
