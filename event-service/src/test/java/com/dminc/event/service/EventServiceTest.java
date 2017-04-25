package com.dminc.event.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.dminc.event.client.ShowServiceClient;
import com.dminc.event.domain.Event;
import com.dminc.event.domain.Show;
import com.dminc.event.repository.EventRepository;

public class EventServiceTest {

    @InjectMocks
    private EventServiceImpl eventService;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private ShowServiceClient showServiceClient;
 
    @Before
    public void setup() {
        initMocks(this);
    }
    
    @Test
    public void shouldFindById() {
        Event event = Event.builder()
                    .description("Description 1")
                    .externalTitle("External Title")
                    .internalTitle("Internal Title")
                    .id("1")
                    .build();
        
        when(eventService.findById("1")).thenReturn(event);
        Event found = eventService.findById("1");
        
        assertEquals(event, found);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenIdIsNull() {
        eventService.findById(null);
    }
    
    @Test
    public void shouldCreateNewEvent() {
        Event event = Event.builder()
                .description("Test event")
                .externalTitle("External title")
                .internalTitle("Internal Title")
                .id("1")
                .show(Show.builder().editionYear(2017).name("Basel").build())
                .build();
        
        event = eventService.createEvent(event);
        
        verify(eventRepository, times(1)).save(event);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenEventIsDuplicated() {
        Event event = Event.builder()
                .description("Test event")
                .externalTitle("External title")
                .internalTitle("Internal Title")
                .id("1")
                .show(Show.builder().editionYear(2017).name("Basel").build())
                .build();

        when(eventRepository.findByInternalTitleAndShow(event.getInternalTitle(), event.getShow())).thenReturn(Event.builder().build());
        
        eventService.createEvent(event);
    }
    
}
