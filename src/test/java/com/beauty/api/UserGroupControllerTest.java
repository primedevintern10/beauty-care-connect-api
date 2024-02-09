package com.beauty.api;

import com.beauty.api.collection.UserGroup;
import com.beauty.api.controller.UserGroupController;
import com.beauty.api.service.UserGroupService;
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

public class UserGroupControllerTest {

    @Mock
    private UserGroupService userGroupService;

    @InjectMocks
    private UserGroupController userGroupController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUserGroups() {
        UserGroup userGroup1 = UserGroup.builder()._id("1").name("Group 1").build();
        UserGroup userGroup2 = UserGroup.builder()._id("2").name("Group 2").build();
        List<UserGroup> userGroups = Arrays.asList(userGroup1, userGroup2);

        when(userGroupService.getAllUserGroups()).thenReturn(userGroups);

        ResponseEntity<List<UserGroup>> response = userGroupController.getAllUserGroups();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userGroups, response.getBody());
    }

    @Test
    void testGetUserGroupById() {
        String userGroupId = "1";
        UserGroup userGroup = UserGroup.builder()._id(userGroupId).name("Group 1").build();

        when(userGroupService.getUserGroupById(userGroupId)).thenReturn(Optional.of(userGroup));

        ResponseEntity<UserGroup> response = userGroupController.getUserGroupById(userGroupId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userGroup, response.getBody());
    }

    @Test
    void testGetUserGroupByIdNotFound() {
        String userGroupId = "1";

        when(userGroupService.getUserGroupById(userGroupId)).thenReturn(Optional.empty());

        ResponseEntity<UserGroup> response = userGroupController.getUserGroupById(userGroupId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

