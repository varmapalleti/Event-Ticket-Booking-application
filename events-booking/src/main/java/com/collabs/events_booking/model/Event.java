package com.collabs.events_booking.model;


import com.collabs.events_booking.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String venue;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double ticketPrice;
    private int totalSeats;
    private int availableSeats;
    
    private User user;
    @Enumerated(EnumType.STRING)
    private Status status;

}
