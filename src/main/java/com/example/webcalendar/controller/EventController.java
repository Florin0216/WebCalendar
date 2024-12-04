package com.example.webcalendar.controller;

import com.example.webcalendar.model.Event;
import com.example.webcalendar.service.EventService;
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
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Map<String, ?>> addEvent(@RequestBody Event event) {
        if (event.getEvent() == null || event.getEvent().trim().isEmpty() || event.getDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Event savedEvent = eventService.addEvent(event);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "The event has been added!",
                "event", savedEvent.getEvent(),
                "date", savedEvent.getDate()
        ));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents(@RequestParam(name = "start_time", required = false) String start_time,
                                                 @RequestParam(name = "end_time", required = false) String end_time) {
        LocalDate startDate = null;
        LocalDate endDate = null;
        if (start_time != null && end_time != null) {
            startDate = LocalDate.parse(start_time);
            endDate = LocalDate.parse(end_time);
        }
        List<Event> events = eventService.getEvents(startDate, endDate);
        if (events.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEvent(@PathVariable(name = "id") int id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "The event doesn't exist!"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(event.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable(name = "id") int id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "The event doesn't exist!"));
        }
        eventService.deleteEventById(id);
        return ResponseEntity.status(HttpStatus.OK).body(event.get());
    }

    @GetMapping("/today")
    public ResponseEntity<List<Event>> getTodayEvent() {
        List<Event> events = eventService.getTodayEvents();
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }
}
