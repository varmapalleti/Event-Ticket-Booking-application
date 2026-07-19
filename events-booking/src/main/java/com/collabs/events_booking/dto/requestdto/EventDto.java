package com.collabs.events_booking.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.util.Date;

@Data
public class EventDto {

    @NotBlank(message = "title must be required")
    private String title;
    @NotBlank
    private String description;
    @NotBlank(message = "venue must be required")
    private String venue;
    @NotBlank(message = "startDate must be required")
    private Date startTime;
    @NotBlank(message = "endDate must be required")
    private Date endTime;
    private double ticketPrice;
    private int totalSeats;
    private int availableSeats;
}
