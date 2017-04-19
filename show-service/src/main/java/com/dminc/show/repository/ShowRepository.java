package com.dminc.show.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dminc.show.domain.Show;

@Repository
public interface ShowRepository extends CrudRepository<Show, String> {

    Show findByNameAndEditionYear(String name, Integer editionYear);
    
}
