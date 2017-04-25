package com.dminc.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dminc.event.domain.Event;
import com.dminc.event.service.EventService;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Event findById(@PathVariable String id) {
        return eventService.findById(id);
    }
    
    @RequestMapping( method = RequestMethod.POST)
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }
    
    @RequestMapping( method = RequestMethod.PUT)
    public Event saveEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Event> findAll() {
        return eventService.findAll();
    }

}
