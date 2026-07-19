package com.collabs.events_booking.controller;

import com.collabs.events_booking.dto.requestdto.BookingRequestDto;
import com.collabs.events_booking.dto.responseDto.BookingCancelResponseDto;
import com.collabs.events_booking.dto.responseDto.BookingResponseDto;
import com.collabs.events_booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {


    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto> creteBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(bookingRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingCancelResponseDto> cancelBooking(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.cancelBooking(id));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBookings(){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookings());
    }
}
