package com.dminc.event.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.dminc.event.client.ShowServiceClient;
import com.dminc.event.domain.Event;
import com.dminc.event.domain.Show;
import com.dminc.event.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private ShowServiceClient showServiceClient;
    
    @Override
    public List<Event> findAll() {
        List<Event> result = new ArrayList<>();
        eventRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    public Event createEvent(Event event) {
        Assert.notNull(event);
        Assert.notNull(event.getInternalTitle(), "Internal title cannot be empty");
        Assert.notNull(event.getShow(), "Show cannot be empty");
        
        Event existingEvent = eventRepository.findByInternalTitleAndShow(event.getInternalTitle(), event.getShow());
        
        Assert.isNull(existingEvent, "Event already exists");
        
        Show show = showServiceClient.incrementNumberOfEvents(event.getShow().getId(), 1);
        
        event.setShow(show);
        eventRepository.save(event);
        
        
        
        return event;
    }

    @Override
    public Event saveEvent(Event event) {
        Assert.notNull(event);
        Assert.notNull(event.getInternalTitle(), "Internal title cannot be empty");
        Assert.notNull(event.getShow(), "Show cannot be empty");
        
        
        Event existingEvent = eventRepository.findOne(event.getId());
        if (!existingEvent.getShow().getId().equals(event.getShow().getId())) {
            showServiceClient.incrementNumberOfEvents(existingEvent.getShow().getId(), -1);
            showServiceClient.incrementNumberOfEvents(event.getShow().getId(), 1);
        }
        
        eventRepository.save(event);
        
        return event;
    }

    @Override
    public Event findById(String id) {
        Assert.notNull(id);
        return eventRepository.findOne(id);
    }

}
