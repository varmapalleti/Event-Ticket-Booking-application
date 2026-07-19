package com.collabs.events_booking.controller;

import com.collabs.events_booking.dto.requestdto.EventDto;
import com.collabs.events_booking.dto.responseDto.EventResponseDto;
import com.collabs.events_booking.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDto> create(@Valid @RequestBody EventDto eventDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.create(eventDto));
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents(){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getAllEvents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDto> update(@PathVariable Long id,@Valid @RequestBody EventDto eventDto){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.update(id,eventDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.delete(id));
    }

}
