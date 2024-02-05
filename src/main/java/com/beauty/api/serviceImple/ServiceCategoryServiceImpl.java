package com.beauty.api.serviceImple;

import com.beauty.api.collection.ServiceCategory;
import com.beauty.api.repository.ServiceCategoryRepository;
import com.beauty.api.service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService {
        @Autowired
        private ServiceCategoryRepository serviceCategoryRepository;

        @Override
        public ServiceCategory save(ServiceCategory serviceCategory) {
            return serviceCategoryRepository.save(serviceCategory);
        }

        @Override
        public List<ServiceCategory> getAllServiceCategories() {
            return serviceCategoryRepository.findAll();
        }

        @Override
        public Optional<ServiceCategory> getServiceCategoryById(String serviceCategoryId) {
            return serviceCategoryRepository.findById(serviceCategoryId);
        }

        @Override
        public boolean delete(String serviceCategoryId) {
            serviceCategoryRepository.deleteById(serviceCategoryId);
            return false;
        }

        @Override
        public ServiceCategory update(ServiceCategory serviceCategories, String serviceCategoriesId) {
            ServiceCategory existingServiceCategoriesData = serviceCategoryRepository.findById(serviceCategoriesId).orElse(null);

            if (existingServiceCategoriesData != null) {
                existingServiceCategoriesData.setName(serviceCategories.getName());

                return serviceCategoryRepository.save(existingServiceCategoriesData);
            } else {
                return null;
            }
        }
}
