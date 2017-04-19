package com.dminc.show.repository;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dminc.show.ShowApplication;
import com.dminc.show.domain.Show;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShowApplication.class)
public class ShowRepositoryTest {

    @Autowired
    private ShowRepository showRepository;
    
    @Test
    public void createAndFindShow() {
        
        Show show = Show.builder()
                        .name("Basel")
                        .editionYear(2017)
                        .startDate("2017-02-01")
                        .endDate("2017-02-10")
                        .build();
        showRepository.save(show);
        
        Show foundShow = showRepository.findByNameAndEditionYear(show.getName(), show.getEditionYear());
        
        assertArrayEquals(new Object[]{show.getName(), show.getEditionYear()}, new Object[]{foundShow.getName(), foundShow.getEditionYear()});
        
        
    }
    
    
}
