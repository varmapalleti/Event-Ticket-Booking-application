package com.collabs.events_booking.dto.requestdto;

import com.collabs.events_booking.model.Event;
import lombok.Data;

@Data
public class BookingRequestDto {

    private Long eventId;
    private Integer noOfTickets;
}
