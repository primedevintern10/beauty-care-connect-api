package com.beauty.api.controller;

import com.beauty.api.collection.ServiceCategory;
import com.beauty.api.service.ServiceCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String save(@RequestBody ServiceCategory serviceCategory) {
        return serviceCategoryService.save(serviceCategory);
    }

    @Operation(summary = "Get All Service Categories")
    @GetMapping
    public List<ServiceCategory> getAllServiceCategories() {
        return serviceCategoryService.getAllServiceCategories();
    }

    @Operation(summary = "Get Service Category by ID")
    @GetMapping("/{id}")
    public Optional<ServiceCategory> getServiceCategoryById(@PathVariable("id") String serviceCategoryId) {
        return serviceCategoryService.getServiceCategoryById(serviceCategoryId);
    }

    @Operation(summary = "Update a Service Category")
    @PutMapping("/{id}")
    public ServiceCategory update(@PathVariable("id") String serviceCategoryId, @RequestBody ServiceCategory serviceCategory) {
        return serviceCategoryService.update(serviceCategory, serviceCategoryId);
    }

    @Operation(summary = "Remove a Service Category")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String serviceCategoryId) {
        serviceCategoryService.delete(serviceCategoryId);
        return "Deleted Successfully";
    }
}
