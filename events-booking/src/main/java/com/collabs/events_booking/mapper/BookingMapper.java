package com.collabs.events_booking.mapper;

import com.collabs.events_booking.dto.responseDto.BookingResponseDto;
import com.collabs.events_booking.model.Booking;

public class BookingMapper {

    public static BookingResponseDto bookingToResponse(Booking booking){
        return BookingResponseDto.builder()
                .bookingId(booking.getId())
                .eventTitle(booking.getEvent().getTitle())
                .bookingTime(booking.getBookingTime())
                .customerName(booking.getUser().getName())
                .numberOfTickets(booking.getNoOfTickets())
                .paymentStatus(booking.getPaymentStatus())
                .status(booking.getBookingStatus())
                .totalAmount(booking.getTotalPrice())
                .build();
    }
}
