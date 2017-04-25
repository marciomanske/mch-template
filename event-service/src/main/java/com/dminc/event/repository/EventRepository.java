package com.dminc.event.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dminc.event.domain.Event;
import com.dminc.event.domain.Show;

@Repository
public interface EventRepository extends CrudRepository<Event, String> {

    Event findByInternalTitleAndShow(String externalTitle, Show show);
    
}
