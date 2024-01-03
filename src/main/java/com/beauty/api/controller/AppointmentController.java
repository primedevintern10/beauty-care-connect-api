package com.beauty.api.controller;

import com.beauty.api.collection.Appointment;
import com.beauty.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public String save(@RequestBody Appointment appointment) {
        return appointmentService.save(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Optional<Appointment> getAppointmentById(@PathVariable("id") String appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @PutMapping("/{id}")
    public Appointment update(@PathVariable("id") String appointmentId, @RequestBody Appointment appointment) {
        return appointmentService.update(appointment, appointmentId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String appointmentId) {
        appointmentService.delete(appointmentId);
        return "Deleted Successfully";
    }
}
