package com.beauty.api.collection;

import com.beauty.api.models.Review;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Document(collection = "Appointment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointment {
    @Id
    private String _id;
    @DBRef
    private Client client;
    @DBRef
    private Company company;
    @DBRef
    private Branch branch;
    @DBRef
    private List<Service> services;
    private Date date;
    private String startTime;
    private String endTime;
    @DBRef
    private Employee assignee;
    @DBRef
    private AppointmentStatus status;
    private List<Review> reviews;
    private String note;
}
