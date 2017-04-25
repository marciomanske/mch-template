package com.dminc.event.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dminc.event.domain.Show;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ShowServiceClientFallback implements ShowServiceClient {

    @Override
    public Show incrementNumberOfEvents(String showId, int amount) {
        log.info("Fallback ShowServiceClientFallback#incrementNumberOfEvents");
        return new Show("-1", "Couldn't connect to the Show Service",0);
    }

    @Override
    public List<Show> getAllShows() {
        log.info("Fallback ShowServiceClientFallback#getAllShows");
        List<Show> list = new ArrayList<>();
        list.add(new Show("-1", "Couldn't connect to the Show Service",0));
        return list;
    }

}
