package com.collabs.events_booking.model;

import com.collabs.events_booking.dto.requestdto.EventDto;
import com.collabs.events_booking.enums.BookingStatus;
import com.collabs.events_booking.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private User user;

    private Integer noOfTickets;
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
