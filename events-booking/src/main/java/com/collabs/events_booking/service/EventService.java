package com.collabs.events_booking.service;

import com.collabs.events_booking.dto.requestdto.EventDto;
import com.collabs.events_booking.dto.responseDto.EventResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EventService {
    
     EventResponseDto create(EventDto eventDto);

    EventResponseDto update(Long id, EventDto eventDto);

    String delete(Long id);

    List<EventResponseDto> getAllEvents();
}
