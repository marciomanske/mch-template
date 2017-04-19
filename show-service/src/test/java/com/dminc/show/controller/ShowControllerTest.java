package com.dminc.show.controller;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.dminc.show.ShowApplication;
import com.dminc.show.domain.Show;
import com.dminc.show.service.ShowService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShowApplication.class)
@WebAppConfiguration
public class ShowControllerTest {

    @InjectMocks
    private ShowController showController;
    
    @Mock
    private ShowService showService;
    
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(showController).build();
    }
    
    @Test
    @SneakyThrows
    public void shouldGetShowByNameAndYear() {
        final Show show = Show.builder()
                    .name("Basel").editionYear(2017)
                    .build();
        

        when(showService.findByNameAndYear(show.getName(), show.getEditionYear())).thenReturn(show);

        String url = String.format("/%s/%d", show.getName(), show.getEditionYear());
        
        mockMvc.perform(get(url))
                .andExpect(jsonPath("$.name").value(show.getName()))
                .andExpect(jsonPath("$.editionYear").value(show.getEditionYear()))
                .andExpect(status().isOk());
    }
    
    @Test
    @SneakyThrows
    public void shouldCreateNewShow() {
        final Show show = Show.builder()
                .name("Basel").editionYear(2017)
                .build();
        String jsonShow = mapper.writeValueAsString(show);
        
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(jsonShow))
        .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void shouldNotCreateInvalidShow() {
        mockMvc.perform(post("/shows"))
        .andExpect(status().isBadRequest());

    }
    
}
