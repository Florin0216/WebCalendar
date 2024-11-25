package com.example.webcalendar.model;

import java.time.LocalDate;


public class ResponseMessage {
    private String message;
    private String event;
    private LocalDate date;

    public ResponseMessage(String message, String event, LocalDate date) {
        this.message = message;
        this.event = event;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
