package com.beauty.api.controller;

import com.beauty.api.collection.AppointmentStatus;
import com.beauty.api.service.AppointmentStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointmentStatus")
@Tag(name = "Appointment Status ")
public class AppointmentStatusController {
    @Autowired
    private AppointmentStatusService appointmentStatusService;

    @Operation(summary = "Create Appointment Status")
    @PostMapping
    public ResponseEntity<AppointmentStatus> save(@RequestBody AppointmentStatus appointmentStatus) {
        AppointmentStatus savedStatus = appointmentStatusService.save(appointmentStatus);
        return new ResponseEntity<>(savedStatus, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Appointment Statuses")
    @GetMapping
    public List<AppointmentStatus> getAllAppointmentStatuses() {
        return appointmentStatusService.getAllAppointmentStatuses();
    }

    @Operation(summary = "Get Appointment Status by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentStatus> getAppointmentStatusById(@PathVariable("id") String appointmentStatusId) {
        Optional<AppointmentStatus> appointmentStatus = appointmentStatusService.getAppointmentStatusById(appointmentStatusId);
        return appointmentStatus.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update Appointment Status")
    @PutMapping("/{id}")
    public AppointmentStatus update(@PathVariable("id") String appointmentStatusId, @RequestBody AppointmentStatus appointmentStatus) {
        return appointmentStatusService.update(appointmentStatus, appointmentStatusId);
    }

    @Operation(summary = "Remove Appointment Status")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String appointmentStatusId) {
        boolean isDeleted = appointmentStatusService.delete(appointmentStatusId);
        if (isDeleted) {
            return new ResponseEntity<>("Appointment Status deleted successfully", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Appointment Status not found", HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
