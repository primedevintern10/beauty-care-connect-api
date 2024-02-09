package com.beauty.api;

import com.beauty.api.collection.Appointment;
import com.beauty.api.controller.AppointmentController;
import com.beauty.api.service.AppointmentService;
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
import static org.mockito.Mockito.when;

public class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllAppointments() {
        Appointment appointment1 = Appointment.builder()._id("1").note("Appointment 1").build();
        Appointment appointment2 = Appointment.builder()._id("2").note("Appointment 2").build();
        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        ResponseEntity<List<Appointment>> response = appointmentController.getAllAppointments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(appointments, response.getBody());
    }

    @Test
    void testGetAppointmentById() {
        String appointmentId = "1";
        Appointment appointment = Appointment.builder()._id(appointmentId).note("Appointment 1").build();

        when(appointmentService.getAppointmentById(appointmentId)).thenReturn(Optional.of(appointment));

        ResponseEntity<Appointment> response = appointmentController.getAppointmentById(appointmentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(appointment, response.getBody());
    }

    @Test
    void testGetAppointmentByIdNotFound() {
        String appointmentId = "1";

        when(appointmentService.getAppointmentById(appointmentId)).thenReturn(Optional.empty());

        ResponseEntity<Appointment> response = appointmentController.getAppointmentById(appointmentId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

