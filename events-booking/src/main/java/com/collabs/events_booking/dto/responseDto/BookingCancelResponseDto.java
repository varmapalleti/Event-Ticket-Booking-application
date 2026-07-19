package com.collabs.events_booking.dto.responseDto;

import com.collabs.events_booking.enums.BookingStatus;
import com.collabs.events_booking.model.Event;
import lombok.Data;

@Data
public class BookingCancelResponseDto {

    private String eventTitle;
    private BookingStatus status;
}
