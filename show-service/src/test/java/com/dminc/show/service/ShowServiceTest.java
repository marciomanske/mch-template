package com.dminc.show.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.dminc.show.domain.Show;
import com.dminc.show.repository.ShowRepository;


public class ShowServiceTest {

    @InjectMocks
    private ShowServiceImpl showService;
    
    @Mock
    private ShowRepository showRepository;
    
    @Before
    public void setup() {
        initMocks(this);
    }
    
    @Test
    public void shouldFindByNameAndYear() {
        final Show show = Show.builder()
                    .name("Basel")
                    .editionYear(2017).build();
        when(showService.findByNameAndYear(show.getName(), show.getEditionYear())).thenReturn(show);
        Show found = showService.findByNameAndYear(show.getName(), show.getEditionYear()); 
        assertEquals(show, found);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNameOrYearIsEmpty() {
        showService.findByNameAndYear(null, 2017); 
    }
    
    @Test
    public void shouldCreateNewShow() {
        Show show = Show.builder()
                    .name("Hong Kong")
                    .editionYear(2017)
                    .endDate("2017-04-10")
                    .startDate("2017-04-15")
                    .build();
        
        show = showService.createShow(show);
        
        verify(showRepository, times(1)).save(show);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenShowIsDuplicated() {
        Show show = Show.builder()
                .name("San Francisco")
                .editionYear(2017)
                .endDate("2017-04-10")
                .startDate("2017-04-15")
                .build();
        
        when(showRepository.findByNameAndEditionYear(show.getName(), show.getEditionYear())).thenReturn(Show.builder().build());
        showService.createShow(show);
    }
    
}
