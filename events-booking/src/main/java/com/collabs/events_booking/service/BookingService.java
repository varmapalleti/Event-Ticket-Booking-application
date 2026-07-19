package com.collabs.events_booking.service;

import com.collabs.events_booking.dto.requestdto.BookingRequestDto;
import com.collabs.events_booking.dto.responseDto.BookingCancelResponseDto;
import com.collabs.events_booking.dto.responseDto.BookingResponseDto;

import java.util.List;

public interface BookingService {
    BookingResponseDto createBooking(BookingRequestDto bookingRequestDto);

    BookingCancelResponseDto cancelBooking(Long id);

    List<BookingResponseDto> getAllBookings();
}
