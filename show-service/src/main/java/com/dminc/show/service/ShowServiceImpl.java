package com.dminc.show.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.dminc.show.domain.Show;
import com.dminc.show.repository.ShowRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Override
    public List<Show> findAll() {
        List<Show> result = new ArrayList<>();
        showRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    public Show createShow(Show show) {

        Assert.notNull(show);
        Assert.notNull(show.getName());
        Assert.notNull(show.getEditionYear());

        Show existingShow = showRepository.findByNameAndEditionYear(show.getName(), show.getEditionYear());

        Assert.isNull(existingShow, "Show already exists");

        showRepository.save(show);

        log.info("Show {}/{} created", show.getName(), show.getEditionYear());

        return show;
    }

    @Override
    public Show saveShow(Show show) {

        Assert.notNull(show);
        Assert.notNull(show.getName());
        Assert.notNull(show.getEditionYear());

        showRepository.save(show);

        log.info("Show {}/{} updated", show.getName(), show.getEditionYear());
        return show;
    }

    @Override
    public Show findByNameAndYear(String name, Integer year) {
        Assert.hasLength(name);
        Assert.notNull(year);
        return showRepository.findByNameAndEditionYear(name, year);
    }

    @Override
    public Show incrementNumberOfEvents(String showId, int amount) {
        Assert.notNull(showId);
        Assert.hasLength(showId);
        Assert.isTrue(amount != 0, "Amount can't be 0");
        Show show = showRepository.findOne(showId);
        
        Assert.notNull(show, String.format("Show does not exist id: %s", showId));
        
        show.addNumberOfEvents(amount);
        
        return this.saveShow(show); 
    }


}
