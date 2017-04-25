package com.dminc.event.controller;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dminc.event.EventApplication;
import com.dminc.event.domain.Event;
import com.dminc.event.domain.Show;
import com.dminc.event.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EventApplication.class)
@WebAppConfiguration
public class EventControllerTest {

    @InjectMocks
    private EventController eventController;
    
    @Mock
    private EventService eventService;
    
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }
    
    @SneakyThrows
    @Test
    public void shouldCreateNewEvent() {
        Event event = Event.builder()
                    .description("Test event")
                    .externalTitle("External title")
                    .internalTitle("Internal Title")
                    .show(Show.builder().editionYear(2017).name("Basel").build())
                    .build();
        
        String jsonEvent = mapper.writeValueAsString(event);
        
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(jsonEvent))
        .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void shouldNotCreateInvalidShow() {
        mockMvc.perform(post("/events"))
        .andExpect(status().isBadRequest());

    }

    @Test
    @SneakyThrows
    public void shouldGetEventById() {
        Event event = Event.builder()
                .description("Test event")
                .externalTitle("External title")
                .internalTitle("Internal Title")
                .id("1")
                .show(Show.builder().editionYear(2017).name("Basel").build())
                .build();
        
        when(eventService.findById("1")).thenReturn(event);
        
        mockMvc.perform(get("/1"))
                .andExpect(jsonPath("$.description").value(event.getDescription()))
                .andExpect(jsonPath("$.internalTitle").value(event.getInternalTitle()))
                .andExpect(jsonPath("$.externalTitle").value(event.getExternalTitle()))
                .andExpect(status().isOk());
    }
    
    @Test
    @SneakyThrows
    public void shouldGetAllEvents() {
        
        List<Event> list = new ArrayList<>();
        list.add(Event.builder()
                .description("Test event1")
                .externalTitle("External Title1")
                .internalTitle("Internal Title1")
                .id("1")
                .show(Show.builder().editionYear(2017).name("Basel").build())
                .build());
        list.add(Event.builder()
                .description("Test event2")
                .externalTitle("External Title2")
                .internalTitle("Internal Title2")
                .id("2")
                .show(Show.builder().editionYear(2017).name("Basel").build())
                .build());
        
        when(eventService.findAll()).thenReturn(list);
        
        String url = "/";
        
        mockMvc.perform(get(url))
                .andExpect(jsonPath("$[0].description").value("Test event1"))
                .andExpect(jsonPath("$[0].internalTitle").value("Internal Title1"))
                .andExpect(jsonPath("$[0].externalTitle").value("External Title1"))
                .andExpect(jsonPath("$[1].description").value("Test event2"))
                .andExpect(jsonPath("$[1].internalTitle").value("Internal Title2"))
                .andExpect(jsonPath("$[1].externalTitle").value("External Title2"))
                .andExpect(status().isOk());
    }
}
