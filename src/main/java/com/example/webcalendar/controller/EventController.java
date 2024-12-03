package com.example.webcalendar.controller;

import com.example.webcalendar.model.Event;
import com.example.webcalendar.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public ResponseEntity<Map<String, ?>> addEvent(@RequestBody Event event) {
        if (event.getEvent() == null || event.getEvent().trim().isEmpty() || event.getDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "The event has been added!",
                "event", event.getEvent(),
                "date", event.getDate()
        ));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents(@RequestParam(name = "start_time", required = false) String start_time,
                                                 @RequestParam(name = "end_time", required = false) String end_time) {
        List<Event> events;

        if (start_time != null && end_time != null) {
            LocalDate startDate = LocalDate.parse(start_time);
            LocalDate endDate = LocalDate.parse(end_time);
            events = eventRepository.findByDateBetween(startDate, endDate);
        } else {
            events = eventRepository.findAll();
        }
        if (events.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable(name = "id") int id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "The event doesn't exist!"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(event.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEventById(@PathVariable(name = "id") int id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "The event doesn't exist!"));
        }
        eventRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(event.get());
    }

    @GetMapping("/today")
    public ResponseEntity<List<Event>> getTodayEvents() {
        List<Event> events = eventRepository.findByDate(LocalDate.now());
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }
}
