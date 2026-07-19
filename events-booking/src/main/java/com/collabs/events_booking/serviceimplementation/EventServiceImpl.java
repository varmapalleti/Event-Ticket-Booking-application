package com.collabs.events_booking.serviceimplementation;

import com.collabs.events_booking.dto.requestdto.EventDto;
import com.collabs.events_booking.dto.responseDto.EventResponseDto;
import com.collabs.events_booking.enums.Status;
import com.collabs.events_booking.exceptions.ResourceNotFound;
import com.collabs.events_booking.mapper.EventMapper;
import com.collabs.events_booking.model.Event;
import com.collabs.events_booking.repository.EventRepo;
import com.collabs.events_booking.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;

    @Override
    public EventResponseDto create(EventDto eventDto) {

        if(!eventDto.getStartTime().isAfter(eventDto.getEndTime())){
            throw new IllegalArgumentException("End time must be after start time");
        }
       Event event = EventMapper.eventDtoToEntity(eventDto);
        event.setAvailableSeats(eventDto.getTotalSeats());
       event.setStatus(Status.ACTIVE);
        Event savedEvent = eventRepo.save(event);
        return EventMapper.eventToResponse(savedEvent);
    }

    @Override
    public EventResponseDto update(Long id, EventDto eventDto) {
       Event event = findOrThrow(id);
       event.setTitle(eventDto.getTitle());
       event.setDescription(eventDto.getDescription());
       event.setVenue(eventDto.getVenue());
       event.setEndTime(eventDto.getEndTime());
       event.setStartTime(eventDto.getStartTime());
       event.setTicketPrice(eventDto.getTicketPrice());
       event.setTotalSeats(eventDto.getTotalSeats());
        Event updatedEvent = eventRepo.save(event);

        return EventMapper.eventToResponse(updatedEvent);
    }

    @Override
    public String delete(Long id) {
        Event event = findOrThrow(id);
        event.setStatus(Status.CANCELLED);
        eventRepo.save(event);
        return "Event Cancelled Successfully";
    }

    @Override
    public List<EventResponseDto> getAllEvents() {
       return eventRepo.findAll()
               .stream()
               .map(e -> EventMapper.eventToResponse(e))
               .collect(Collectors.toList());
    }

    private Event findOrThrow(Long id){
        return eventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Event not found with id: " + id));
    }
}
