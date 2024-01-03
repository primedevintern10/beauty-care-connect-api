package com.beauty.api.repository;

import com.beauty.api.collection.AppointmentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentStatusRepository extends MongoRepository<AppointmentStatus, String> {
}
