package com.beauty.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCount {
    private long totalCount;
    private long completedCount;
    private long pendingCount;
    private long confirmedCount;
}
