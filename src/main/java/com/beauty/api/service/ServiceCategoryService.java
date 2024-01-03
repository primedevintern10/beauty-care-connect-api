package com.beauty.api.service;

import com.beauty.api.collection.ServiceCategory;

import java.util.List;
import java.util.Optional;

public interface ServiceCategoryService {
    String save(ServiceCategory serviceCategories);

    List<ServiceCategory> getAllServiceCategories();

    Optional<ServiceCategory> getServiceCategoryById(String serviceCategoryId);

    void delete(String serviceCategoryId);

    ServiceCategory update(ServiceCategory serviceCategory, String serviceCategoryId);
}
