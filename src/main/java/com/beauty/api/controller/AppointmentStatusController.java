package com.beauty.api.controller;

import com.beauty.api.collection.AppointmentStatus;
import com.beauty.api.service.AppointmentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointmentStatus")
public class AppointmentStatusController {
    @Autowired
    private AppointmentStatusService appointmentStatusService;

    @PostMapping
    public String save(@RequestBody AppointmentStatus appointmentStatus) {
        return appointmentStatusService.save(appointmentStatus);
    }

    @GetMapping
    public List<AppointmentStatus> getAllAppointmentStatuses() {
        return appointmentStatusService.getAllAppointmentStatuses();
    }

    @GetMapping("/{id}")
    public Optional<AppointmentStatus> getAppointmentStatusById(@PathVariable("id") String appointmentStatusId) {
        return appointmentStatusService.getAppointmentStatusById(appointmentStatusId);
    }

    @PutMapping("/{id}")
    public AppointmentStatus update(@PathVariable("id") String appointmentStatusId, @RequestBody AppointmentStatus appointmentStatus) {
        return appointmentStatusService.update(appointmentStatus, appointmentStatusId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String appointmentStatusId) {
        appointmentStatusService.delete(appointmentStatusId);
        return "Deleted Successfully";
    }
}
