package com.example.webcalendar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @GetMapping("/today")
    public ResponseEntity<List<Event>> getTodayEvent() {

        List<Event> events = new ArrayList<>();

        return ResponseEntity.ok(events);
    }
}