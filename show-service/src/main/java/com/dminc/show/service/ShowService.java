package com.dminc.show.service;

import java.util.List;

import com.dminc.show.domain.Show;

public interface ShowService {

    List<Show> findAll();
    Show createShow(Show show);
    Show saveShow(Show show);
    Show findByNameAndYear(String name, Integer year);
    Show incrementNumberOfEvents(String showId, int amount);
}
