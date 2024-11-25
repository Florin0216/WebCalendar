package com.example.webcalendar.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Event {
    private String event;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Event() {
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
