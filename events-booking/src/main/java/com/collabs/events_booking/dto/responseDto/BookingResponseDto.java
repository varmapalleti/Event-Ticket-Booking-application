package com.collabs.events_booking.dto.responseDto;

import com.collabs.events_booking.enums.BookingStatus;
import com.collabs.events_booking.enums.PaymentStatus;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingResponseDto {

    private Long bookingId;
    private String eventTitle;
    private String customerName;
    private Integer numberOfTickets;
    private Double totalAmount;
    private BookingStatus status;
    private LocalDateTime bookingTime;
    private PaymentStatus paymentStatus;
}
