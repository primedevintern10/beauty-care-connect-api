package com.beauty.api.controller;

import com.beauty.api.collection.ServiceCategory;
import com.beauty.api.service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/serviceCategory")
public class ServiceCategoryController {
    @Autowired
    private ServiceCategoryService serviceCategoryService;

    @PostMapping
    public String save(@RequestBody ServiceCategory serviceCategory) {
        return serviceCategoryService.save(serviceCategory);
    }

    @GetMapping
    public List<ServiceCategory> getAllServiceCategories() {
        return serviceCategoryService.getAllServiceCategories();
    }

    @GetMapping("/{id}")
    public Optional<ServiceCategory> getServiceCategoryById(@PathVariable("id") String serviceCategoryId) {
        return serviceCategoryService.getServiceCategoryById(serviceCategoryId);
    }

    @PutMapping("/{id}")
    public ServiceCategory update(@PathVariable("id") String serviceCategoryId, @RequestBody ServiceCategory serviceCategory) {
        return serviceCategoryService.update(serviceCategory, serviceCategoryId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String serviceCategoryId) {
        serviceCategoryService.delete(serviceCategoryId);
        return "Deleted Successfully";
    }
}
