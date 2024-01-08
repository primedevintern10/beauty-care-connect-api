package com.beauty.api.controller;

import com.beauty.api.collection.AppointmentStatus;
import com.beauty.api.service.AppointmentStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String save(@RequestBody AppointmentStatus appointmentStatus) {
        return appointmentStatusService.save(appointmentStatus);
    }

    @Operation(summary = "Get All Appointment Statuses")
    @GetMapping
    public List<AppointmentStatus> getAllAppointmentStatuses() {
        return appointmentStatusService.getAllAppointmentStatuses();
    }

    @Operation(summary = "Get Appointment Status by ID")
    @GetMapping("/{id}")
    public Optional<AppointmentStatus> getAppointmentStatusById(@PathVariable("id") String appointmentStatusId) {
        return appointmentStatusService.getAppointmentStatusById(appointmentStatusId);
    }

    @Operation(summary = "Update Appointment Status")
    @PutMapping("/{id}")
    public AppointmentStatus update(@PathVariable("id") String appointmentStatusId, @RequestBody AppointmentStatus appointmentStatus) {
        return appointmentStatusService.update(appointmentStatus, appointmentStatusId);
    }

    @Operation(summary = "Remove Appointment Status")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String appointmentStatusId) {
        appointmentStatusService.delete(appointmentStatusId);
        return "Deleted Successfully";
    }
}
