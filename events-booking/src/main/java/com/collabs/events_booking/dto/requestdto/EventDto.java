package com.collabs.events_booking.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;


@Data
public class EventDto {

    @NotBlank(message = "title must be required")
    private String title;
    @NotBlank
    private String description;
    @NotBlank(message = "venue must be required")
    private String venue;
    @NotNull(message = "startDate must be required")
    private LocalDateTime  startTime;
    @NotNull(message = "endDate must be required")
    private LocalDateTime endTime;
    private double ticketPrice;
    private int totalSeats;
}
