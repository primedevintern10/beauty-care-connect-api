package com.beauty.api;

import com.beauty.api.collection.Branch;
import com.beauty.api.controller.BranchController;
import com.beauty.api.service.BranchService;
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

public class BranchControllerTest {

    @Mock
    private BranchService branchService;

    @InjectMocks
    private BranchController branchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllBranches() {
        Branch branch1 = Branch.builder()._id("1").name("Branch 1").build();
        Branch branch2 = Branch.builder()._id("2").name("Branch 2").build();
        List<Branch> branches = Arrays.asList(branch1, branch2);

        when(branchService.getAllBranches()).thenReturn(branches);

        ResponseEntity<List<Branch>> response = branchController.getAllBranches();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(branches, response.getBody());
    }

    @Test
    void testGetBranchById() {
        String branchId = "1";
        Branch branch = Branch.builder()._id(branchId).name("Branch 1").build();

        when(branchService.getBranchById(branchId)).thenReturn(Optional.of(branch));

        ResponseEntity<Branch> response = branchController.getBranchById(branchId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(branch, response.getBody());
    }

    @Test
    void testGetBranchByIdNotFound() {
        String branchId = "1";

        when(branchService.getBranchById(branchId)).thenReturn(Optional.empty());

        ResponseEntity<Branch> response = branchController.getBranchById(branchId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

