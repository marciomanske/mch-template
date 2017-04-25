package com.dminc.event.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dminc.event.domain.Show;

@FeignClient(name = "show-service", configuration = ShowServiceClientConfiguration.class, fallback=ShowServiceClientFallback.class)
public interface ShowServiceClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/shows/{showId}/events/{amount}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Show incrementNumberOfEvents(@PathVariable("showId") String showId, @PathVariable("amount") int amount);
    
    @RequestMapping(method = RequestMethod.GET, value = "/shows/")
    List<Show> getAllShows();
}
