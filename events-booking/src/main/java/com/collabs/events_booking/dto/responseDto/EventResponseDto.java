package com.collabs.events_booking.dto.responseDto;

import com.collabs.events_booking.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventResponseDto {

    private String title;
    private String description;
    private String venue;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double ticketPrice;
    private int totalSeats;
    private int availableSeats;
    private String organizerName;
    private Status status;
}
