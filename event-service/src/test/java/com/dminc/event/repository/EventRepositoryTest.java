package com.dminc.event.repository;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dminc.event.EventApplication;
import com.dminc.event.domain.Event;
import com.dminc.event.domain.Show;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EventApplication.class)
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;
    
    @Test
    public void createAndFindEvent() {
        Event event = Event.builder()
                            .internalTitle("Event Test")
                            .description("Description Test")
                            .show(Show.builder().editionYear(2017).name("Basel").build())
                            .build();
        
        eventRepository.save(event);
        
        Event foundEvent = eventRepository.findOne(event.getId());
        
        assertArrayEquals(new Object[]{foundEvent.getInternalTitle(), foundEvent.getDescription()}, new Object[]{event.getInternalTitle(), event.getDescription()});
    }
    
}
