package com.collabs.events_booking.mapper;

import com.collabs.events_booking.dto.requestdto.EventDto;
import com.collabs.events_booking.dto.responseDto.EventResponseDto;
import com.collabs.events_booking.model.Event;

public class EventMapper {

    public static Event eventDtoToEntity(EventDto eventDto){
        return Event.builder()
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
                .venue(eventDto.getVenue())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .ticketPrice(eventDto.getTicketPrice())
                .totalSeats(eventDto.getTotalSeats())
                .build();
    }
    public static EventResponseDto eventToResponse(Event event){
        return EventResponseDto.builder()
                .title(event.getTitle())
                .description(event.getDescription())
                .venue(event.getVenue())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .ticketPrice(event.getTicketPrice())
                .totalSeats(event.getTotalSeats())
                .availableSeats(event.getAvailableSeats())
                .status(event.getStatus())
                .build();
    }
}
