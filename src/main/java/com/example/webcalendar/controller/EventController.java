package com.example.webcalendar.controller;

import com.example.webcalendar.model.Event;

import com.example.webcalendar.model.ResponseMessage;
import org.springframework.http.HttpStatus;
import com.example.webcalendar.repository.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public ResponseEntity<Object> addEvent(@RequestBody Event event) {
        if (event.getEvent() == null || event.getEvent().trim().isEmpty() || event.getDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        Event savedEvent = eventRepository.save(event);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("The event has been added!", event.getEvent(), event.getDate()));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = eventRepository.findAll();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/today")
    public ResponseEntity<List<Event>> getTodayEvent() {
        List<Event> events = eventRepository.findByDate(LocalDate.now());
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}