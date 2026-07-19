package com.collabs.events_booking.serviceimplementation;

import com.collabs.events_booking.dto.requestdto.BookingRequestDto;
import com.collabs.events_booking.dto.responseDto.BookingCancelResponseDto;
import com.collabs.events_booking.dto.responseDto.BookingResponseDto;
import com.collabs.events_booking.enums.BookingStatus;
import com.collabs.events_booking.enums.PaymentStatus;
import com.collabs.events_booking.enums.Status;
import com.collabs.events_booking.exceptions.ResourceNotFound;
import com.collabs.events_booking.mapper.BookingMapper;
import com.collabs.events_booking.model.Booking;
import com.collabs.events_booking.model.Event;

import com.collabs.events_booking.model.User;
import com.collabs.events_booking.repository.BookingRepo;
import com.collabs.events_booking.repository.EventRepo;

import com.collabs.events_booking.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final EventRepo eventRepo;


    @Override
    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
        Event event = eventRepo.findById(bookingRequestDto.getEventId())
                .orElseThrow(() -> new ResourceNotFound("Event not found with id:  + id"));


        if (event.getStatus() != Status.ACTIVE) {
            throw new IllegalArgumentException("Event is not active");
        }
        // Validate Event Time
        if (event.getEndTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Event has already ended");
        }

        double totalPrice;

        // Validate Ticket Count
        if (bookingRequestDto.getNoOfTickets() <= 0) {
            throw new IllegalArgumentException("Number of tickets must be greater than zero");
        }
        // Validate Seat Availability
        if (event.getAvailableSeats() < bookingRequestDto.getNoOfTickets()) {
            throw new IllegalArgumentException("Not enough seats available");
        }

        User customer = new User();
        customer.setId(1L);

        double totalAmount =
                bookingRequestDto.getNoOfTickets() * event.getTicketPrice();

        // Create Booking
        Booking booking = Booking.builder()
                .event(event)
                .user(customer)
                .noOfTickets(bookingRequestDto.getNoOfTickets())
                .totalPrice(totalAmount)
                .bookingStatus(BookingStatus.BOOKED)
                .paymentStatus(PaymentStatus.SUCCESS) // Temporary
                .bookingTime(LocalDateTime.now())
                .build();

        // Update Available Seats
        event.setAvailableSeats(
                event.getAvailableSeats() - bookingRequestDto.getNoOfTickets()
        );

        // Save Event
        eventRepo.save(event);

        // Save Booking
        Booking savedBooking = bookingRepo.save(booking);

        // Return Response
        return BookingMapper.bookingToResponse(savedBooking);

    }

    @Override
    @Transactional
    public BookingCancelResponseDto cancelBooking(Long id) {

        // Find Booking
        Booking booking = findOrThrow(id);

        // Check if already cancelled
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new IllegalArgumentException("Booking is already cancelled.");
        }

        // Restore Event Seats
        Event event = booking.getEvent();
        event.setAvailableSeats(
                event.getAvailableSeats() + booking.getNoOfTickets()
        );

        // Update Booking Status
        booking.setBookingStatus(BookingStatus.CANCELLED);

        // Save Event
        eventRepo.save(event);

        // Save Booking
        bookingRepo.save(booking);

        // Prepare Response
        BookingCancelResponseDto responseDto = new BookingCancelResponseDto();
        responseDto.setEventTitle(event.getTitle());
        responseDto.setStatus(booking.getBookingStatus());

        return responseDto;
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return bookingRepo.findAll()
                .stream()
                .map(e -> BookingMapper.bookingToResponse(e))
                .toList();
    }

    private Booking findOrThrow(Long id){
        return bookingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Booking not found with id: " + id));
    }
}
