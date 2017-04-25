package com.dminc.event.service;

import java.util.List;

import com.dminc.event.domain.Event;

public interface EventService {

    List<Event> findAll();
    Event createEvent(Event event);
    Event saveEvent(Event event);
    Event findById(String id); 
}
