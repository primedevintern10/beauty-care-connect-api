package com.beauty.api;

import com.beauty.api.collection.ServiceCategory;
import com.beauty.api.controller.ServiceCategoryController;
import com.beauty.api.service.ServiceCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceCategoryControllerTest {

    @Mock
    private ServiceCategoryService serviceCategoryService;

    @InjectMocks
    private ServiceCategoryController serviceCategoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllServiceCategories() {
        ServiceCategory category1 = ServiceCategory.builder()._id("1").name("Category 1").build();
        ServiceCategory category2 = ServiceCategory.builder()._id("2").name("Category 2").build();
        List<ServiceCategory> categories = Arrays.asList(category1, category2);

        when(serviceCategoryService.getAllServiceCategories()).thenReturn(categories);

        ResponseEntity<List<ServiceCategory>> response = serviceCategoryController.getAllServiceCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }

    @Test
    void testGetServiceCategoryById() {
        String categoryId = "1";
        ServiceCategory category = ServiceCategory.builder()._id(categoryId).name("Category 1").build();

        when(serviceCategoryService.getServiceCategoryById(categoryId)).thenReturn(Optional.of(category));

        ResponseEntity<ServiceCategory> response = serviceCategoryController.getServiceCategoryById(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    void testGetServiceCategoryByIdNotFound() {
        String categoryId = "1";

        when(serviceCategoryService.getServiceCategoryById(categoryId)).thenReturn(Optional.empty());

        ResponseEntity<ServiceCategory> response = serviceCategoryController.getServiceCategoryById(categoryId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

