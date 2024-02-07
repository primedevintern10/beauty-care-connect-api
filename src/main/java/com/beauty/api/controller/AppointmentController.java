package com.beauty.api.controller;

import com.beauty.api.collection.Appointment;
import com.beauty.api.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Appointment> save(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.save(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Appointments")
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @Operation(summary = "Get Appointment by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") String appointmentId) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(appointmentId);
        return appointment.map(value -> ResponseEntity.ok(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an Appointment")
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable("id") String appointmentId, @RequestBody Appointment appointment) {
        Appointment updatedAppointment = appointmentService.update(appointment, appointmentId);
        return (updatedAppointment != null) ?
                ResponseEntity.ok(updatedAppointment) :
                ResponseEntity.notFound().build();
    }

    @Operation(summary = "Remove an Appointment")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String appointmentId) {
        boolean isDeleted = appointmentService.delete(appointmentId);
        if (isDeleted) {
            return ResponseEntity.ok("Appointment deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
        }
    }

    @Operation(summary = "Get Appointments by Branch Id")
    @GetMapping("/byBranch/{branchId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByBranchId(@PathVariable String branchId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByBranchId(branchId);
        return ResponseEntity.ok(appointments);
    }
}
