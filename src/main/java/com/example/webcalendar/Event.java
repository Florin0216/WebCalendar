package com.example.webcalendar;

public class Event {
    private String name;
    private String date;
    private String location;

    public Event(String Name, String Date, String Location) {
        this.name = Name;
        this.date = Date;
        this.location = Location;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String Location) {
        this.location = Location;
    }
}
