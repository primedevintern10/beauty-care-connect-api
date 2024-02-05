package com.beauty.api.controller;

import com.beauty.api.collection.ServiceCategory;
import com.beauty.api.service.ServiceCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/serviceCategory")
@Tag(name = "Service Categories")
public class ServiceCategoryController {
    @Autowired
    private ServiceCategoryService serviceCategoryService;

    @Operation(summary = "Create a Service Category")
    @PostMapping
    public ResponseEntity<ServiceCategory> save(@RequestBody ServiceCategory serviceCategory) {
        ServiceCategory savedServiceCategory = serviceCategoryService.save(serviceCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedServiceCategory);
    }

    @Operation(summary = "Get All Service Categories")
    @GetMapping
    public ResponseEntity<List<ServiceCategory>> getAllServiceCategories() {
        List<ServiceCategory> serviceCategories = serviceCategoryService.getAllServiceCategories();
        return ResponseEntity.ok(serviceCategories);
    }

    @Operation(summary = "Get Service Category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ServiceCategory> getServiceCategoryById(@PathVariable("id") String serviceCategoryId) {
        Optional<ServiceCategory> serviceCategory = serviceCategoryService.getServiceCategoryById(serviceCategoryId);
        return serviceCategory.map(value -> ResponseEntity.ok(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a Service Category")
    @PutMapping("/{id}")
    public ResponseEntity<ServiceCategory> update(@PathVariable("id") String serviceCategoryId,
                                                  @RequestBody ServiceCategory serviceCategory) {
        ServiceCategory updatedServiceCategory = serviceCategoryService.update(serviceCategory, serviceCategoryId);
        return (updatedServiceCategory != null) ?
                ResponseEntity.ok(updatedServiceCategory) :
                ResponseEntity.notFound().build();
    }

    @Operation(summary = "Remove a Service Category")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String serviceCategoryId) {
        boolean isDeleted = serviceCategoryService.delete(serviceCategoryId);
        if (isDeleted) {
            return ResponseEntity.ok("Service Category deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service Category not found");
        }
    }
}
