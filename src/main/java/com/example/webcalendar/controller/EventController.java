package com.example.webcalendar.controller;

import com.example.webcalendar.model.Event;
import com.example.webcalendar.model.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private List<Event> eventList = new ArrayList<>();

    @GetMapping("/today")
    public ResponseEntity<List<Event>> getTodayEvent() {
        List<Event> events = new ArrayList<>();
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<Object> addEvent(@RequestBody Event event) {
        if (event.getEvent() == null || event.getEvent().trim().isEmpty() || event.getDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        eventList.add(event);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("The event has been added!", event.getEvent(), event.getDate()));
    }
}