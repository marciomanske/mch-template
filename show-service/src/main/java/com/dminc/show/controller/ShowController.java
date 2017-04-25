package com.dminc.show.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dminc.show.domain.Show;
import com.dminc.show.service.ShowService;

@RestController
public class ShowController {

    @Autowired
    private ShowService showService;
    
    @RequestMapping(path = "/{name}/{year}", method = RequestMethod.GET)
    public Show getShowByNameAndYear(@PathVariable String name, @PathVariable Integer year) {
        return showService.findByNameAndYear(name, year);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Show> getAllShows() {
        return showService.findAll();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Show createNewShow(@Valid @RequestBody Show show) {
        return showService.createShow(show);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public Show saveShow(@Valid @RequestBody Show show) {
        return showService.saveShow(show);
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(path="/{showId}/events/{amount}", method = RequestMethod.PUT)
    public Show incrementNumberOfEvents(@PathVariable String showId, @PathVariable int amount) {
        return showService.incrementNumberOfEvents(showId, amount);
    }
    
}
