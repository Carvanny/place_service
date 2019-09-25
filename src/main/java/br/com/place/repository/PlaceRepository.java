package br.com.place.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.place.model.Place;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String>{

}
