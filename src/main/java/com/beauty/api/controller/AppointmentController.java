package com.beauty.api.controller;

import com.beauty.api.collection.Appointment;
import com.beauty.api.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@Tag(name = "Appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Operation(summary = "Create an Appointment")
    @PostMapping
    public String save(@RequestBody Appointment appointment) {
        return appointmentService.save(appointment);
    }

    @Operation(summary = "Get All Appointments")
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @Operation(summary = "Get Appointment by ID")
    @GetMapping("/{id}")
    public Optional<Appointment> getAppointmentById(@PathVariable("id") String appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @Operation(summary = "Update an Appointment")
    @PutMapping("/{id}")
    public Appointment update(@PathVariable("id") String appointmentId, @RequestBody Appointment appointment) {
        return appointmentService.update(appointment, appointmentId);
    }

    @Operation(summary = "Remove an Appointment")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String appointmentId) {
        appointmentService.delete(appointmentId);
        return "Deleted Successfully";
    }
}
