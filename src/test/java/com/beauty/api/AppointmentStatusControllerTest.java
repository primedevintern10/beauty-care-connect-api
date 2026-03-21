package com.beauty.api;

import com.beauty.api.collection.AppointmentStatus;
import com.beauty.api.controller.AppointmentStatusController;
import com.beauty.api.service.AppointmentStatusService;
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

public class AppointmentStatusControllerTest {

    @Mock
    private AppointmentStatusService appointmentStatusService;

    @InjectMocks
    private AppointmentStatusController appointmentStatusController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllAppointmentStatuses() {
        AppointmentStatus status1 = AppointmentStatus.builder()._id("1").status("Status 1").build();
        AppointmentStatus status2 = AppointmentStatus.builder()._id("2").status("Status 2").build();
        List<AppointmentStatus> statuses = Arrays.asList(status1, status2);

        when(appointmentStatusService.getAllAppointmentStatuses()).thenReturn(statuses);

        List<AppointmentStatus> result = appointmentStatusController.getAllAppointmentStatuses();

        assertEquals(statuses, result);
    }

    @Test
    void testGetAppointmentStatusById() {
        String statusId = "1";
        AppointmentStatus status = AppointmentStatus.builder()._id(statusId).status("Status").build();

        when(appointmentStatusService.getAppointmentStatusById(statusId)).thenReturn(Optional.of(status));

        ResponseEntity<AppointmentStatus> response = appointmentStatusController.getAppointmentStatusById(statusId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(status, response.getBody());
    }

    @Test
    void testGetAppointmentStatusByIdNotFound() {
        String statusId = "1";

        when(appointmentStatusService.getAppointmentStatusById(statusId)).thenReturn(Optional.empty());

        ResponseEntity<AppointmentStatus> response = appointmentStatusController.getAppointmentStatusById(statusId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

