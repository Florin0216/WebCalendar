package com.example.webcalendar.service;

import com.example.webcalendar.model.Event;
import com.example.webcalendar.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getEvents(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null) {
            return eventRepository.findByDateBetween(startDate, endDate);
        } else {
            return eventRepository.findAll();
        }
    }

    public Optional<Event> getEventById(int id) {
        return eventRepository.findById(id);
    }

    public void deleteEventById(int id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getTodayEvents() {
        return eventRepository.findByDate(LocalDate.now());
    }
}
